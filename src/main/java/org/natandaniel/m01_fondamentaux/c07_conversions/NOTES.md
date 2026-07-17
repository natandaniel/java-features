# Conversions de types

> Module `m01_fondamentaux / c07_conversions`
> Leçons : `Ex01_Widening`, `Ex02_Narrowing`, `Ex03_PromotionExpressions`
> Exercices : `Exo01_Conversions`, `Exo02_Caracteres`
> Vient **après** c06_flottants : le widening et le narrowing mettent en jeu `float`/`double`.

---

## Widening (élargissant) — automatique

Chaîne : `byte → short → int → long → float → double` (JLS §5.1.2). Aucun cast requis. **Mais pas toujours sans perte** : un `long` (64 bits) converti en `float` (mantisse ~24 bits) peut perdre des chiffres significatifs — la conversion reste légale et silencieuse.

---

## Narrowing (rétrécissant) — cast explicite obligatoire

Sens inverse : Java exige un cast pour signaler le risque. `(byte) 300 = 44` (on ne garde que les 8 bits de poids faible). `(int) 3.99999 = 3` : la conversion `double → int` **tronque vers zéro**, elle n'arrondit pas.

---

## Promotion dans les expressions

Deux niveaux, réunis ici (c'est l'unique endroit qui traite la promotion) :

1. **Petits types entiers** : `byte`, `short`, `char` sont d'abord promus en `int`. D'où `byte + byte` de type `int`, et `byte z = x + y;` qui ne compile pas sans cast.
2. **Types mixtes** : le plus petit est ensuite promu vers le plus grand *avant* le calcul. `5 + 2.0` → `5.0 + 2.0` (type `double`).

D'où la différence cruciale : `7 / 2 = 3` (deux `int`), mais `7 / 2.0 = 3.5` (un `double` en jeu).

Le `char` illustre ces règles (arithmétique et casts `char ↔ int`) : exercice `Exo02_Caracteres`.
