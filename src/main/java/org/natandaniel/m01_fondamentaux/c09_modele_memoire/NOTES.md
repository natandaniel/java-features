# Modèle mémoire

> Module `m01_fondamentaux / c09_modele_memoire`
> Leçon / exercices / solutions / tests : **à produire** (`java-mentor`).
> Voir aussi : [`c01_types_primitifs`](../c01_types_primitifs/NOTES.md), [`c07_references`](../c07_references/NOTES.md), [`c08_variables`](../c08_variables/NOTES.md), [`c10_execution`](../c10_execution/NOTES.md) (le processus **autour** de cette mémoire).

La JVM range les données dans deux zones distinctes selon le type de la variable. Un **primitif** stocke sa valeur directement dans la variable. Une **référence** stocke l'adresse d'un objet alloué sur le tas. Le programmeur n'alloue jamais explicitement la mémoire (`new` suffit) et ne la libère jamais : le *garbage collector* s'en charge.

## Zones mémoire

| Zone | Contenu | Portée | Libération |
|------|---------|--------|------------|
| **Pile** (*stack*) | un *frame* par appel de méthode : variables locales, paramètres, valeur de retour | un thread | au retour de la méthode (LIFO) |
| **Tas** (*heap*) | tous les objets (`new`), tableaux, leurs champs | partagé entre threads | par le GC |
| **Metaspace** | métadonnées de classes, code JIT, champs `static` | partagé, hors tas | au déchargement de la classe |

- Une pile **par thread** ; chaque thread ne voit que ses frames. Sa taille est bornée (`-Xss`).
- Un seul tas, partagé. Sa taille est bornée (`-Xmx`).
- Le metaspace remplace la *PermGen* depuis Java 8.

## Pile : les frames

Chaque appel de méthode empile un *frame* (JVMS §2.6) qui contient :
- les **variables locales** dans des *slots* numérotés (`this` occupe le slot 0 d'une méthode d'instance) ;
- la **pile d'opérandes** (calculs intermédiaires du bytecode) ;
- une référence vers le *pool de constantes* de la classe.

Le frame est créé à l'appel, détruit au retour — coût O(1), pas de GC. Une récursion trop profonde épuise la pile → `StackOverflowError`.

## Primitif vs référence

```
int x = 42;                 // les 4 octets de 42 vivent dans le slot de x
String s = new String("ab"); // le slot de s contient une adresse → objet sur le tas
```

- Lire un primitif local = un accès mémoire direct (souvent élevé en registre par le JIT).
- Lire un champ via une référence = au moins deux indirections : lire l'adresse, déréférencer vers le tas, lire le champ.
- Un **champ primitif d'instance** est stocké *inline* dans le corps de l'objet sur le tas — la valeur elle-même, jamais un pointeur. Détail des 8 types → [`c01_types_primitifs`](../c01_types_primitifs/NOTES.md).
- Une référence Java est une valeur **opaque** gérée par la JVM, pas une adresse brute manipulable comme un pointeur C. Le GC peut donc déplacer l'objet et mettre à jour les références.

## Où vit chaque variable

| Kind de variable (JLS §4.12.1) | Emplacement |
|--------------------------------|-------------|
| Variable locale, paramètre | pile (slot du frame) |
| Champ d'instance | tas, *inline* dans l'objet |
| Champ de classe (`static`) | metaspace (zone de la classe) |
| Composant de tableau | tas (le tableau est un objet) |

Une variable locale de type référence vit sur la **pile**, mais l'**objet** qu'elle désigne vit sur le **tas**. Les six kinds → [`c08_variables`](../c08_variables/NOTES.md).

## Schéma mémoire

```
Thread stack                          Heap
┌──────────────────────┐             ┌───────────────────────────────┐
│ frame main()         │             │  String                       │
│   int x   = [42]     │             │  ┌─────────────────────────┐  │
│   String s = ref ───────────────▶  │  │ header (mark + klass)   │  │
└──────────────────────┘             │  │ byte[] value ───────────────▶ {'a','b'}
                                     │  └─────────────────────────┘  │
                                     └───────────────────────────────┘
```

## Allocation et durée de vie

- `new` alloue sur le tas et renvoie une référence. L'objet vit tant qu'il reste **accessible** (*reachable*) depuis une racine GC (variables de pile actives, champs `static`, références JNI).
- Dès qu'un objet n'est plus accessible, il devient candidat au GC. La mémoire est récupérée à un moment indéterminé, pas immédiatement.
- Pas de `free`/`delete` : libérer manuellement est impossible. Mettre une référence à `null` ne libère rien directement ; cela supprime seulement un chemin d'accessibilité.
- *Escape analysis* (JIT) peut allouer un objet à courte vie sur la pile ou le supprimer (*scalar replacement*) — optimisation invisible, jamais garantie.

## Garbage collection

- **Générationnel** : les objets naissent dans la *young generation* (collectée souvent, *minor GC*) ; les survivants sont promus en *old generation* (*major/full GC*, plus coûteux). Hypothèse : la plupart des objets meurent jeunes.
- Le GC parcourt le graphe d'accessibilité depuis les racines, marque le vivant, récupère le reste (*mark-sweep*), puis compacte souvent le tas (les références déplacées sont mises à jour).
- `System.gc()` est une **suggestion**, pas un ordre.
- `finalize()` est déprécié (Java 9) ; pour libérer une ressource, utiliser `try-with-resources` / `AutoCloseable`, jamais le GC.

## Pièges

- **Fuite mémoire en Java** : un objet inutile mais toujours **accessible** (jamais retiré d'une `static` Map, d'une collection, d'un cache, d'un listener) n'est jamais collecté → `OutOfMemoryError: Java heap space`. Le GC ne récupère que l'inaccessible.
- **`StackOverflowError` vs `OutOfMemoryError`** : le premier vient d'une pile saturée (récursion sans fin) ; le second d'un tas saturé. Ne pas les confondre.
- **`final` ne fige que la variable** : sur une référence, l'objet pointé reste modifiable (`final List` → `add` autorisé). Voir [`c08_variables`](../c08_variables/NOTES.md).
- **`==` sur des références teste l'identité** (même objet en mémoire), pas le contenu → utiliser `.equals()`. Voir [`c07_references`](../c07_references/NOTES.md).
- **Passage par valeur toujours** : réaffecter un paramètre référence dans une méthode n'affecte pas l'appelant ; modifier l'objet pointé, si. Voir [`c08_variables`](../c08_variables/NOTES.md).
- **`null` ne libère pas** : utile seulement pour rompre l'accessibilité d'un objet à longue vie ; inutile sur une locale en fin de méthode (le frame disparaît seul).

## Exemple

```java
void demo() {
    int a = 10;                  // pile : valeur 10 dans le slot de a
    int[] t = {1, 2, 3};         // pile : ref de t ; tas : l'objet tableau
    StringBuilder sb = new SB(); // pile : ref de sb ; tas : l'objet

    modifie(a, t, sb);
    // a  vaut toujours 10  : primitif copié, l'original intact
    // t  contient {99,2,3} : objet partagé, contenu modifié via la copie de l'adresse
    // sb contient "x"      : même objet, muté
}

void modifie(int a, int[] t, StringBuilder sb) {
    a = 0;          // modifie la copie locale — invisible à l'appelant
    t[0] = 99;      // déréférence l'adresse copiée — visible
    sb.append("x"); // mute l'objet partagé — visible
}
// au retour, le frame de modifie() est dépilé ; les objets restent
// sur le tas tant que demo() les référence, puis deviennent collectables.
```
