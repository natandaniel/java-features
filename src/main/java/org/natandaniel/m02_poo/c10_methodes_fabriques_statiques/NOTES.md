# Méthodes fabriques statiques

> Module `m02_poo / c10_methodes_fabriques_statiques`
> Leçons : `Ex01_NommerLIntention`, `Ex02_MasquerLImplementation`, `Ex03_ReutiliserUneInstance`
> Exercices : `Exo01_Coordonnees`, `Exo02_Formes`
> Prérequis : `c01_classes_objets`, `c02_constructeurs` (le constructeur qu'une fabrique statique
> vient compléter, jamais remplacer syntaxiquement) ; `c06_heritage`/`c07_polymorphisme` pour
> `Ex02_MasquerLImplementation` (type de retour plus large qu'une sous-classe).

Une **méthode fabrique statique** n'est rien de spécial au niveau du langage : c'est une méthode
`static` ordinaire (`c04_membres_statiques`), qui a pour rôle de construire et renvoyer une
instance de sa propre classe (ou d'un type apparenté), à la place d'un appel direct à `new`.
Rien dans la JLS ne la distingue d'une autre méthode `static` — l'intérêt vient entièrement de
la façon dont elle est utilisée, pas d'une syntaxe dédiée.

```java
class Temperature {
    private Temperature(double kelvin) { ... }

    static Temperature depuisCelsius(double celsius) { ... }
}
```

---

## 1. Nommer l'intention (`Ex01`)

Un constructeur porte toujours le nom de sa classe — impossible de le nommer autrement. Deux
constructeurs qui prendraient les **mêmes types de paramètres** pour exprimer deux intentions
différentes (« depuis Celsius » vs « depuis Fahrenheit », toutes deux `Temperature(double)`) sont
une **surcharge illégale** : signatures identiques, refusé à la compilation (JLS §8.4.2, rappel de
`c13_methodes`/`m01_fondamentaux`). Deux méthodes statiques **nommées**, en revanche, coexistent
sans ambiguïté : `depuisCelsius(double)` et `depuisFahrenheit(double)`.

## 2. Masquer l'implémentation (`Ex02`)

Le type de retour d'une méthode est **déclaré**, indépendamment du type exact de l'objet renvoyé
(tant qu'il en est un sous-type — polymorphisme, `c07_polymorphisme`). Une fabrique peut donc
déclarer un type de retour plus large (`Forme`) que la classe concrète qu'elle construit
(`Cercle`, `private`, invisible hors de son fichier). Un constructeur ne peut jamais offrir ça :
`new Cercle(...)` nomme et expose forcément `Cercle` à l'appelant — le nom de la classe concrète
est syntaxiquement obligatoire dans un appel à `new`.

## 3. Ne pas être obligée de créer une nouvelle instance (`Ex03`)

`new` alloue **systématiquement** une nouvelle instance ; une méthode, non. Une fabrique statique
peut renvoyer une instance déjà construite (ex. une constante partagée) plutôt que d'en allouer une
nouvelle à chaque appel. Le JDK l'utilise abondamment : `Boolean.valueOf(boolean)` renvoie toujours
l'une des deux constantes `Boolean.TRUE`/`Boolean.FALSE`, jamais une nouvelle instance.

**Distinction importante** : ceci n'est **pas** le patron de conception *Singleton* (qui garantit
qu'**une seule** instance existe dans toute l'application, en général exposée par un point d'accès
global unique). Ici, il s'agit seulement d'éviter une allocation inutile pour une valeur immuable
qui existe déjà — rien n'empêche une classe d'avoir plusieurs constantes ainsi réutilisées
(`Ex03` en a deux : `CONNECTE`, `DECONNECTE`).

## Le mécanisme qui force le passage par la fabrique : le constructeur `private`

Pour qu'une fabrique statique soit le **seul** point d'entrée (empêcher `new Classe(...)` en
dehors de la classe), son constructeur doit être déclaré `private` (JLS §8.8.10, voir « Ancrage
dans la spec » ci-dessous). Ce n'est pas obligatoire : une classe peut avoir à la fois un
constructeur public et des fabriques statiques (ex. beaucoup de classes du JDK) — mais c'est le
choix fait dans `Ex01` et `Ex03` de ce concept, pour rendre le contrôle total et visible.

## Fabriques dans le JDK

Les méthodes de fabrique de collections `List.of(...)`, `Set.of(...)`, `Map.of(...)`
(`// @since Java 9`) sont des fabriques statiques : elles construisent des collections
**immuables**, sans jamais exposer le nom de la classe d'implémentation concrète (masquage de
l'implémentation, point 2 ci-dessus) — et refusent `null`/les doublons via des règles propres à
chaque fabrique plutôt que via une signature de constructeur.

## Inconvénient : perte de la sous-classabilité

Une classe dont **tous** les constructeurs sont `private` ne peut pas être étendue (`extends`) :
aucune sous-classe ne pourrait appeler un constructeur de superclasse accessible (`c08_mot_cle_super`
— `super(...)` exige un constructeur au moins `protected`). C'est un compromis assumé quand une
classe est conçue pour n'être construite que via ses fabriques (`Ex01`, `Ex03` de ce concept).

## Hors scope (concepts suivants ou hors périmètre)

- **Patron de conception Singleton** — garantie d'unicité globale d'instance ; sujet de conception
  logicielle plus large, pas une illustration de fonctionnalité du langage.
- **Frameworks à fournisseur de service** (*service provider frameworks*, ex. `ServiceLoader`) —
  cas où la classe exacte renvoyée par une fabrique n'existe pas encore au moment d'écrire la
  fabrique elle-même ; nécessite des notions non encore couvertes ici.
- **`Objects.requireNonNull`/validation d'argument dans une fabrique** — même remarque que pour
  les constructeurs (`c02_constructeurs`) : reste au niveau mécanique, pas de validation ajoutée.
- **Records (`record`) et leur constructeur canonique implicite** — un `record` génère déjà un
  constructeur public ; les fabriques statiques y jouent un rôle différent (validation, alias
  nommés) — concept pas encore traité (`m02_poo/c21_records`, à venir).

## Ancrage dans la spec

- **JLS §8.4** (méthodes, déjà sourcé dans `c13_methodes`) : une fabrique statique n'est, du point
  de vue de la grammaire, qu'une méthode `static` ordinaire — aucune section JLS ne nomme ni ne
  distingue le « static factory method pattern » en tant que tel.
- **JLS §8.8.10 « Preventing Instantiation of a Class »** (`jls26.pdf`, p.331 imprimée — voir
  mémoire `jls_table_of_contents`), vérifié via `pdftotext` :
  > *"A class can be designed to prevent code outside the class declaration from creating
  > instances of the class by declaring at least one constructor, to prevent the creation of a
  > default constructor, and by declaring all constructors to be private (§6.6.1)."*
  C'est le mécanisme exact utilisé dans `Ex01`/`Ex03` pour forcer le passage par la fabrique —
  section directement pertinente, au-delà de ce que couvrait déjà `c02_constructeurs` (qui ne
  traitait que le constructeur par défaut, §8.8.9).
- **Hors JLS, à dire explicitement** : la convention de nommage (`of`/`valueOf`/`getInstance`/
  `depuisXxx`...) et le rationale de conception (les trois avantages ci-dessus, un quatrième et
  un cinquième non traités ici — variation du type renvoyé selon l'entrée, fabriques à
  fournisseur de service) viennent d'*Effective Java*, Item 1 (« Consider static factory methods
  instead of constructors »). Ce texte n'est **pas** disponible localement dans
  `sources/Effective Java/` (seul l'Item 18 y est photographié) — le contenu ci-dessus s'appuie
  donc sur la connaissance générale de cet Item, signalée ici comme source non-JLS et non
  vérifiée localement, plutôt que citée comme si elle l'était.

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — pourquoi deux constructeurs `Temperature(double)` (l'un « depuis
  Celsius », l'autre « depuis Fahrenheit ») seraient une surcharge illégale (signatures
  identiques, JLS §8.4.2), alors que deux fabriques statiques nommées coexistent sans problème.
  Question de compréhension, pas de code à écrire — teste directement l'idée centrale de la
  leçon (nommer l'intention), en premier car c'est le point de départ du raisonnement.
- **Application** — écrire une classe `Duree` avec deux fabriques nommées,
  `depuisSecondes(long)`/`depuisMinutes(long)`, et un constructeur `private` prenant la
  représentation canonique (secondes). Transfert mécanique direct du patron vu dans
  `Ex01`/`Exo01` (Temperature/Coordonnees) à un nouveau domaine — vient après le rappel, qui ne
  portait que sur le diagnostic.
- **Transfert / cas limite** — une fabrique `Notification.pour(String canal, String message)`
  qui renvoie une `Notification` (type déclaré) mais construit en réalité une
  `NotificationEmail` ou une `NotificationSms` (cachées) selon la valeur de `canal`. Ajoute une
  idée que `Ex02`/`Exo02` ne couvrent pas encore : le type concret renvoyé peut **varier d'un
  appel à l'autre** selon l'entrée, pas seulement rester masqué mais fixe. Vient en dernier, car
  il combine masquage de l'implémentation (`Ex02`) et une logique de sélection nouvelle.

## Pistes d'approfondissement (DeepDive)

- **Rationale *Effective Java* et usage massif dans le JDK** — pourquoi l'Item 1 recommande ce
  patron ; exemples du JDK (`Collectors`, `Executors`, `Optional.of`, `List.of`). Optionnel :
  purement informatif, aucune notion nouvelle indispensable pour utiliser une fabrique statique.
- **Frameworks à fournisseur de service** (*service provider frameworks*, ex. `ServiceLoader`,
  `DriverManager`) — cas où la classe exacte renvoyée par la fabrique n'existe pas encore au
  moment d'écrire la fabrique elle-même. Optionnel : angle avancé, hors du besoin immédiat pour
  comprendre ou utiliser une fabrique statique.
- **Discoverabilité — absence de signalement Javadoc** — contrairement à un constructeur (visible
  dans une section dédiée de la Javadoc), une fabrique statique n'est signalée nulle part
  spécifiquement, d'où l'importance des conventions de nommage (`of`/`valueOf`/`getInstance`...)
  pour rester repérable. Optionnel : nuance d'outillage/convention, pas de mécanique du langage.
