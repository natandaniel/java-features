# Modèle mémoire

> Module `m01_fondamentaux / c09_modele_memoire`
> Leçon : `lecon/Ex01` → `Ex06` · Entraînement : `exercices/` + `solutions/` (Exo01 à Exo03).
> Voir aussi : [`c01_types_primitifs`](../c01_types_primitifs/NOTES.md), [`c08_references_variables`](../c08_references_variables/NOTES.md), [`c10_execution`](../c10_execution/NOTES.md) (le processus **autour** de cette mémoire).

La JVM range les données dans deux zones distinctes selon le type de la variable. Un **primitif** stocke sa valeur directement dans la variable. Une **référence** stocke l'adresse d'un objet alloué sur le tas. Le programmeur n'alloue jamais explicitement la mémoire (`new` suffit) et ne la libère jamais : le *garbage collector* s'en charge.

## Zones mémoire

| Zone | Contenu | Portée | Libération |
|------|---------|--------|------------|
| **Pile** (*stack*) | un *frame* par appel de méthode : variables locales, paramètres, valeur de retour | un thread | au retour de la méthode (LIFO) |
| **Tas** (*heap*) | tous les objets (`new`), tableaux, leurs champs | partagé entre threads | par le GC |
| **Metaspace** | métadonnées de classes, code JIT, champs `static` | partagé, hors tas | au déchargement de la classe |

- Une pile **par thread** ; chaque thread ne voit que ses frames. Sa taille est bornée (`-Xss`).
- Un seul tas, partagé. Sa taille est bornée (`-Xmx`).
- Le metaspace remplace la *PermGen* — `@since Java 8`.

→ Démo : `lecon/Ex01_PileEtTas`.

## Pile : les frames

Chaque appel de méthode empile un *frame* (JVMS §2.6) qui contient :
- les **variables locales** dans des *slots* numérotés (`this` occupe le slot 0 d'une méthode d'instance) ;
- la **pile d'opérandes** (calculs intermédiaires du bytecode) ;
- une référence vers le *pool de constantes* de la classe.

Le frame est créé à l'appel, détruit au retour — coût O(1), pas de GC. Une récursion trop profonde épuise la pile → `StackOverflowError`.

Java n'élimine **pas** la récursion terminale : même un appel en position de retour garde son frame jusqu'à la remontée. Une somme récursive de 1 à *n* consomme donc *n* frames, là où sa version itérative en consomme un seul. La profondeur maximale atteignable dépend de `-Xss`, de la taille des frames et du JIT — elle n'est jamais une constante sur laquelle s'appuyer.

→ Démo : `lecon/Ex02_FramesEtPile` · Exercice : `Exo02_Recursion`.

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

Une variable locale de type référence vit sur la **pile**, mais l'**objet** qu'elle désigne vit sur le **tas**. Les six kinds → [`c08_references_variables`](../c08_references_variables/NOTES.md).

Corollaire souvent mal vu : un champ `static` a **une seule case pour toute la classe**, un champ d'instance **une case par objet**. Un cache `static` vit donc aussi longtemps que la classe est chargée — c'est ce qui en fait le vecteur de fuite le plus courant.

→ Démo : `lecon/Ex04_StaticEtInstance`.

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

## Aliasing : copie superficielle vs copie profonde

Affecter une variable de type référence copie une **adresse**, jamais l'objet. Deux variables qui désignent le même objet en sont des **alias** : muter par l'une se voit par l'autre.

```java
int[] original = {1, 2, 3};
int[] alias = original;      // copie l'adresse (4 ou 8 octets), pas les 3 entiers
alias[0] = 99;               // original[0] vaut 99 : un seul objet sur le tas
original == alias            // true — identité, pas contenu
```

La distinction devient visible sur un tableau 2D, qui est un **tableau de références** vers des tableaux 1D :

| Copie | Ce qui est alloué | Effet d'une mutation `copie[i][j] = x` |
|-------|-------------------|----------------------------------------|
| **superficielle** (`source.clone()`) | un nouveau tableau externe ; les lignes sont partagées | la source change aussi |
| **profonde** (`ligne.clone()` pour chaque ligne) | un nouveau tableau externe **et** une nouvelle ligne par ligne | la source est intacte |

