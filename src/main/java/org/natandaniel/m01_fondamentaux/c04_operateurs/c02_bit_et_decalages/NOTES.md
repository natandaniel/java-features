# Opérateurs bit à bit et décalages

> Module `m01_fondamentaux / c04_operateurs / c02_bit_et_decalages`
> Leçons : `Ex01_AndOrXorNot`, `Ex02_Masques`, `Ex03_DecalageGauche`, `Ex04_DecalageDroitSigne`, `Ex05_DecalageDroitNonSigne`, `Ex06_ApplicationsPratiques`
> Exercices : `Exo01_Masques`, `Exo02_Decalages`, `Exo03_CouleursRGB`, `Exo04_CompterBits`
> Prérequis : la **représentation binaire** et le **complément à deux** (c02_representation_binaire).

Bits (`& | ^ ~`) et décalages (`<< >> >>>`) forment un même bloc : les masques s'écrivent avec des décalages, et les décalages se lisent en binaire. On les apprend donc ensemble, une fois la représentation binaire acquise.

---

## Les quatre opérateurs bit à bit

Ils agissent sur chaque bit indépendamment :

| Opérateur | Règle | Usage typique |
|-----------|-------|---------------|
| `&` AND   | 1 si les deux bits sont à 1 | tester, effacer des bits |
| `\|` OR   | 1 si au moins un bit à 1 | activer des bits |
| `^` XOR   | 1 si les bits diffèrent | basculer (toggle) |
| `~` NOT   | inverse tous les bits | `~n = -(n+1)` en complément à deux |

## Le pattern des masques

Un masque isole un (ou plusieurs) bit(s). Avec `MASQUE = 1 << position` :

- **activer** : `flags | MASQUE`
- **désactiver** : `flags & ~MASQUE`
- **basculer** : `flags ^ MASQUE`
- **tester** : `(flags & MASQUE) != 0`

C'est la base du stockage compact de drapeaux booléens dans un seul entier (jusqu'à 32 flags dans un `int`).

---

## Les trois opérateurs de décalage

| Opérateur | Nom | Comportement |
|-----------|-----|--------------|
| `<<`  | décalage gauche | glisse à gauche, insère des 0 à droite ; `x << k` = `x × 2^k` |
| `>>`  | décalage droit **signé** | glisse à droite, **réplique le bit de signe** (extension de signe) |
| `>>>` | décalage droit **non signé** | glisse à droite, insère **toujours des 0** |

`>>` et `>>>` ne diffèrent que sur les nombres négatifs. Java n'ayant pas d'entier non signé, `>>>` sert à traiter les bits comme une quantité non signée (ex. `-1 >>> 1 = Integer.MAX_VALUE`).

### Pièges

- **Overflow à gauche** : un bit non nul qui « sort » est perdu (`<<` peut changer le signe).
- **`>>` n'est pas exactement `/2`** sur les négatifs impairs : il arrondit vers −∞ (`-1 >> 1 = -1`, alors que `-1 / 2 = 0`).
- **`&` et `|` sur des `boolean`** : ce sont aussi des opérateurs logiques valides (JLS §15.22.2), mais **sans court-circuit** — les deux opérandes sont toujours évalués. Ne pas les confondre avec `&&`/`||`, qui court-circuitent.

---

## Applications (exercices)

- **Multiplier/diviser** par une puissance de 2 (`<<`, `>>`).
- **Tester une puissance de 2** : `n > 0 && (n & (n-1)) == 0`.
- **Extraire un octet** : `(valeur >>> index*8) & 0xFF` — le `>>>` évite l'extension de signe.
- **Empaqueter une couleur RVB** dans un `int` (`0x00RRGGBB`) puis la redécomposer.
- **Compter les bits à 1** : réimplémenter `Integer.bitCount` avec `(n >>> i) & 1` sur les 32 positions (`Exo04_CompterBits`).
