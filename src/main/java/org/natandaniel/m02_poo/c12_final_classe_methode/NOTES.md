# `final` sur une classe ou une méthode

> Module `m02_poo / c12_final_classe_methode`
> Leçons : `Ex01_ClasseFinal`, `Ex02_MethodeFinal`, `Ex03_MethodeEffectivementFinale`,
> `Ex04_ClasseUtilitaire`
> Exercices : `Exo01_InvariantProtegeParFinal`, `Exo02_ClasseUtilitaireNonInstantiable`
> Prérequis : `c06_heritage` (`extends`), `c07_polymorphisme` (redéfinition — `final` est la
> réponse au « comment l'empêcher »), `c10_methodes_fabriques_statiques` (constructeur `private`,
> réutilisé dans `Ex04`)
> Correspondance `ocp-curriculum` : `lessons/I-langage/final-classes-et-methodes.md` (domaine
> `I.B`, `prerequis: [heritage]`)

Le mot-clé `final` a **deux emplois distincts** en POO, tous deux tournés vers la même idée :
interdire une extension du comportement là où elle romprait un invariant.

```java
final class Montant { ... }          // aucune sous-classe possible (JLS §8.1.1.2)

class CompteBancaire {
    final boolean estRetraitValide(long montant) { ... }   // pas de redéfinition (JLS §8.4.3.3)
}
```

---

## 1. `final` sur une classe : aucune sous-classe possible (`Ex01`)

Une classe déclarée `final` ne peut avoir **aucune** sous-classe (JLS §8.1.1.2) — tenter
`class MontantBonus extends Montant` est une erreur de compilation, pas une exception à
l'exécution. C'est un choix de conception fait une fois, à la déclaration de la classe.

Erreur de compilation associée : déclarer une classe à la fois `final` et `abstract` est interdit
(JLS §8.1.1.2) — une classe `abstract` *exige* d'être complétée par une sous-classe, ce que
`final` empêche justement.

## 2. `final` sur une méthode : pas de redéfinition, même dans une classe extensible (`Ex02`)

Une méthode déclarée `final` ne peut être **ni redéfinie ni masquée** par aucune sous-classe (JLS
§8.4.3.3), même si la classe elle-même reste ouverte à l'extension. C'est la granularité fine par
rapport à la classe `final` : `CompteBancaire` peut être étendue (`CompteBancairePremium` redéfinit
librement `solde()`), mais la règle de sécurité `estRetraitValide` ne peut pas être affaiblie par
une sous-classe qui la redéfinirait pour la contourner.

## 3. Redondance : méthode déjà « effectivement finale » (`Ex03`)

Deux cas rendent `final` **redondant sur une méthode**, sans que ce soit une erreur de l'écrire :

- **Toute méthode d'une classe `final`** — puisque la classe elle-même n'a aucune sous-classe
  possible, aucune de ses méthodes ne peut jamais être redéfinie, avec ou sans le mot-clé.
- **Toute méthode `private`** — une méthode `private` n'est pas héritée (JLS §8.4.8.1, déjà vu
  côté `c07_polymorphisme` : une sous-classe qui déclare la même signature crée une méthode sans
  rapport, pas une redéfinition). Le mot-clé `final` ne change donc rien à un comportement déjà
  garanti par `private` seul.

La JLS le formule directement (§8.4.3.3) : *"A private method and all methods declared
immediately within a final class behave as if they are final, since it is impossible to override
them."*

## 4. Cas d'usage réel : la classe utilitaire non instantiable et non extensible (`Ex04`)

Le patron illustré par `java.lang.Math` — cité textuellement par la JLS elle-même en §8.1.1.1 —
combine deux mécanismes déjà vus séparément :

- **Constructeur `private`** (JLS §8.8.10, `c10_methodes_fabriques_statiques`) : empêche
  l'instanciation.
- **Classe `final`** (JLS §8.1.1.2) : empêche l'extension — une classe qui n'expose que des
  méthodes `static` n'a aucune raison d'être sous-classée.

```java
public final class Math {
    private Math() { } // never instantiate this class
    . . . declarations of class variables and methods . . .
}
```

## Piège classique : confondre « ne peut pas être redéfini » et « ne peut pas changer de valeur »

