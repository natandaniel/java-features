# Types flottants (IEEE 754)

> Module `m01_fondamentaux / c06_flottants`
> Leçons : `Ex01_Precision`, `Ex02_InfinityEtNaN`, `Ex03_TesterCorrectement`
> Exercices : `Exo01_Flottants`

---

## Précision

`float` ≈ 7 chiffres significatifs, `double` ≈ 15-16. **Utiliser `double` par défaut.** Les flottants ne sont pas exacts en base 10 : `0.1 + 0.2 != 0.3`. Ne jamais comparer deux flottants calculés avec `==` — utiliser une tolérance `epsilon`.

---

## Trois valeurs spéciales

- **`Infinity` / `-Infinity`** — produites par la division flottante par zéro (pas d'exception, contrairement aux entiers) ou l'overflow. `Infinity + 1 = Infinity` ; `Infinity - Infinity = NaN`.
- **`NaN`** (Not-a-Number) — forme indéterminée (`0.0 / 0.0`). Propriété fondamentale IEEE 754 : **NaN n'est égal à rien, pas même à lui-même** (`nan == nan` est `false`). C'est le seul cas où `x != x`. NaN est *contagieux* : toute opération avec NaN donne NaN.

---

## Tester correctement

- `Double.isNaN(x)`, `Double.isInfinite(x)`, `Double.isFinite(x)` (@since Java 8).
- **Jamais** `x == Double.NaN` (toujours `false`).
- L'exercice exploite l'astuce `x != x` pour détecter NaN sans la bibliothèque.
