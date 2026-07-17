# Opérateurs de décalage

> Module `m01_fondamentaux / c03_operateurs / c03_decalages`
> Leçons : `Ex01_DecalageGauche`, `Ex02_DecalageDroitSigne`, `Ex03_DecalageDroitNonSigne`, `Ex04_ApplicationsPratiques`
> Exercices : `Exo01_Decalages`, `Exo02_CouleursRGB`

---

## Les trois opérateurs

| Opérateur | Nom | Comportement |
|-----------|-----|--------------|
| `<<`  | décalage gauche | glisse à gauche, insère des 0 à droite ; `x << k` = `x × 2^k` |
| `>>`  | décalage droit **signé** | glisse à droite, **réplique le bit de signe** (extension de signe) |
| `>>>` | décalage droit **non signé** | glisse à droite, insère **toujours des 0** |

`>>` et `>>>` ne diffèrent que sur les nombres négatifs. Java n'ayant pas d'entier non signé, `>>>` sert à traiter les bits comme une quantité non signée (ex. `-1 >>> 1 = Integer.MAX_VALUE`).

---

## Pièges

- **Overflow à gauche** : un bit non nul qui « sort » est perdu (`<<` peut changer le signe).
- **`>>` n'est pas exactement `/2`** sur les négatifs impairs : il arrondit vers −∞ (`-1 >> 1 = -1`, alors que `-1 / 2 = 0`).

---

## Applications (exercices)

- **Multiplier/diviser** par une puissance de 2 (`<<`, `>>`).
- **Tester une puissance de 2** : `n > 0 && (n & (n-1)) == 0`.
- **Extraire un octet** : `(valeur >>> index*8) & 0xFF` — le `>>>` évite l'extension de signe.
- **Empaqueter une couleur RVB** dans un `int` (`0x00RRGGBB`) puis la redécomposer.
