# Opérateurs bit à bit

> Module `m01_fondamentaux / c03_operateurs / c02_bit_a_bit`
> Leçons : `Ex01_AndOrXorNot`, `Ex02_Masques`
> Exercices : `Exo01_Masques`

---

## Les quatre opérateurs

Ils agissent sur chaque bit indépendamment :

| Opérateur | Règle | Usage typique |
|-----------|-------|---------------|
| `&` AND   | 1 si les deux bits sont à 1 | tester, effacer des bits |
| `\|` OR   | 1 si au moins un bit à 1 | activer des bits |
| `^` XOR   | 1 si les bits diffèrent | basculer (toggle) |
| `~` NOT   | inverse tous les bits | `~n = -(n+1)` en complément à deux |

---

## Le pattern des masques

Un masque isole un (ou plusieurs) bit(s). Avec `MASQUE = 1 << position` :

- **activer** : `flags | MASQUE`
- **désactiver** : `flags & ~MASQUE`
- **basculer** : `flags ^ MASQUE`
- **tester** : `(flags & MASQUE) != 0`

C'est la base du stockage compact de drapeaux booléens dans un seul entier (jusqu'à 32 flags dans un `int`).
