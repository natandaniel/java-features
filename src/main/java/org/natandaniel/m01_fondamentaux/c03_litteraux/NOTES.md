# Littéraux

> Module `m01_fondamentaux / c03_litteraux`
> Leçons : `lecon/Ex01_Notations`, `Ex02_BinaireEtUnderscores` (@since Java 7), `Ex03_SuffixesEtChar`
> Exercices : `exercices/Exo01_Notations`

Un littéral est la **forme écrite** d'une valeur dans le code. Il vient juste après les types : on apprend quels types existent, puis comment écrire leurs valeurs, avant de les manipuler.

---

## Bases de numération

| Base | Préfixe | Exemple   | @since |
|------|---------|-----------|--------|
| 10   | *(aucun)* | `255`    | 1.0    |
| 16   | `0x`    | `0xFF`    | 1.0    |
| 8    | `0`     | `0377`    | 1.0    |
| 2    | `0b`    | `0b11111111` | Java 7 |

Toutes ces écritures désignent la même valeur. **Piège octal** : un `0` en tête change la base (`0377` ≠ `377`).

Un chiffre hexadécimal encode exactement 4 bits (un *quartet*), d'où la conversion immédiate hex ↔ binaire.

---

## Améliorations Java 7 (JLS §3.10.1)

- **Littéraux binaires `0b`** — symétriques du `0x`. Rendent les masques de bits lisibles sans calcul mental.
- **Underscores `_`** — ignorés par le compilateur, ils groupent les chiffres (`1_000_000`, `0xFF_EC_D1`). Interdits en tête/fin du littéral et collés au point décimal.

---

## Suffixes de type

- `L` pour `long` — obligatoire dès que la valeur dépasse `Integer.MAX_VALUE`.
- `f` pour `float` — obligatoire, sinon le littéral décimal est un `double`.
- Pas de suffixe → un littéral entier est `int`, un littéral décimal est `double`.

---

## `char` comme entier

Un littéral `char` (`'A'`) désigne un **entier non signé de 16 bits** : le code Unicode du caractère (`'A'` = 65). C'est encore de la *notation*.

Son **arithmétique** en revanche (promotion `'A' + 1 → int`, cast `(char)(...)`, écart `'a' - 'A' = 32`) met en jeu opérateurs et conversions : elle est traitée en c07_conversions (exercice `Exo02_Caracteres`).
