# Héritage multiple de type

> Module `m02_poo / c17_heritage_multiple_types`
> Leçons : `Ex01_ImplementationDePlusieursInterfaces`, `Ex02_MemeSignatureAbstraiteSansConflit`,
> `Ex03_ConflitEntreDefautsDInterfacesDirectes`, `Ex04_PrioriteDeLaMethodeConcreteDeClasse`
> Exercices : `Exo01_ImplementationDePlusieursInterfaces`,
> `Exo02_ResolutionDeConflitEntreInterfacesDirectes`
> Prérequis : `c15_interfaces_classes_abstraites` (`implements`, méthode abstraite implicite),
> `c16_methodes_par_defaut` (méthode `default`, résolution de conflit via
> `Interface.super.methode()` — réutilisée ici depuis une classe plutôt qu'entre interfaces)
> Correspondance `ocp-curriculum` : `lessons/I-langage/B-poo/heritage-multiple-types.md`
> (`domaine: I.B`, `prerequis: [heritage, interfaces]`, `niveau: intermediaire`,
> `statut: brouillon`)

Une classe Java ne peut hériter que d'une seule superclasse (`extends`, JLS §8.1.4), mais peut
implémenter plusieurs interfaces à la fois (`implements`, JLS §8.1.5, une liste). Ce concept
traite ce que `c16` avait laissé de côté : implémenter directement plusieurs interfaces (pas
seulement les faire s'étendre entre elles), et les conséquences quand leurs méthodes se
recoupent.

```java
interface Remboursable {
    void rembourser(double montant);
}

interface Facturable {
    String genererFacture();
}

class Avoir implements Remboursable, Facturable {
    // doit fournir rembourser(...) ET genererFacture()
}
```

---

## 1. Une classe peut implémenter plusieurs interfaces (`Ex01`)

`implements` accepte une liste de types (JLS §8.1.5) : une classe qui implémente `Remboursable`
et `Facturable` à la fois doit fournir un corps pour chaque méthode abstraite héritée de l'une ou
l'autre, exactement comme pour une seule interface (`c15`). C'est la différence structurelle avec
`extends` (§8.1.4), limité à une seule superclasse directe — Java interdit l'héritage multiple de
*classes*, jamais l'implémentation multiple d'*interfaces*.

## 2. Deux méthodes abstraites de même signature ne sont pas un conflit si elles sont indépendantes (`Ex02`)

JLS §8.4.8, Example 8.4.8-1 (repris ici avec `Remboursable`/`Tracable`, toutes deux déclarant
`String reference()` sans lien de sous-typage entre elles) : « `Test` hérite des deux méthodes
`foo` ; elle doit forcément être déclarée abstraite, ou redéfinir les deux méthodes abstraites
avec une seule méthode concrète. » La question clé posée par la spec — *est-ce que l'une
« override » l'autre ?* — répond non ici (les interfaces ne sont pas apparentées), donc pas
d'ambiguïté : une seule implémentation suffit à satisfaire les deux contrats.

## 3. Conflit réel entre deux méthodes `default` d'interfaces directement implémentées (`Ex03`)

Variante « côté classe » du conflit vu en `c16` (`Ex04`, entre interfaces liées par `extends`).
JLS §8.4.8.4 : « C'est une erreur de compilation si une classe C hérite d'une méthode `default`
dont la signature est override-équivalente à une autre méthode héritée par C […] » — même sans
diamant d'héritage entre interfaces, deux `default` en conflit directement implémentées par une
classe bloquent la compilation. La résolution est identique à `c16` : la classe redéfinit la
méthode elle-même, en désambiguïsant chaque appel avec `Interface.super.methode()` (§9.4.1.1) —
la qualification par nom de superinterface fonctionne aussi bien depuis une classe que depuis une
interface.

## 4. Une méthode concrète de superclasse l'emporte toujours sur un `default` d'interface (`Ex04`)

Point explicitement laissé hors scope par `c16`. JLS §8.4.8 : « il est possible qu'une méthode
concrète héritée empêche l'héritage d'une méthode abstraite ou `default`. (La méthode concrète
« override » la méthode abstraite ou `default` « from C », cf. §8.4.8.1 et §9.4.1.1.) » Une classe
qui hérite d'une méthode concrète de sa superclasse et implémente une interface au `default` de
même signature n'a **aucun conflit à résoudre** : la méthode de classe prévaut systématiquement,
sans redéfinition nécessaire — contrairement au cas `Ex03`, où deux interfaces sont à égalité.

## Hors scope (au-delà de ce concept)

- **Héritage multiple avec generics et effacement de type** (JLS §8.4.8.4, paragraphes sur les
  signatures override-équivalentes via paramétrage générique) — trop avancé pour l'idée centrale
  du concept (implémenter plusieurs interfaces), relève de `m05_generiques`.
- **`sealed`/`non-sealed`/`permits`** sur une interface implémentée par plusieurs classes —
  différé à `c19_classes_scellees`.
- **`Comparable<T>`** comme interface implémentée aux côtés d'autres — différé à
  `c18_interface_comparable` (dépend des génériques).

## Ancrage dans la spec

- **JLS §8.1.5 « Superinterfaces »** (`jls26.pdf`, p.261-263 imprimées, déjà cité dans `c15`) — la
  clause `implements` acceptant une liste. Fondement d'`Ex01`.
- **JLS §8.1.4 « Superclasses and Subclasses »** (p.258, déjà cité dans `c06`) — une seule
  superclasse directe, contraste structurel avec §8.1.5.
- **JLS §8.4.8 « Inheritance, Overriding, and Hiding »** (p.303-304), Example 8.4.8-1 :
  > *"Here, the abstract class Test inherits the abstract method foo from interface I1 and also
  > the abstract method foo from interface I2 [...] Per §8.4.8.4, class Test can inherit both foo
  > methods; obviously it must be declared abstract, or else override both abstract foo methods
  > with a concrete method."*
  Fondement d'`Ex02`. Même section :
  > *"Note that it is possible for an inherited concrete method to prevent the inheritance of an
  > abstract or default method. (The concrete method will override the abstract or default
  > method 'from C', per §8.4.8.1 and §9.4.1.1.)"*
  Fondement d'`Ex04`.
- **JLS §8.4.8.4 « Inheriting Methods with Override-Equivalent Signatures »** (p.313) :
  > *"It is a compile-time error if a class C inherits a default method whose signature is
  > override-equivalent with another method inherited by C, unless there exists an abstract
  > method declared in a superclass of C and inherited by C that is override-equivalent with the
  > two methods."*
  Fondement d'`Ex03` (conflit réel) et de la nuance d'`Ex04` (l'exception, quand la méthode
  abstraite override-équivalente vient d'une superclasse).

Couvert dans ce concept : §8.1.5 (implémentation multiple), §8.1.4 (contraste avec l'héritage
simple), §8.4.8 (héritage, priorité classe-sur-interface), §8.4.8.4 (1er et 2ᵉ paragraphes,
conflit `default`/`default` entre interfaces directement implémentées). Explicitement non
couvert, renvoyé aux points listés en « Hors scope » ci-dessus : le reste de §8.4.8.4 (generics,
override-équivalence via paramétrage), §9.1.3 (héritage entre interfaces, déjà traité en `c16`).

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — face à une classe qui implémente deux interfaces indépendantes
  déclarant chacune une méthode abstraite de même nom et signature (comme `Ex02`), prédire si ça
  compile et pourquoi ce n'est *pas* un conflit — teste la distinction entre « même signature par
  coïncidence » et « conflit override-équivalent réel », source de confusion facile juste après
  avoir vu un vrai conflit en `c16`.
- **Application** — étant donné deux nouvelles interfaces à méthodes abstraites disjointes (un
  contrat applicable à un nouveau domaine), écrire une classe qui les implémente toutes les deux
  — transfère le patron d'`Ex01`/`Exo01` à un nouveau domaine.
- **Transfert / cas limite** — face à une classe qui hérite d'une méthode concrète de sa
  superclasse et implémente une interface au `default` de même signature (comme `Ex04`),
  expliquer pourquoi aucune redéfinition n'est nécessaire, à la différence du cas `Ex03` où deux
  interfaces sont à égalité — teste la hiérarchie de priorité complète (classe > interface >
  conflit à résoudre), pas seulement la syntaxe `Interface.super.methode()`.

## Pistes d'approfondissement (DeepDive)

- **Pourquoi Java autorise l'héritage multiple d'interfaces mais pas de classes** — rationale
  déjà esquissée en DeepDive de `c15` (« diamond problem » de C++) ; ici, l'angle change : ce
  n'est plus *pourquoi les interfaces peuvent être multiples*, mais *pourquoi les conflits qui en
  résultent restent gérables* (résolution explicite obligatoire, jamais de choix silencieux par
  le compilateur) — comparaison possible avec les traits Scala ou les mixins d'autres langages.
  Optionnel : angle comparatif, n'est pas nécessaire pour écrire ou lire le code du concept.
- **`Object` comme cas limite implicite** — toute interface hérite implicitement des signatures
  d'`Object` (`equals`, `hashCode`, `toString`) sans jamais pouvoir les redéfinir en `default`
  (JLS §9.4.1.2, déjà noté en DeepDive de `c16`) ; ce concept permet de fermer la boucle :
  plusieurs interfaces implémentées par une même classe ne peuvent donc jamais entrer en conflit
  sur ces trois méthodes précises. Optionnel : referme un point déjà semé en `c16`, curiosité qui
  ne conditionne pas la compréhension du mécanisme central ici.
