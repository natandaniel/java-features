# Opérateurs arithmétiques

> Module `m01_fondamentaux / c04_operateurs / c01_arithmetiques`
> Leçons : `Ex01_Operations`, `Ex02_DivisionEtModulo`, `Ex03_Promotion`
> Exercices : `Exo01_DivisionPlancher`

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
