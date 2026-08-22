# Classes immuables

> Module `m02_poo / c13_classes_immuables`
> Leçons : `Ex01_ChampsFinalSansMutateur`, `Ex02_ChampFinalReferenceMutable`,
> `Ex03_CopieDefensive`, `Ex04_ClasseFinalRenforceImmutabilite`
> Exercices : `Exo01_CopieDefensiveConstructeur`, `Exo02_CopieDefensiveAccesseur`
> Prérequis : `c05_encapsulation` (champs privés, accesseurs), `c12_final_classe_methode`
> (`final` sur un champ est une mécanique différente de `final` sur une classe/méthode — mais
> les deux se recroisent dans `Ex04`)
> Correspondance `ocp-curriculum` : `lessons/I-langage/classes-immuables.md` (domaine `I.B`,
> `prerequis: [encapsulation]`)

Une classe immuable garantit qu'une fois construite, son état ne change plus jamais — ni par
une méthode de la classe elle-même, ni par du code extérieur, même indirectement. C'est
l'encapsulation (`c05`) poussée à son terme : non seulement les champs sont privés, mais rien ne
permet jamais de les réassigner après construction.

```java
class Trajet {
    private final String origine, destination;
    private final double distanceKm;
    Trajet(String origine, String destination, double distanceKm) { ... }   // seule écriture
    // aucun mutateur
}
```

---

## 1. Champs `final`, aucun mutateur (`Ex01`)

Le point de départ : tous les champs sont déclarés `final` et assignés une seule fois, dans le
constructeur — un champ `final` sans initialiseur (« *blank final* ») doit être **définitivement
assigné** à la fin de chaque constructeur de la classe, ou c'est une erreur de compilation
(JLS §8.3.1.2). Aucune méthode n'expose de mutateur : une fois `Trajet` construit, son état ne
peut plus être modifié par aucun moyen offert par la classe elle-même.

## 2. Le piège : `final` protège la variable, pas l'objet référencé (`Ex02`)

`final` sur un champ **référence** (ici un tableau) interdit uniquement de réassigner ce champ à
un *autre* tableau — il ne dit strictement rien sur le contenu du tableau pointé, qui reste
librement modifiable. Un accesseur naïf qui renvoie directement cette référence interne laisse
n'importe quel appelant modifier l'état de l'objet de l'extérieur, sans jamais passer par une
méthode de la classe : `Releve` n'est final qu'en apparence.

## 3. La correction : copie défensive (`Ex03`)

Deux copies distinctes, à deux endroits distincts :

- **En entrée**, dans le constructeur — copier le tableau reçu avant de l'assigner au champ, pour
  que l'appelant, en gardant sa propre référence, ne puisse plus jamais atteindre l'état interne.
- **En sortie**, dans l'accesseur — renvoyer une copie du tableau interne, jamais la référence
  elle-même, pour que l'appelant puisse librement modifier le résultat sans rien casser.

Ce patron (« *defensive copying* ») n'a **aucune section JLS dédiée** — ce n'est pas une règle du
langage mais une pratique de conception, popularisée par *Effective Java* (Item 50, « Make
defensive copies when needed »). Non disponible en source locale vérifiée (voir « Ancrage dans la
spec » ci-dessous).

## 4. Pourquoi la classe elle-même est souvent `final` (`Ex04`, renvoi `c12`)

Les champs `private final` interdisent déjà à toute sous-classe de **modifier** l'état hérité —
ça, aucune sous-classe ne peut le contourner. Mais rien n'empêche une sous-classe de redéfinir un
**accesseur non-final** pour le rendre impur : `IdentifiantInstable` ne touche à aucun champ
`final`, et pourtant `valeur()` renvoie un résultat différent à chaque appel. La garantie
d'immuabilité ne porte alors plus sur le *type* `Identifiant`, seulement sur les instances de la
classe de base elle-même.

La correction rejoint directement `c12_final_classe_methode` : déclarer l'accesseur `final` (il ne
peut plus être redéfini, JLS §8.4.3.3), ou — plus simple — déclarer la classe entière `final` (JLS
§8.1.1.2), comme le fait `Montant` en `c12`. C'est pourquoi la plupart des classes immuables du
JDK (`String`, les types enveloppe, `LocalDate`…) sont `final`.

## Hors scope (concepts suivants ou hors périmètre)

- **`Cloneable`/`Object.clone()`** — un mécanisme de copie alternatif, avec ses propres pièges
  (copie superficielle par défaut). Renvoyé à `c14_clonage`.
