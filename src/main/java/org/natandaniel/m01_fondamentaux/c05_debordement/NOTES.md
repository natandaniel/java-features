# Débordement entier (overflow)

> Module `m01_fondamentaux / c05_debordement`
> Leçons : `Ex01_ComportementSilencieux`, `Ex02_PiegesClassiques`, `Ex03_ArithmetiqueSure`
> Exercices : `Exo01_ArithmetiqueSure`
> Repose sur c02_representation_binaire (complément à deux).

---

## L'overflow est silencieux (JLS §4.2.1)

> *« An integer operation that overflows produces a result that is the low-order bits of the mathematical result represented in the two's-complement format. »*

Aucune exception : `Integer.MAX_VALUE + 1 == Integer.MIN_VALUE`. Le calcul se fait modulo `2ⁿ` (n = 32 pour un `int`) : le bit de signe bascule et l'entier « boucle » d'une borne à l'autre — le mécanisme déjà vu en c02_representation_binaire, ici comme conséquence plutôt que comme définition. Le phénomène est identique sur `byte`, `short`, `int` et `long` : seule change la taille *n*, donc le point où ça boucle.

---

## Pièges classiques

1. **Cast tardif** — `(long)(i * i)` déborde en `int` *avant* le cast. Caster *avant* : `(long)i * i`.
2. **`byte b = 127; b++`** — `b++` ≡ `b = (byte)(b + 1)` → −128 (overflow silencieux).
3. **Constantes calculées en `int`** — `400 * 365 * 24 * 60 * 60` est évalué en `int` par le compilateur. Forcer `long` dès le premier opérande : `400L * …`.
4. **`Math.abs(Integer.MIN_VALUE)`** — reste négatif (`== Integer.MIN_VALUE`) : cette seule valeur n'a pas d'opposé représentable, donc `Math.abs` comme l'unaire `-` débordent dessus (asymétrie des bornes, c02_representation_binaire). Bug classique quand `Math.abs` sert à garantir un résultat positif.

---

## Se protéger

- **`Math.addExact` / `subtractExact` / `multiplyExact`** — mêmes opérations, mais lèvent `ArithmeticException` au lieu de déborder.
- **`Math.toIntExact(long)`** — cast `long → int` qui lève plutôt que de tronquer.
- **`BigInteger`** — entiers de taille arbitraire (exacts, mais plus lents).

L'exercice réimplémente la détection : un calcul intermédiaire en `long` ne peut pas déborder pour des opérandes `int`, on compare ensuite aux bornes.