- `clone()` sur un tableau est toujours **superficiel** : il ne descend jamais dans les éléments. Idem pour `Arrays.copyOf`, `System.arraycopy`, `List.copyOf`.
- Il n'y a pas de copie profonde générique en Java : la profondeur voulue est un choix de conception, à écrire explicitement.
- Comparer le **contenu** demande `Arrays.equals` (1 niveau) ou `Arrays.deepEquals` (imbriqué) ; `==` ne répond qu'à la question de l'identité.
- Les objets **immuables** (`String`, `Integer`, `LocalDate`) rendent la question sans objet : partager un objet qu'on ne peut pas muter est sans risque. C'est l'argument mémoire principal en faveur de l'immuabilité.

→ Démo : `lecon/Ex03_Aliasing` · Exercice : `Exo01_Copies`.

## Allocation et durée de vie

- `new` alloue sur le tas et renvoie une référence. L'objet vit tant qu'il reste **accessible** (*reachable*) depuis une racine GC (variables de pile actives, champs `static`, références JNI).
- Dès qu'un objet n'est plus accessible, il devient candidat au GC. La mémoire est récupérée à un moment indéterminé, pas immédiatement.
- Pas de `free`/`delete` : libérer manuellement est impossible. Mettre une référence à `null` ne libère rien directement ; cela supprime seulement un chemin d'accessibilité.
- *Escape analysis* (JIT) peut allouer un objet à courte vie sur la pile ou le supprimer (*scalar replacement*) — optimisation invisible, jamais garantie.

## Garbage collection

- **Générationnel** : les objets naissent dans la *young generation* (collectée souvent, *minor GC*) ; les survivants sont promus en *old generation* (*major/full GC*, plus coûteux). Hypothèse : la plupart des objets meurent jeunes.
- Le GC parcourt le graphe d'accessibilité depuis les racines, marque le vivant, récupère le reste (*mark-sweep*), puis compacte souvent le tas (les références déplacées sont mises à jour).
- `System.gc()` est une **suggestion**, pas un ordre.
- `finalize()` est **déprécié** — `@since Java 9` ; pour libérer une ressource, utiliser `try-with-resources` / `AutoCloseable` (`@since Java 7`), ou `Cleaner` (`@since Java 9`) — jamais le GC.
- `WeakReference` (`@since Java 1.2`) permet d'**observer** l'accessibilité sans la maintenir : elle ne retient pas son référent. Utile pour instrumenter, jamais pour synchroniser — le moment de la collecte reste imprévisible.

Ce qui est **garanti** : un objet inaccessible ne sera jamais ressuscité. Ce qui ne l'est **pas** : le moment de la collecte, ni même qu'elle ait lieu avant la fin du programme.

→ Démo : `lecon/Ex05_Accessibilite` (non testable en JUnit : `System.gc()` n'est pas déterministe).

## Pièges

- **Fuite mémoire en Java** : un objet inutile mais toujours **accessible** (jamais retiré d'une `static` Map, d'une collection, d'un cache, d'un listener) n'est jamais collecté → `OutOfMemoryError: Java heap space`. Le GC ne récupère que l'inaccessible.
- **`StackOverflowError` vs `OutOfMemoryError`** : le premier vient d'une pile saturée (récursion sans fin) ; le second d'un tas saturé. Ne pas les confondre.
- **`final` ne fige que la variable** : sur une référence, l'objet pointé reste modifiable (`final List` → `add` autorisé). Voir [`c08_references_variables`](../c08_references_variables/NOTES.md).
- **`==` sur des références teste l'identité** (même objet en mémoire), pas le contenu → utiliser `.equals()`. Voir [`c08_references_variables`](../c08_references_variables/NOTES.md).
- **Passage par valeur toujours** : réaffecter un paramètre référence dans une méthode n'affecte pas l'appelant ; modifier l'objet pointé, si. Voir [`c08_references_variables`](../c08_references_variables/NOTES.md).
- **`null` ne libère pas** : utile seulement pour rompre l'accessibilité d'un objet à longue vie ; inutile sur une locale en fin de méthode (le frame disparaît seul).
- **`clone()` cru profond** : sur un tableau ou une collection, il ne copie que le premier niveau. Les éléments restent partagés.

→ Démo : `lecon/Ex06_FuiteMemoire` · Exercice : `Exo03_Accessibilite`.

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
