# Méthodes par défaut d'interface

> Module `m02_poo / c16_methodes_par_defaut`
> Leçons : `Ex01_MethodeParDefautEtRetrocompatibilite`, `Ex02_RedefinitionDeMethodeParDefaut`,
> `Ex03_HeritageEntreInterfacesSansConflit`, `Ex04_ConflitEntreMethodesParDefaut`
> Exercices : `Exo01_RedefinitionDeMethodeParDefaut`, `Exo02_ResolutionDeConflitEntreInterfaces`
> Prérequis : `c15_interfaces_classes_abstraites` (interface = contrat sans état, méthode
> abstraite implicite)
> Correspondance `ocp-curriculum` : `lessons/I-langage/methodes-par-defaut.md`
> (`domaine: I.B`, `prerequis: [interfaces]`, `niveau: intermediaire`, `statut: brouillon`)

Depuis Java 8, une interface peut porter une méthode avec un corps : une méthode `default`. Elle
fournit une implémentation de repli, héritée telle quelle par toute classe qui implémente
l'interface sans la redéfinir — une classe reste libre de la redéfinir comme n'importe quelle
méthode héritée.

```java
interface MoyenDePaiement {
    boolean payer(double montant);            // toujours implicitement abstract

    default double fraisTraitement(double montant) {  // @since Java 8
        return 0.0;                             // implémentation de repli
    }
}
```

---

## 1. Une méthode `default` a un corps, hérité si non redéfini (`Ex01`)

Une méthode `default` (JLS §9.4) « est une méthode d'instance déclarée dans une interface avec le
modificateur `default`. Son corps est toujours représenté par un bloc, qui fournit une
implémentation par défaut pour toute classe qui implémente l'interface sans redéfinir la
méthode. » Une classe qui implémente `MoyenDePaiement` sans écrire `fraisTraitement` hérite
directement du corps `return 0.0;` — elle n'est *pas* obligée de le faire, contrairement à une
méthode abstraite.

**Motivation historique** : avant Java 8, ajouter une méthode à une interface publiée cassait
toute classe qui l'implémentait déjà (erreur de compilation, méthode abstraite non fournie). Le
modificateur `default` permet de faire évoluer une interface existante — cas réel : l'ajout de
`forEach` à `Iterable` ou de `stream` à `Collection` en Java 8 — sans obliger chaque
implémentation existante à être modifiée.

## 2. Une classe implémentante peut redéfinir le corps par défaut (`Ex02`)

Redéfinir une méthode `default` dans une classe implémentante suit les règles habituelles de
redéfinition (`c07_polymorphisme`) : la redéfinition prime, liaison dynamique à l'exécution.
Depuis cette redéfinition, l'implémentation par défaut de l'interface reste accessible
explicitement — elle n'est pas perdue. JLS §9.4.1.1 : « Un `default` redéfini peut être accédé au
moyen d'une expression d'invocation de méthode qui contient le mot-clé `super` qualifié par un
nom de superinterface. » `MoyenDePaiement.super.fraisTraitement(montant)` appelle précisément
cette implémentation, permettant de l'étendre plutôt que de la remplacer intégralement.

## 3. Héritage entre interfaces : pas de conflit si une seule branche redéfinit (`Ex03`)

