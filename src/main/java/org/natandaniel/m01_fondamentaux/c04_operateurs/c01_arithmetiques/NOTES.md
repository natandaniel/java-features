# Opérateurs arithmétiques

> Module `m01_fondamentaux / c04_operateurs / c01_arithmetiques`
> Leçons : `Ex01_Operations`, `Ex02_DivisionEtModulo`, `Ex03_Promotion`
> Exercices : `Exo01_DivisionPlancher`

Cinq opérateurs binaires (`+ - * / %`) et deux unaires (`+x`, `-x`) opèrent sur les types numériques (JLS §15.17-15.18). Addition, soustraction et multiplication n'ont pas de piège propre ; les subtilités sont la division entière, le modulo, et les opérateurs composés.

---

## Division entière et modulo

- `/` entre entiers **tronque vers zéro** (`-7 / 2 = -3`, pas `-4`) — JLS §15.17.2.
- `%` donne un reste du signe du **dividende** (`-7 % 2 = -1`).
- Invariant toujours vrai : `a == (a / b) * b + (a % b)`.
- Division entière par zéro → `ArithmeticException` (contrairement aux flottants).

Le modulo « mathématique » (signe du diviseur, résultat dans `[0, b[`) s'obtient avec `Math.floorMod` / `Math.floorDiv` — réimplémentés dans l'exercice.

---

## Opérateurs composés et incrément

Les **opérateurs composés** (`+=`, `*=`…) insèrent un cast implicite : `c += 20` équivaut à `c = (byte)(c + 20)` — ce qui peut **déborder silencieusement** (voir module `c05_debordement`).

**Pré vs post-incrément** : `a = p++` lit puis incrémente ; `d = ++q` incrémente puis lit. La différence ne compte que dans une expression.

> La **promotion de type** dans les expressions (`byte + byte → int`, `int + double → double`) est traitée en un seul endroit : c07_conversions.

---

## Pièges

- **Débordement silencieux** : `+ - *` débordent sans erreur sur les types entiers, pas seulement via un opérateur composé — détail et pièges classiques (dont l'unaire `-`) en c05_debordement.
- **Division/modulo par zéro** : lève `ArithmeticException` pour les entiers ; silencieux (`Infinity`/`NaN`) pour les flottants (c06_flottants).
