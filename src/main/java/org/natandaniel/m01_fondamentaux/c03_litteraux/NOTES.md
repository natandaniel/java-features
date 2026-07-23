# Littéraux

> Module `m01_fondamentaux / c03_litteraux`
> Leçons : `lecon/Ex01_Notations`, `Ex02_BinaireEtUnderscores` (@since Java 7), `Ex03_SuffixesEtChar`
> Exercices : `exercices/Exo01_Notations`
> Après c01 (quels types existent) et c02 (comment ils sont stockés) : reste à savoir comment écrire leurs valeurs.

Un littéral est la **forme écrite** d'une valeur directement dans le code source — sa notation, indépendante de son stockage en mémoire.

---

## Bases de numération

| Base | Préfixe | Exemple      | @since |
|------|---------|--------------|--------|
| 10   | *(aucun)* | `255`      | 1.0    |
| 16   | `0x`    | `0xFF`       | 1.0    |
| 8    | `0`     | `0377`       | 1.0    |
| 2    | `0b`    | `0b11111111` | 7      |

Toutes ces écritures désignent la **même valeur** — seule la notation change. Un chiffre hexadécimal encode exactement 4 bits (un *quartet*), d'où la conversion immédiate hex ↔ binaire.

**Piège octal** : un `0` en tête change la base (`0377` ≠ `377`, `0377` vaut 255 en décimal).

---

## Améliorations Java 7 (JLS §3.10.1)

- **Littéraux binaires `0b`/`0B`** — symétriques du `0x`. Rendent les masques de bits lisibles sans calcul mental (`0b0000_1000` = le bit 3, visible directement).
- **Underscores `_`** — ignorés par le compilateur, ils groupent les chiffres (`1_000_000`, `0xFF_EC_D1`). Interdits :
  - en tête ou en fin de littéral ;
  - juste après un préfixe (`0b_1010`, `0x_FF` invalides) ;
  - juste avant un suffixe de type (`100_L` invalide) ;
  - collés au point décimal (`3._14`, `3.14_` invalides).

---

## Suffixes de type

| Suffixe   | Type forcé | Obligatoire quand |
|-----------|-----------|--------------------|
| `L` / `l` | `long`    | la valeur dépasse `Integer.MAX_VALUE` |
| `f` / `F` | `float`   | toujours — sinon un littéral à virgule est un `double` |
| `d` / `D` | `double`  | jamais — c'est déjà le type par défaut d'un littéral à virgule |
| *(aucun)* | `int` (entier) ou `double` (à virgule) | — |

Aucun suffixe n'existe pour `byte` ou `short` : on écrit un littéral `int` et on l'assigne (voir piège ci-dessous), ou on convertit explicitement (c07_conversions).

**Piège** : préférer `L` majuscule à `l` minuscule — `100l` se lit à l'écran comme `1001`.

**Piège — narrowing sur constante (JLS §5.2)** : `byte b = 127;` compile sans cast, alors qu'un `byte` ne stocke pas un `int` — le compilateur reconnaît que `127` est une **expression constante** représentable dans le domaine de `byte`, et la réduit à la compilation. `byte b = 128;` ne compile pas (128 dépasse 127) : il faut alors un cast explicite, qui tronque silencieusement à l'exécution (c07_conversions). Cette réduction ne s'applique **qu'aux constantes**, jamais à une variable : `int i = 127; byte b = i;` ne compile pas.

---

## `char` comme entier

Un littéral `char` (`'A'`) désigne un **entier non signé de 16 bits** : le code Unicode du caractère (`'A'` = 65). Il s'écrit aussi par échappement :
- séquences usuelles : `'\n'`, `'\t'`, `'\\'`, `'\''`, `'\"'` ;
- échappement Unicode, forme `\u` suivi de 4 chiffres hexadécimaux du code point — `'A'` (code `0x41` = 65) s'écrit `'\u0041'` (JLS §3.3). Traduit avant l'analyse du code source, donc utilisable en principe n'importe où, même hors littéral `char`.

C'est encore de la *notation*. Son **arithmétique** en revanche (promotion `'A' + 1 → int`, cast `(char)(...)`, écart `'a' - 'A' = 32`) met en jeu opérateurs et conversions : elle est traitée en c07_conversions (exercice `Exo02_Caracteres`).
