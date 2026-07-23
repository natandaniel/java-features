# Conversions de types

> Module `m01_fondamentaux / c07_conversions`
> Leçons : `Ex01_Widening`, `Ex02_Narrowing`, `Ex03_PromotionExpressions`
> Exercices : `Exo01_Conversions`, `Exo02_Caracteres`
> Vient **après** c06_flottants : le widening et le narrowing mettent en jeu `float`/`double`.

---

## Widening (élargissant) — automatique

Chaîne : `byte → short → int → long → float → double` (JLS §5.1.2). Aucun cast requis. **Mais pas toujours sans perte** : un `long` (64 bits) converti en `float` (mantisse ~24 bits) peut perdre des chiffres significatifs — la conversion reste légale et silencieuse.

`char` entre dans une chaîne **séparée** : `char → int → long → float → double`. Il ne widen ni vers `byte`, ni vers `short`, ni depuis eux : `char` est non signé, `byte`/`short` sont signés, aucune des deux directions n'est sans ambiguïté de signe. Passer de l'un à l'autre exige donc toujours un cast explicite (narrowing), même si les deux tiennent sur 16 bits.

---

## Narrowing (rétrécissant) — cast explicite obligatoire

Sens inverse : Java exige un cast pour signaler le risque. `(byte) 300 = 44` (on ne garde que les 8 bits de poids faible). `(int) 3.99999 = 3` : la conversion `double → int` **tronque vers zéro**, elle n'arrondit pas.

**Exception — constante littérale (JLS §5.2)** : `byte b = 127;` compile sans cast, car `127` est une expression constante qui tient dans le domaine de `byte` (détail en c03_litteraux). Dès que la source est une variable, le cast redevient obligatoire.

**Piège — flottant vers entier hors domaine (JLS §5.1.3)** : le narrowing `double`/`float → int`/`long` ne boucle pas comme le ferait un narrowing entier. Il **sature** aux bornes du type cible : `(int) Double.POSITIVE_INFINITY == Integer.MAX_VALUE`, `(int) Double.NEGATIVE_INFINITY == Integer.MIN_VALUE`, `(int) Double.NaN == 0`.

---

## Promotion dans les expressions (JLS §5.6)

Deux niveaux, réunis ici (c'est l'unique endroit qui traite la promotion) :

1. **Promotion unaire (§5.6.1)** — `byte`, `short`, `char` sont d'abord promus en `int`. D'où `byte + byte` de type `int`, et `byte z = x + y;` qui ne compile pas sans cast.
2. **Promotion binaire (§5.6.2)** — le plus petit type est ensuite promu vers le plus grand *avant* le calcul. `5 + 2.0` → `5.0 + 2.0` (type `double`).

D'où la différence cruciale : `7 / 2 = 3` (deux `int`), mais `7 / 2.0 = 3.5` (un `double` en jeu).

Le `char` illustre ces règles (arithmétique et casts `char ↔ int`) : exercice `Exo02_Caracteres`.
