# Méthodes et structuration d'un programme

> Module `m01_fondamentaux / c13_methodes`
> Leçons : `lecon/Ex01_DeclarationEtStructure`, `Ex02_ParametresEtPassageParValeur`, `Ex03_ValeurDeRetour`,
> `Ex04_ParametresVariables`, `Ex05_Surcharge`
> Exercices : `exercices/Exo01_Moyenne`, `Exo02_FormaterDuree`, `Exo03_ConvertirDistance`
> Repose sur `c08_references_variables` (passage par valeur — non répété ici) ; le principe de la
> surcharge est déjà posé pour les constructeurs dans `m02_poo/c02_constructeurs`, repris ici pour
> les méthodes.

Ce concept traite uniquement la **structure d'une méthode** : déclaration, paramètres, retour,
corps, surcharge, varargs. Les modificateurs (`public`/`private`/`static`/`abstract`/`final`...)
sont des notions orientées objet, couvertes ailleurs (`m02_poo/c04_membres_statiques`,
`c05_encapsulation`, et un futur `c12_final_classe_methode`) ou hors périmètre du catalogue
(`native`, `synchronized`) — **non traités ici**.

---

## Déclaration et signature (JLS §8.4, §8.4.2)

Une déclaration de méthode réunit un type de retour, un nom, une liste de paramètres et un corps.
Le nom seul ne suffit pas à identifier une méthode : sa **signature** est la combinaison du nom et
des types de ses paramètres (le type de retour n'en fait **pas** partie). Deux méthodes déclarées
dans la même classe avec des signatures « override-equivalentes » (au sens strict, identiques)
sont une erreur de compilation, même si leurs types de retour diffèrent.

Une méthode n'existe qu'à l'intérieur d'une classe — il n'y a pas de fonction « libre » en Java.
Structurer un programme en méthodes permet de nommer une étape de calcul et de la réutiliser
(une méthode peut en appeler une autre), plutôt que de tout dérouler dans un seul bloc.

## Paramètres formels (JLS §8.4.1)

Chaque paramètre déclaré est une variable **locale** à la méthode, initialisée avec la valeur de
l'argument correspondant au moment de l'appel — l'association se fait par **position**, jamais par
nom. Java passe toujours **par valeur** (la nuance pour les références est déjà traitée dans
`c08_references_variables`, non répétée ici) : réaffecter un paramètre dans le corps ne modifie
jamais la variable de l'appelant.

## Paramètres à arité variable — varargs (JLS §8.4.1, **@since Java 5**)

Un paramètre suivi de `...` (*variable arity parameter*) accepte un nombre quelconque d'arguments,
y compris zéro. À l'intérieur du corps, il est traité comme un tableau ordinaire du type déclaré.
Contraintes : au plus **un** paramètre varargs par méthode, et il doit obligatoirement occuper la
**dernière** position de la liste — sinon erreur de compilation. Appeler une méthode varargs avec
un tableau déjà construit du bon type est équivalent à l'appeler avec les éléments listés un par un
(pas de nouvelle allocation intermédiaire dans ce cas).

## Résultat d'une méthode (JLS §8.4.5, §8.4.7)

Le résultat déclare soit `void` (aucune valeur renvoyée), soit un type de retour. Une méthode à
type de retour non-`void` est soumise à une règle stricte : « *a compile-time error occurs if the
body of the method can complete normally* » (JLS §8.4.7) — le corps ne doit jamais pouvoir
« tomber en bas » sans avoir rencontré un `return`. Cette règle n'exige pas un `return` visible
dans chaque branche : une méthode qui lève systématiquement une exception la satisfait aussi,
puisqu'elle ne complète jamais normalement.

**Hors scope** : la covariance du type de retour entre une méthode et celle qu'elle redéfinit
(*return-type-substitutability*, JLS §8.4.5) — déjà traitée dans `m02_poo/c07_polymorphisme`.

## Surcharge (JLS §8.4.9)

Deux méthodes de même nom mais de signatures **non override-equivalentes** sont dites surchargées
— aucune erreur de compilation, et aucune relation n'est exigée entre leurs types de retour ou
leurs clauses `throws`. La variante effectivement appelée est déterminée à la **compilation**, à
partir du nombre d'arguments et de leur type statique (JLS §15.12.2, mécanique en plusieurs phases
non détaillée ici — voir `m02_poo/c02_constructeurs` pour le principe déjà posé sur les
constructeurs). Point à retenir sans entrer dans le détail des phases : à arité identique, une
variante à arité **fixe** est toujours préférée à une variante **varargs** quand les deux
correspondent.
