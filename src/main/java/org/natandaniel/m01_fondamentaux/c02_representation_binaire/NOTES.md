# Représentation binaire et complément à deux

> Module `m01_fondamentaux / c02_representation_binaire`
> Leçons : `lecon/Ex01_RepresentationBinaire`
> Exercices : `exercices/Exo01_RepresentationBinaire` (solution + test miroirs)
> Prérequis de c04_operateurs (bit à bit/décalages), c05_debordement et c07_conversions.

Comment les entiers sont réellement stockés en mémoire : binaire non signé, puis complément à deux pour le signe.

---

## Entiers non signés

Chaque bit occupe une position de poids `2ⁱ` (i = 0 pour le bit le plus faible). La valeur est la **somme des bits à 1**. Sur un octet : poids `128 64 32 16 8 4 2 1`, donc `42 = 32 + 8 + 2 = 0010 1010`. Sur *n* bits non signés, le domaine est `0` à `2ⁿ − 1`.

La **méthode de Horner** reconstruit cette valeur sans opérateur de bits : `valeur = valeur * 2 + bit`, bit après bit du poids fort vers le poids faible (implémentée dans `valeurNonSignee`, `exercices/Exo01_RepresentationBinaire`).

## Complément à deux

Java signe ses entiers (`byte`, `short`, `int`, `long`) avec le **complément à deux** : le bit de poids fort (MSB) porte un poids **négatif**, `−2ⁿ⁻¹`, au lieu de `+2ⁿ⁻¹`.

```
valeur = −bit(n−1)·2ⁿ⁻¹ + Σ bit(i)·2ⁱ   (i de 0 à n−2)
```

Exemple sur 8 bits, `−1` = `1111 1111` : `−128 + 64+32+16+8+4+2+1 = −1`.

**Recette pratique** pour obtenir `−n` : inverser tous les bits de `n` (NOT), puis ajouter 1. Elle découle d'une identité arithmétique : sur *n* bits, `NON(n) = 2ⁿ − 1 − n` (inverser chaque bit `b` en `1−b` revient à soustraire `n` de `2ⁿ − 1`), donc `NON(n) + 1 = 2ⁿ − n`, qui est exactement la représentation de `−n` modulo `2ⁿ`.

Le bit de poids fort reste le **bit de signe** : `0` = positif ou nul, `1` = négatif.

## Pourquoi le complément à deux

L'addition et la soustraction utilisent le **même circuit binaire**, que le résultat soit ensuite interprété comme signé ou non signé : le motif de bits produit est identique modulo `2ⁿ`, seule l'interprétation diffère. Une représentation en signe-magnitude (bit de signe séparé du reste) n'a pas cette propriété — il faut traiter le signe à part — et impose en plus deux zéros (`+0` et `−0`). Le complément à deux n'a qu'un seul zéro.

## Domaine et bornes

Sur *n* bits signés, le domaine est `−2ⁿ⁻¹` à `2ⁿ⁻¹ − 1` : asymétrique, parce que le zéro occupe une valeur du côté positif. Les bornes concrètes par type (`byte`, `short`, `int`, `long`) sont tabulées dans `c01_types_primitifs`.

## Compter / inspecter les bits

`Integer.bitCount(n)` compte les bits à 1 ; `Integer.toBinaryString(n)` donne l'écriture binaire du motif de bits, y compris pour un `n` négatif.

## Pièges

- **Asymétrie des bornes** : `Integer.MIN_VALUE` n'a pas d'opposé représentable — `−Integer.MIN_VALUE` déborde et redonne `Integer.MIN_VALUE` (de même `Math.abs(Integer.MIN_VALUE) == Integer.MIN_VALUE`).
- **`toBinaryString` sur un négatif** n'affiche pas de signe `-` : `Integer.toBinaryString(-1)` donne 32 caractères `1`, pas `"-1"`.
- **`~n` n'est pas `−n`** mais `−(n + 1)` : NOT seul, sans le `+1` de la recette du complément à deux.

## Exemple

```java
Integer.toBinaryString(42);   // "101010"
Integer.toBinaryString(-1);   // 32 fois "1" -- motif de bits, pas un signe "-"
~42 + 1;                      // == -42 : NOT puis +1, la recette du complément à deux
Integer.bitCount(-1);         // 32
```
