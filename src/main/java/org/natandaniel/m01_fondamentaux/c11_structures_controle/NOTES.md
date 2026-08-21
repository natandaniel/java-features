# Structures de contrôle

> Module `m01_fondamentaux / c11_structures_controle`
> Leçons : `lecon/Ex01_IfElse`, `Ex02_SwitchStatement`, `Ex03_BoucleWhile`, `Ex04_BoucleDoWhile`,
> `Ex05_BoucleFor`, `Ex06_BreakContinue`, `Ex07_PorteeDeBloc`
> Exercices : `exercices/Exo01_Classification`, `Exo02_JoursDuMois`, `Exo03_BouclesEtControle`
> Repose sur c01-c07 (types, opérateurs, conversions) ; aucun prérequis sur c08-c10.

---

## `if` / `else` (JLS §14.9)

`if (condition) bloc` exécute `bloc` seulement si `condition` (un `boolean`) est vraie. `else`
couvre le cas contraire. Un enchaînement `else if` n'est **pas** une syntaxe dédiée : c'est un
`if` imbriqué dans le `else` du précédent — la première condition vraie « gagne », les suivantes
ne sont jamais évaluées.

**Piège — dangling else** : sans accolades, un `else` se rattache toujours au `if` le plus proche
qui n'en a pas déjà un, quelle que soit l'indentation visuelle. Mettre des accolades dès qu'un
`if` est imbriqué dans un autre.

---

## `switch` statement (JLS §14.11)

Forme *historique* de branchement à étiquettes (`case`), distincte du *switch expression* (Java 14,
`switch (x) -> ...`, `yield`) traité à part en `m11_modernite/c02_switch_expressions` — hors scope
ici. Chaque `case` est une étiquette dans un même bloc : sans `break`, l'exécution **tombe**
(fall-through) dans le `case` suivant, y compris son propre code, jusqu'au premier `break`
rencontré.

Le fall-through est parfois **volontaire** — regrouper plusieurs étiquettes sur un seul bloc
(`case 4: case 6: return 30;`) — et parfois un **piège** : un `break` oublié fait exécuter le
`case` suivant sans que sa condition ait jamais été testée.

Depuis **Java 7** (JLS §14.11, comparaison via `equals`), le `switch` accepte aussi les chaînes de
caractères, sans piège d'identité particulier.

---

## `while` (JLS §14.12) et `do-while` (JLS §14.13)

- **`while`** : condition testée **avant** chaque itération, y compris la première. Si elle est
  fausse dès le départ, le corps ne s'exécute jamais (0 itération possible).
- **`do-while`** : condition testée **après** le corps. Le corps s'exécute donc **toujours au
  moins une fois**, même si la condition est fausse dès le départ.

Le choix entre les deux dépend de la garantie voulue : `do-while` convient quand une valeur doit
être produite ou consommée au moins une fois avant de savoir si l'on continue (ex. compter les
chiffres d'un nombre — `0` a un chiffre, il faut au moins un passage dans le corps).

**Piège commun aux deux** : une condition qui ne devient jamais fausse boucle indéfiniment — la
variable testée doit être modifiée dans le corps.

---

## `for` classique (JLS §14.14.1)

`for (initialisation; condition; incrément) corps` regroupe en une seule ligne ce qu'un `while`
disperse. Les trois clauses sont indépendamment optionnelles (`for (;;)` est un `while (true)`).
Initialisation et incrément peuvent déclarer/modifier plusieurs variables, séparées par des
virgules.

La variable déclarée dans l'initialisation n'existe que dans la portée du `for` (voir portée de
bloc ci-dessous) — deux boucles `for` successives peuvent réutiliser le même nom sans conflit.

**Hors scope explicite** : le *for-each* (`for (T x : iterable)`, JLS §14.14.2) dépend de
`Iterable`/tableaux ; il est différé vers `m04_collections` (où `Iterable` prend son sens complet).

---

## `break` et `continue` (JLS §14.15, §14.16)

- **`break`** quitte immédiatement la boucle (ou le `switch`) qui le contient.
- **`continue`** saute directement à l'itération suivante, sans quitter la boucle — dans un `for`,
  il saute à la clause d'incrément ; dans un `while`/`do-while`, il saute directement au test de
  condition.

**Labels** (JLS §14.7 Labeled Statements) : `label: for (...) { for (...) { break label; } }` cible
une boucle englobante précise plutôt que la plus proche — utile pour sortir de plusieurs boucles
imbriquées d'un coup.

**Piège** : dans un `while`/`do-while`, si l'incrément de la variable de contrôle est placé
*après* le `continue` dans le corps, il est sauté à chaque fois que `continue` s'exécute — la
boucle peut alors ne jamais progresser. Placer l'incrément avant tout `continue` évite le piège.

---

## Portée de bloc (JLS §14.2 Blocks, §6.3 Scope of a Declaration)

Un bloc `{ ... }` délimite la durée de vie des variables locales qui y sont déclarées : une
variable n'existe qu'entre sa déclaration et l'accolade fermante du bloc qui la contient. Une
structure de contrôle ouvre implicitement un bloc pour son corps, même sans accolades visibles
autour d'une instruction unique (`if (x) instr;` équivaut à `if (x) { instr; }` du point de vue de
la portée).

- **Deux blocs frères** (l'un après l'autre, ni l'un dans l'autre) peuvent réutiliser le même nom
  de variable sans conflit : ils ne se voient pas.
- **Un bloc imbriqué**, en revanche, ne peut **pas** redéclarer un nom déjà visible dans un bloc
  englobant — Java refuse ce shadowing à la compilation (contrairement à des langages qui
  l'autorisent silencieusement).
- La variable de contrôle d'un `for` (`for (int i = ...)`) a la portée du `for` lui-même, pas de la
  méthode englobante.

---

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — à partir d'un `switch` avec des `break` manquants intentionnellement,
  prédire la sortie avant de l'exécuter. Teste la compréhension du fall-through, pas juste la
  syntaxe.
- **Application** — écrire une boucle `for` qui produit une pyramide de caractères (ou toute
  structure imbriquée simple). Teste la maîtrise des trois clauses et de l'imbrication de boucles.
- **Transfert/cas limite** — donné un `while` qui boucle infiniment (bug fourni), le corriger *sans*
  le réécrire en `for`/`do-while`. Teste la compréhension de *pourquoi* la condition ne devient
  jamais fausse, pas seulement la syntaxe des boucles.

## Pistes d'approfondissement (DeepDive)

- **Le `switch` statement face au `switch` expression (Java 14)** — pourquoi le fall-through
  implicite a été jugé assez risqué pour justifier une nouvelle syntaxe (`->`, exhaustivité,
  `yield`) plutôt que de corriger l'ancienne. Réellement optionnel : comprendre `switch` statement
  ne demande pas de connaître son remplaçant moderne.
- **Complexité algorithmique de `estPremier`** (exercice `Exo03`) — pourquoi `diviseur * diviseur
  <= n` suffit (pas besoin de tester au-delà de `√n`). Angle performance, non nécessaire pour
  comprendre `for`/`break` en eux-mêmes.