`final` sur une **classe** ou une **méthode** est une restriction de **structure du programme**
(qui peut hériter/redéfinir quoi), vérifiée à la compilation — sans rapport avec `final` sur un
**champ ou une variable** (qui restreint l'**assignation**, une fois la valeur fixée). Les deux
partagent le mot-clé mais pas le mécanisme ; `final` sur un champ est traité séparément dans
`c13_classes_immuables` (à venir), qui s'appuie sur ce concept-ci pour les invariants de classe.

## Hors scope (concepts suivants ou hors périmètre)

- **`sealed`/`non-sealed`** (JLS §8.1.1.2, même section que `final` sur une classe) — contrôle
  plus fin de l'ensemble des sous-classes directes autorisées, plutôt que leur interdiction totale.
  Renvoyé à `c19_classes_scellees`.
- **`final` sur un champ ou une variable locale** — restreint l'assignation, pas l'héritage ;
  mécanique différente bien que partageant le mot-clé. Renvoyé à `c13_classes_immuables`.
- **Inlining par le compilateur/JIT** (mentionné par JLS §8.4.3.3 comme motivation historique de
  performance) — cité comme rationale dans la spec, mais implémentation interne de la JVM, pas un
  comportement observable depuis le code Java lui-même.
- **`strictfp`** (JLS §8.1.1.3, §8.4.3.5, mentionné dans le même bloc de modificateurs que
  `final`) — modificateur obsolète (« has no effect at compile time or run time »), non traité.

## Ancrage dans la spec

- **JLS §8.1.1.2 « sealed, non-sealed, and final Classes »** (`jls26.pdf`, p.250 imprimée) :
  > *"A class can be declared final if its definition is complete and no subclasses are desired
  > or required. […] It is a compile-time error if a class is declared both final and abstract […]
  > Because a final class never has any subclasses, the methods of a final class are never
  > overridden (§8.4.8.1)."*
  Fondement direct d'`Ex01` : aucune sous-classe possible, et la contrainte `final`+`abstract`
  incompatible.
- **JLS §8.4.3.3 « final Methods »** (p.296 imprimée) :
  > *"A method can be declared final to prevent subclasses from overriding or hiding it. It is a
  > compile-time error to attempt to override or hide a final method. A private method and all
  > methods declared immediately within a final class (§8.1.1.2) behave as if they are final,
  > since it is impossible to override them."*
  Fondement direct d'`Ex02` (restriction de redéfinition) et d'`Ex03` (redondance dans les deux
  cas cités explicitement par la spec).
- **JLS §8.1.1.1 « abstract Classes »** (p.250 imprimée, exemple `Math` cité tel quel) :
  > *"If the intent is simply to prevent instantiation of a class, the proper way to express this
  > is to declare a constructor (§8.8.10) of no arguments, make it private, never invoke it, and
  > declare no other constructors. […] public final class Math { private Math() { } . . . }"*
  Fondement direct d'`Ex04` — l'exemple `Math` de la spec est repris identiquement (domaine
  différent, même patron).
- **JLS §8.4.8.1** (p.304-307, déjà cité dans `c07_polymorphisme`) — confirme qu'une méthode
  `private` n'est pas héritée, base du second cas d'`Ex03`.

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — pourquoi `CommandeVip` (qui redéfinit librement `fraisLivraison`)
  ne peut pas redéfinir `remiseApplicable` pour, par exemple, doubler la remise autorisée. Reprend
  directement `Ex02` : distinguer ce qu'une sous-classe peut et ne peut pas changer selon que la
  méthode est `final` ou non — point de départ, avant d'aller plus loin.
- **Application** — étant donné une classe `final` représentant un identifiant (type valeur,
  comme `Montant`), écrire le raisonnement (pas de code à faire compiler en échec) pour lequel
  aucune tentative d'extension n'est possible, et pourquoi c'est voulu pour un type qui représente
  une valeur figée. Transfert du patron `Ex01` vers un nouveau domaine.
- **Transfert / cas limite** — face à une méthode `private` **sans** le mot-clé `final` dans une
  classe non-`final`, déterminer si elle peut être redéfinie par une sous-classe qui déclare la
  même signature, et expliquer ce que fait réellement cette sous-classe si elle le fait quand
  même (elle déclare une méthode sans rapport, pas une redéfinition — renvoi direct à
  `c07_polymorphisme`). Vient en dernier : généralise la distinction `final`-explicite vs
  `final`-de-fait vue dans `Ex03`.

## Pistes d'approfondissement (DeepDive)

- **Inlining et optimisation JIT des méthodes `final`** (JLS §8.4.3.3, rationale historique) —
  pourquoi le compilateur/JIT peut remplacer un appel à une méthode `final` par le code de son
  corps, et les précautions qu'il doit prendre (ex. `NullPointerException` toujours levée au bon
  endroit). Optionnel : détail d'implémentation de la JVM, sans impact sur l'écriture de code Java
  correct.
- **`sealed`/`non-sealed` comme alternative plus fine à `final`** — contrôler *lesquelles*
  sous-classes sont permises plutôt que d'en interdire toute possibilité. Optionnel : sujet
  complet à part entière (`c19_classes_scellees`, à venir), pas nécessaire pour comprendre `final`.
- **`strictfp`, modificateur obsolète du même groupe syntaxique** — pourquoi il apparaît encore
  dans la grammaire (JLS §8.1.1.3, §8.4.3.5) alors qu'il n'a plus aucun effet depuis Java 17.
  Optionnel : curiosité historique de compatibilité, sans impact pratique.
