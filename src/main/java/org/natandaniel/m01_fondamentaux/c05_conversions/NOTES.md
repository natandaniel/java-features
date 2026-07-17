# Conversions de types

> Module `m01_fondamentaux / c05_conversions`
> Leçons : `Ex01_Widening`, `Ex02_Narrowing`, `Ex03_PromotionExpressions`
> Exercices : `Exo01_Conversions`

---

## Widening (élargissant) — automatique

Chaîne : `byte → short → int → long → float → double` (JLS §5.1.2). Aucun cast requis. **Mais pas toujours sans perte** : un `long` (64 bits) converti en `float` (mantisse ~24 bits) peut perdre des chiffres significatifs — la conversion reste légale et silencieuse.

---

## Narrowing (rétrécissant) — cast explicite obligatoire

Sens inverse : Java exige un cast pour signaler le risque. `(byte) 300 = 44` (on ne garde que les 8 bits de poids faible). `(int) 3.99999 = 3` : la conversion `double → int` **tronque vers zéro**, elle n'arrondit pas.

---

## Promotion dans les expressions

Quand une expression mélange des types, le plus petit est promu vers le plus grand *avant* le calcul. `5 + 2.0` → `5.0 + 2.0` (type `double`). D'où la différence cruciale : `7 / 2 = 3` (deux `int`), mais `7 / 2.0 = 3.5` (un `double` en jeu).
