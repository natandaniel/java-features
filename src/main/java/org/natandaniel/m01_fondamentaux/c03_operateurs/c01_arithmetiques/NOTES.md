# Opérateurs arithmétiques

> Module `m01_fondamentaux / c03_operateurs / c01_arithmetiques`
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

## Promotion numérique (JLS §5.6.2)

Avant tout calcul entier, les opérandes plus petits que `int` (`byte`, `short`, `char`) sont **promus en `int`**. D'où `byte + byte` de type `int`, et `byte z = x + y;` qui ne compile pas sans cast.

Les **opérateurs composés** (`+=`, `*=`…) insèrent un cast implicite : `c += 20` équivaut à `c = (byte)(c + 20)` — ce qui peut **déborder silencieusement** (voir module `c04_debordement`).

**Pré vs post-incrément** : `a = p++` lit puis incrémente ; `d = ++q` incrémente puis lit. La différence ne compte que dans une expression.