Un diamant d'interfaces (une interface étendue par deux autres, réunies par une quatrième)
n'entraîne pas nécessairement de conflit. JLS §9.4.1 — exemple canonique repris ici avec
`OperationFinanciere`/`Remboursable`/`Facturable`/`Avoir` (calqué sur l'exemple
`Top`/`Left`/`Right`/`Bottom` de la spec) : si une seule branche (`Remboursable`) redéfinit
`resume()`, la sous-interface (`Avoir`) hérite de *cette* redéfinition, pas de celle
d'`OperationFinanciere`, ni d'une ambiguïté avec `Facturable` (qui, elle, n'a rien redéfini).
« La troisième clause empêche une sous-interface d'hériter à nouveau d'une méthode déjà
redéfinie par une autre de ses superinterfaces. »

## 4. Conflit réel entre deux méthodes `default` : résolution explicite (`Ex04`)

Quand les deux branches du diamant redéfinissent `default` la **même** méthode différemment, il y
a un vrai conflit. JLS §9.4.1.3 : « Si une interface I hérite d'une méthode `default` dont la
signature est override-équivalente à une autre méthode héritée par I, une erreur de compilation
se produit. » La sous-interface (`Avoir`) ne compile plus tant qu'elle n'a pas sa propre
redéfinition — impossible de laisser Java choisir silencieusement une branche. La résolution
combine les deux implémentations en désambiguïsant chaque appel par le nom de la superinterface :

```java
interface Avoir extends Remboursable, Facturable {
    @Override
    default String resume() {
        return Remboursable.super.resume() + " + " + Facturable.super.resume();
    }
}
```

Même traitement (erreur, résolution par redéfinition explicite) pour un conflit entre une méthode
`abstract` et une méthode `default` de même signature (§9.4.1.3, second cas) : Java ne suppose
jamais qu'un `default` rencontré par coïncidence de nom/signature est une implémentation valide
de la méthode abstraite.

## Hors scope (concepts suivants, ou non planifiés)

- **Méthodes `static`/`private` d'interface** (JLS §9.4, même section que `default`) — non
  traité ici : la leçon `ocp-curriculum` ciblée (`methodes-par-defaut.md`) porte uniquement sur
  `default`, et aucun concept du `ROADMAP.md` ne leur est aujourd'hui réservé. **Gap identifié
  le 2026-08-22** — à ajouter au `ROADMAP.md` comme concept futur si `ocp-curriculum` en a
  besoin, pas improvisé ici.
- **Une classe implémente directement plusieurs interfaces** (`implements A, B`) — démontré ici
  seulement entre interfaces (`extends` en diamant, `Ex03`/`Ex04`), jamais entre une classe et
  plusieurs interfaces directement implémentées. Différé à `c17_heritage_multiple_types`.
- **Priorité d'une méthode concrète de classe sur un `default` d'interface** (JLS §8.4.8, évoqué
  en fin de §9.4.1.3 mais spécifié ailleurs) — hors scope, relève de la hiérarchie de classes,
  pas des interfaces elles-mêmes.
- **Surcharge (`overloading`) entre méthodes `default`** (JLS §9.4.2) — non spécifique à
  `default`, déjà couvert par la résolution de surcharge générale (`c13_methodes`).

## Ancrage dans la spec

- **JLS §9.4 « Method Declarations »** (`jls26.pdf`, p.364-365 imprimées) :
  > *"A default method is an instance method declared in an interface with the default modifier.
  > Its body is always represented by a block, which provides a default implementation for any
  > class that implements the interface without overriding the method. Default methods are
  > distinct from concrete methods (§8.4.3.1), which are declared in classes, and from private
  > interface methods, which are neither inherited nor overridden."*
  Fondement d'`Ex01`.
- **JLS §9.4.1 « Inheritance and Overriding »** (p.366-367), exemple `Top`/`Left`/`Right`/`Bottom` :
  > *"Right inherits name() from Top, but Bottom inherits name() from Left, not Right. This is
  > because name() from Left overrides the declaration of name() in Top."*
  Fondement d'`Ex03`.
- **JLS §9.4.1.1 « Overriding (by Instance Methods) »** (p.367) :
  > *"An overridden default method can be accessed by using a method invocation expression
  > (§15.12) that contains the keyword super qualified by a superinterface name."*
  Fondement de `Interface.super.methode()` dans `Ex02`/`Ex04`.
- **JLS §9.4.1.3 « Inheriting Methods with Override-Equivalent Signatures »** (p.369) :
  > *"If an interface I inherits a default method whose signature is override-equivalent with
  > another method inherited by I, then a compile-time error occurs... The error can be avoided
  > by declaring a new method that overrides, and thus prevents the inheritance of, all
  > conflicting methods."* Et : *"Similarly, when an abstract method and a default method with
  > matching signatures are inherited by a subinterface, we produce an error."*
  Fondement d'`Ex04` (conflit `default`/`default` et rappel `abstract`/`default`).

Couvert dans ce concept : §9.4 (définition `default`), §9.4.1 (héritage sans conflit),
§9.4.1.1 (`super` qualifié), §9.4.1.3 (conflit `default`/`default` et `abstract`/`default`).
Explicitement non couvert, renvoyé aux points listés en « Hors scope » ci-dessus : §9.4
(`static`, `private` d'interface), §9.4.1.3 dernier paragraphe (priorité classe-sur-interface,
§8.4.8), §9.4.2 (surcharge).

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — face à un diamant d'interfaces où une seule branche redéfinit une
  méthode `default`, prédire quelle implémentation une sous-interface hérite. Teste la règle
  subtile « pas de ré-héritage d'une méthode déjà supplantée par une autre branche » (§9.4.1,
  `Ex03`), source de confusion facile (on pourrait croire à une ambiguïté qui n'existe pas).
- **Application** — étant donné une nouvelle interface à méthode `default` (ex. un contrat
  applicable à un nouveau domaine), écrire une classe qui la redéfinit en délégant à
  l'implémentation d'origine via `Interface.super.methode()` — transfère le patron d'`Ex02` à un
  nouveau domaine.
- **Transfert / cas limite** — face à un diamant où deux branches redéfinissent différemment la
  même méthode, expliquer pourquoi ça ne compile pas et écrire la résolution (`Ex04`). Teste
  l'idée centrale de la leçon (le conflit est actif, pas silencieusement résolu par Java), pas
  seulement la syntaxe de `Interface.super.methode()`.

## Pistes d'approfondissement (DeepDive)

- **Pourquoi `equals`/`hashCode`/`toString` ne peuvent jamais être des méthodes `default`** —
  rationale directement tirée de la JLS (§9.4.1.2) : le risque qu'une superinterface change
  silencieusement le comportement hérité d'`Object` pour toute classe qui l'implémente.
  Optionnel : éclaire une limite précise du mécanisme, mais la leçon n'a pas besoin de
  l'exclusion d'`Object` pour être comprise.
- **Méthodes `private` d'interface (Java 9)** — code partagé entre plusieurs méthodes `default`
  d'une même interface, sans faire partie du contrat public exposé aux classes implémentantes.
  Optionnel : préfigure `c25_methodes_statiques_et_privees_interface` (non traité ici), pure
  curiosité complémentaire qui ne conditionne pas la compréhension du mécanisme `default`
  lui-même.
