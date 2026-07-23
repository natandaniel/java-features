# Types flottants (IEEE 754)

> Module `m01_fondamentaux / c06_flottants`
> Leçons : `Ex01_Precision`, `Ex02_InfinityEtNaN`, `Ex03_TesterCorrectement`
> Exercices : `Exo01_Flottants`
> Prolonge c02_representation_binaire aux nombres à virgule : un format signe/exposant/mantisse, sans complément à deux.

IEEE 754 est le standard binaire que Java utilise pour `float` (32 bits) et `double` (64 bits) : un bit de signe, un exposant, une mantisse — pas une base 10, pas un simple entier décalé.

---

## Encodage

| Type     | Total   | Signe | Exposant | Mantisse | Biais |
|----------|---------|-------|----------|----------|-------|
| `float`  | 32 bits | 1     | 8        | 23       | 127   |
| `double` | 64 bits | 1     | 11       | 52       | 1023  |

Valeur = `(−1)^signe × 1.mantisse₂ × 2^(exposant − biais)` : le `1.` implicite devant la mantisse (nombre *normalisé*) gagne un bit de précision gratuit.

Le signe est un bit séparé, pas un complément à deux : il existe donc **deux zéros distincts**, `+0.0` et `−0.0` — à l'inverse de l'entier (un seul zéro, c02_representation_binaire).

Motifs d'exposant réservés, qui produisent les valeurs spéciales ci-dessous :
- exposant tout à 1, mantisse nulle → `Infinity` (signe selon le bit de signe) ;
- exposant tout à 1, mantisse non nulle → `NaN` ;
- exposant tout à 0 → zéro, ou nombre *dénormalisé* si la mantisse est non nulle (précision dégradée près de zéro).

---

## Précision

`float` ≈ 7 chiffres significatifs, `double` ≈ 15-16 — directement lié à la taille de la mantisse (23 bits ≈ 2²³ ≈ 8,4.10⁶ pas, soit ~7 chiffres décimaux ; 52 bits ≈ 4,5.10¹⁵, soit ~15-16 chiffres). **Utiliser `double` par défaut.**

Les flottants ne sont pas exacts en base 10 : `0.1 + 0.2 != 0.3`. Comme `1/3` en décimal, `0.1` et `0.2` n'ont pas d'écriture binaire finie ; ils sont arrondis au bit de mantisse le plus proche, d'où une erreur qui apparaît dès la première addition. Ne jamais comparer deux flottants calculés avec `==` — utiliser une tolérance `epsilon`.

---

## Trois valeurs spéciales

- **`Infinity` / `-Infinity`** — produites par la division flottante par zéro (pas d'exception, contrairement aux entiers) ou l'overflow. `Infinity + 1 = Infinity` ; `Infinity - Infinity = NaN`.
- **`NaN`** (Not-a-Number) — forme indéterminée (`0.0 / 0.0`). Propriété fondamentale IEEE 754 : **NaN n'est égal à rien, pas même à lui-même** (`nan == nan` est `false`). C'est le seul cas où `x != x`. NaN est *contagieux* : toute opération avec NaN donne NaN.

**Piège du zéro signé** : `0.0 == -0.0` vaut `true`, mais ils ne se comportent pas identiquement : `1.0 / 0.0 = Infinity` alors que `1.0 / -0.0 = -Infinity`.

---

## Tester correctement

- `Double.isNaN(x)`, `Double.isInfinite(x)`, `Double.isFinite(x)` (@since Java 8).
- **Jamais** `x == Double.NaN` (toujours `false`).
- L'exercice exploite l'astuce `x != x` pour détecter NaN sans la bibliothèque.