- **`record`** — une syntaxe dédiée (Java 14+) qui génère automatiquement champs `final`,
  constructeur canonique et accesseurs pour un type immuable, sans réduire le besoin de copie
  défensive pour les composants mutables. Renvoyé à `c21_records`, qui s'appuie explicitement sur
  ce concept-ci.
- **Sémantique mémoire de `final` en contexte concurrent** (JLS §17.5) — garanties de visibilité
  entre threads pour un champ `final` correctement construit ; sujet à part entière de
  `m07_concurrence`, mentionné ici uniquement comme piste d'approfondissement.
- **`java.time`** (`LocalDate`, etc.) — exemples réels de classes immuables du JDK, mais leur
  API est le sujet propre de `m09_date_temps/c01_api_java_time`.

## Ancrage dans la spec

- **JLS §4.12.4 « final Variables »** (`jls26.pdf`, p.103 imprimée) :
  > *"Once a final variable has been assigned, it always contains the same value. If a final
  > variable holds a reference to an object, then the state of the object may be changed by
  > operations on the object, but the variable will always refer to the same object."*
  Fondement direct d'`Ex02` — la spec énonce littéralement la distinction que la leçon illustre :
  `final` fixe la variable, jamais l'état de l'objet référencé.
- **JLS §8.3.1.2 « final Fields »** (p.281 imprimée) :
  > *"A blank final instance variable must be definitely assigned and moreover not definitely
  > unassigned at the end of every constructor of the class in which it is declared, or a
  > compile-time error occurs."*
  Fondement d'`Ex01` — un champ `final` sans initialiseur doit être assigné dans **chaque**
  constructeur, exactement une fois.
- **JLS §8.4.3.3 « final Methods »** et **§8.1.1.2 « sealed, non-sealed, and final Classes »**
  (déjà cités et sourcés dans `c12_final_classe_methode`) — fondement d'`Ex04`, repris tel quel
  sans reciter les extraits déjà consignés là-bas.
- **Hors JLS, signalé explicitement** : la copie défensive (`Ex03`) n'a pas de section JLS — la
  source usuelle est *Effective Java* Item 50, **non disponible en local** dans
  `sources/Effective Java/` (seul l'Item 18 y est photographié) ; contenu basé sur connaissance
  générale de cet Item, pas une citation vérifiée sur pièce (même limite déjà signalée pour
  `c10_methodes_fabriques_statiques`, Item 1).

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — face à `Releve` (`Ex02`), identifier précisément quelle ligne permet
  à un appelant de corrompre l'état interne malgré le champ `final`, et pourquoi. Point de départ :
  vérifier que la distinction « `final` protège la variable, pas l'objet » est bien comprise avant
  d'aller plus loin.
- **Application** — étant donné une nouvelle classe à immuniser (état mutable interne, ex. une
  liste d'étapes de livraison), déterminer quelles deux copies ajouter et où, en transférant le
  patron d'`Ex03` à un nouveau domaine.
- **Transfert / cas limite** — un champ `final` référençant un objet dont le **type lui-même est
  immuable** (ex. un `String`, ou un `Trajet` d'`Ex01`) a-t-il encore besoin d'une copie
  défensive ? Fait raisonner sur la condition réelle d'application du patron (le type référencé
  est-il lui-même immuable ?), pas seulement sur son mécanisme. Vient en dernier : généralise au
  lieu de répéter `Ex03`.

## Pistes d'approfondissement (DeepDive)

- **JLS §17.5, sémantique mémoire de `final` en contexte concurrent** — garanties de visibilité
  entre threads, sans synchronisation, pour un objet immuable correctement construit. Optionnel :
  anticipe `m07_concurrence`, sans impact sur l'écriture de code Java correct dans ce module-ci.
- **`record` (Java 14+) comme sucre syntaxique pour l'immuabilité** — pourquoi il génère
  automatiquement champs `final`/constructeur/accesseurs, mais ne dispense pas de copie défensive
  pour un composant mutable. Optionnel : sujet complet à part entière (`c21_records`, à venir).
- **Le compromis de conception (*Effective Java* Item 17)** — sûreté de partage, mise en cache,
  utilisation comme clé, contre le coût de churn d'objets à chaque « modification » (qui crée en
  réalité un nouvel objet). Optionnel : angle rationale/performance, pas nécessaire pour écrire du
  code correct.
