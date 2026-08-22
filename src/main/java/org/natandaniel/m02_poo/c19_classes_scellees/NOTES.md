# Classes scellées

> Module `m02_poo / c19_classes_scellees`
> Leçons : `Ex01_ClasseScelleeAvecPermitsExplicite`, `Ex02_PermitsImplicite`,
> `Ex03_InterfaceScelleeAvecSousTypesMixtes`, `Ex04_NonSealedRouvreLExtensibilite`
> Exercices : `Exo01_MethodesSurUneHierarchieScellee`, `Exo02_ExtensionDUneBrancheNonSealed`
> Prérequis : `c06_heritage` (`extends`, restriction de sous-typage), `c12_final_classe_methode`
> (`final` sur une classe — `c12` renvoie explicitement `sealed`/`non-sealed` ici),
> `c15_interfaces_classes_abstraites` (`implements`, méthode abstraite)
> Correspondance `ocp-curriculum` : `lessons/I-langage/B-poo/classes-scellees.md` (`domaine: I.B`,
> `prerequis: [heritage, final-classes-et-methodes]`, `niveau: intermediaire`,
> `statut: brouillon`)

`final` (`c12`) interdit **toute** sous-classe. `sealed` (JLS §8.1.1.2, `// @since Java 17`, JEP
409) offre un contrôle **plus fin** : la classe reste extensible, mais seulement par un ensemble
**connu et fermé** de sous-classes, énumérées via la clause `permits` (§8.1.6). Chaque sous-classe
directe doit à son tour choisir explicitement `final`, `sealed` ou `non-sealed` — jamais de choix
implicite, pour éviter d'exposer accidentellement la hiérarchie à une extension non désirée.

```java
sealed class ModePaiement permits CarteBancaire, VirementBancaire, Especes { ... }

final class CarteBancaire extends ModePaiement { ... }      // ferme cette branche
final class VirementBancaire extends ModePaiement { ... }   // ferme cette branche
non-sealed class Especes extends ModePaiement { ... }       // rouvre cette branche
```

---

## 1. `permits` explicite : la liste fermée des sous-classes directes (`Ex01`)

Une classe scellée n'existe que si **toutes** ses sous-classes directes sont connues au moment où
elle est déclarée (JLS §8.1.1.2). La clause `permits` (§8.1.6) les énumère par leur nom ; chacune
doit être accessible et déclarer `ModePaiement` comme superclasse directe, ou c'est une erreur de
compilation. Symétriquement, chaque classe listée dans `permits` doit se déclarer `final`,
`sealed` ou `non-sealed` — omettre les trois est une erreur de compilation (illustrée en
commentaire dans `Ex01`, non testable en JUnit puisque c'est une erreur *à la compilation*, pas à
l'exécution).

## 2. `permits` implicite : inféré de la même unité de compilation (`Ex02`)

La clause `permits` est optionnelle (§8.1.6). Si elle est omise, les sous-classes permises sont
**inférées** : ce sont toutes les classes de la **même unité de compilation** (le même fichier
`.java`, §7.3) qui déclarent la classe scellée comme superclasse directe et possèdent un nom
canonique (donc ni classe locale ni classe anonyme). C'est l'usage le plus idiomatique en
pratique — la liste est déjà visible dans le fichier, la répéter dans `permits` est redondant.

## 3. Une interface aussi peut être scellée, avec des sous-types mixtes (`Ex03`)

Une interface ne peut jamais être `final` (§9.1) — mais elle peut être `sealed` (§9.1.1.4), avec
exactement les mêmes règles de fond. Sa clause `permits` (§9.1.4) peut mélanger **classes et
interfaces** parmi ses sous-types directs autorisés : `Paiement` scelle à la fois une classe
(`PaiementImmediat`, qui `implements`) et une interface (`PaiementDiffere`, qui `extends`) —
celle-ci doit à son tour choisir `sealed` ou `non-sealed` (jamais `final`, réservé aux classes).

## 4. `non-sealed` rouvre l'extensibilité en dessous d'elle (`Ex04`)

Dès qu'une classe a une superclasse ou une superinterface `sealed`, elle est **contrainte** de
choisir `final`, `sealed` ou `non-sealed` (§8.1.1.2) — sans quoi c'est une erreur de compilation.
`non-sealed` est le seul des trois qui **rouvre totalement** l'extensibilité : n'importe quelle
classe peut ensuite étendre cette branche librement, sans toucher à `permits` sur la classe
scellée d'origine. C'est le point d'échappement contrôlé d'une hiérarchie par ailleurs fermée —
utile quand une seule variante (ex. `Especes`) doit rester ouverte à des extensions tierces
(devises étrangères) alors que les autres (`CarteBancaire`) doivent rester définitivement figées.

## Hors scope (concepts suivants ou hors périmètre)

- **`enum`/`record` comme cas implicitement `final`/`sealed`** — JLS §8.1.1.2 : « An enum class is
  either implicitly final or implicitly sealed [...] a record class is implicitly final ». Ces
  deux mécanismes ne sont pas encore construits ici (`c20_enumerations`, `c21_records`) ; le lien
  sera fait à ce moment-là, pas anticipé maintenant.
- **`switch` pattern matching exhaustif sur une hiérarchie scellée** — l'exhaustivité vérifiée à
  la compilation (savoir qu'il n'existe aucun autre cas) est justement l'intérêt pratique le plus
  cité de `sealed`, mais le mécanisme du `switch` sur pattern n'est pas encore traité ; différé à
  `m11_modernite/c03_pattern_matching`. Les leçons ici utilisent `instanceof`/cast classique ou la
  liaison dynamique ordinaire, jamais un `switch` sur type.
- **`Comparable<T>`** comme interface implémentée par une hiérarchie scellée — différé à
  `c18_interface_comparable` (dépend des génériques).
- **Contraintes de module/package sur `permits`** (JLS §8.1.6/§9.1.4 : co-localisation obligatoire
  dans le même module nommé ou le même package pour un module non nommé) — pertinent surtout à
  l'échelle d'un projet multi-modules (JPMS), hors périmètre d'un seul fichier de leçon ; renvoi
  possible vers `m11_modernite/c04_modules_jpms`.

## Ancrage dans la spec

- **JLS §8.1.1.2 « sealed, non-sealed, and final Classes »** (`jls26.pdf`, p.250 imprimée) :
  > *"A class can be declared sealed if all its direct subclasses are known when the class is
  > declared [...] A class that has a sealed direct superclass or a sealed direct superinterface
  > is freely extensible if and only if it is declared non-sealed. It is a compile-time error if a
  > class has a sealed direct superclass or a sealed direct superinterface, and is not declared
  > final, sealed, or non-sealed either explicitly or implicitly."*
  Fondement d'`Ex01` (règle de base) et `Ex04` (obligation `non-sealed`, propagation).
- **JLS §8.1.6 « Permitted Direct Subclasses »** (p.264-266) :
  > *"If the declaration of a sealed class C lacks a permits clause, then [...] its permitted
  > direct subclasses are those classes declared in the same compilation unit as C [...] which
  > have a canonical name and whose direct superclass is C."*
  Fondement d'`Ex02` (`permits` implicite).
- **JLS §9.1.1.4 « sealed and non-sealed Interfaces »** (p.355-356) et **§9.1.4 « Permitted Direct
  Subclasses and Subinterfaces »** (p.358-359) :
  > *"An interface can be declared sealed if all its direct subclasses and direct subinterfaces
  > are known when the interface is declared [...] The optional permits clause in a normal
  > interface declaration specifies all the classes and interfaces intended as direct subclasses
  > and direct subinterfaces of the interface being declared."*
  Fondement d'`Ex03` (interface scellée, `permits` mixte classes/interfaces).

Couvert dans ce concept : §8.1.1.2 (règles de compilation pour les classes, en entier), §8.1.6
(`permits` explicite et implicite), §9.1.1.4 et §9.1.4 (équivalent côté interface, `permits`
mixte). Explicitement non couvert, renvoyé aux points listés en « Hors scope » ci-dessus : le lien
avec `enum`/`record`, l'exhaustivité via `switch` pattern matching, les contraintes de
module/package sur `permits`.

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — face à une sous-classe directe d'une classe `sealed` qui omet
  `final`/`sealed`/`non-sealed` (comme le commentaire d'`Ex01`), expliquer pourquoi ça ne compile
  pas et quelles sont les trois façons de corriger — teste la règle de base avant toute nuance sur
  `permits` implicite ou `non-sealed`.
- **Application** — étant donné une nouvelle hiérarchie scellée à modéliser (un domaine différent,
  ex. statuts d'une livraison), écrire la classe `sealed` avec `permits` explicite et ses
  sous-classes `final` — transfère le patron d'`Ex01`/`Exo01` à un nouveau domaine, sans variante
  `non-sealed`.
- **Transfert / cas limite** — face à une hiérarchie où une seule branche est `non-sealed` (comme
  `Ex04`), prédire si une classe tierce peut étendre cette branche, et si elle peut étendre la
  classe scellée racine directement — teste la distinction entre « rouvrir une branche précise »
  et « rouvrir toute la hiérarchie », piège facile juste après avoir vu `permits` comme liste
  fermée.

## Pistes d'approfondissement (DeepDive)

- **Pourquoi `sealed` a été introduit alors que `final` existait déjà** — rationale JEP 409 :
  modéliser « les sortes de valeurs d'un domaine » plutôt que favoriser la réutilisation de code
  par héritage (cité explicitement en §8.1.1.2 : *"useful when the class hierarchy is used to
  model the kinds of values in a domain, rather than as a mechanism for code inheritance and
  reuse"*) — angle historique/design, n'est pas nécessaire pour écrire ou lire le code du concept.
  Comparaison possible avec les types somme (ADT) d'autres langages (Kotlin `sealed class`,
  Scala `sealed trait`, Rust `enum`).
- **Contraintes de co-localisation module/package** (déjà notées en « Hors scope ») —
  approfondissement naturel une fois `m11_modernite/c04_modules_jpms` traité ; optionnel ici car
  sans effet observable sur un projet à module unique comme ce catalogue.
