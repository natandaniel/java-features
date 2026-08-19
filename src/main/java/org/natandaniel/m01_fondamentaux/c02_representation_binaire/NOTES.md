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

## Pourquoi la borne vaut 2ⁿ − 1

La valeur maximale sur *n* bits est atteinte quand tous les bits valent 1, soit la somme de tous les poids. L'identité à démontrer :

```
Σ 2ⁱ = 2ⁿ − 1        (i de 0 à n−1)
```

Notons `S(n) = 1 + 2 + 4 + … + 2ⁿ⁻¹`.

**Par récurrence.** Base `n = 0` : la somme est vide, `S(0) = 0 = 2⁰ − 1`. Hérédité : si `S(n) = 2ⁿ − 1`, alors `S(n+1) = S(n) + 2ⁿ = (2ⁿ − 1) + 2ⁿ = 2·2ⁿ − 1 = 2ⁿ⁺¹ − 1`.

**Par doublement.** `2S` est la même somme décalée d'un rang : `2S = 2 + 4 + … + 2ⁿ`. La soustraction annule tous les termes intermédiaires : `2S − S = 2ⁿ − 1`, donc `S = 2ⁿ − 1`. C'est le cas `q = 2` de la somme géométrique `(qⁿ − 1)/(q − 1)`.

**Par télescopage.** Chaque terme s'écrit `2ⁱ = 2ⁱ⁺¹ − 2ⁱ`. La somme `(2¹ − 2⁰) + (2² − 2¹) + … + (2ⁿ − 2ⁿ⁻¹)` ne laisse que les extrémités : `2ⁿ − 2⁰`.

**Par la retenue binaire** — la lecture à retenir. `S(n)` est le motif de *n* bits tous à 1. Ajouter 1 propage la retenue jusqu'au bout et produit `1` suivi de *n* zéros, soit `2ⁿ` ; donc `S(n) + 1 = 2ⁿ`. C'est exactement le débordement de `c05_debordement` vu à l'endroit.

```
n = 4 :   1111 = 8+4+2+1 = 15 = 2⁴ − 1
        + 0001
        = 10000 = 16 = 2⁴
```

L'identité est partout dans le code : `Integer.MAX_VALUE == (1 << 31) − 1` (31 bits de magnitude), `(1 << n) − 1` comme masque des *n* bits de poids faible, `0xFF == (1 << 8) − 1 == 255` pour un octet plein.

## Complément à deux

Java signe ses entiers (`byte`, `short`, `int`, `long`) avec le **complément à deux** : le bit de poids fort (MSB) porte un poids **négatif**, `−2ⁿ⁻¹`, au lieu de `+2ⁿ⁻¹`.

```
valeur = −bit(n−1)·2ⁿ⁻¹ + Σ bit(i)·2ⁱ   (i de 0 à n−2)
```

Exemple sur 8 bits, `−1` = `1111 1111` : `−128 + 64+32+16+8+4+2+1 = −1`.

**Recette pratique** pour obtenir `−n` : inverser tous les bits de `n` (NOT), puis ajouter 1. Elle découle d'une identité arithmétique : sur *n* bits, `NON(n) = 2ⁿ − 1 − n` (inverser chaque bit `b` en `1−b` revient à soustraire `n` de `2ⁿ − 1`), donc `NON(n) + 1 = 2ⁿ − n`, qui est exactement la représentation de `−n` modulo `2ⁿ`.

Le bit de poids fort reste le **bit de signe** : `0` = positif ou nul, `1` = négatif.

**Sur le nom.** *Complément à deux* désigne la technique du complément — soustraire en additionnant `2ⁿ − x` — appliquée en base deux ; les machines décimales antérieures à l'informatique binaire employaient de même le complément à dix et le complément à neuf. L'appellation est historique et n'a aucun contenu opératoire : tout le comportement se lit dans la formule ci-dessus. La JLS emploie le terme (`§4.2`) sans jamais le définir.

## Pourquoi le complément à deux

L'addition et la soustraction utilisent le **même circuit binaire**, que le résultat soit ensuite interprété comme signé ou non signé : le motif de bits produit est identique modulo `2ⁿ`, seule l'interprétation diffère. Une représentation en signe-magnitude (bit de signe séparé du reste) n'a pas cette propriété — il faut traiter le signe à part — et impose en plus deux zéros (`+0` et `−0`). Le complément à deux n'a qu'un seul zéro.

## Domaine et bornes

Sur *n* bits signés, le domaine est `−2ⁿ⁻¹` à `2ⁿ⁻¹ − 1`. Les `2ⁿ` motifs se partagent en `2ⁿ⁻¹` négatifs (MSB à 1) et `2ⁿ⁻¹` positifs ou nuls (MSB à 0). L'appariement `x ↔ −x` sur ce domaine se répartit en trois cas :

- **Appariés** — `1` … `2ⁿ⁻¹ − 1` et leurs opposés `−1` … `−(2ⁿ⁻¹ − 1)`.
- **Son propre opposé** — `0`. Il n'existe qu'un seul zéro, `000…0` ; `−0` n'est pas un motif distinct, contrairement au signe-magnitude.
- **Orphelin** — `100…0` = `−2ⁿ⁻¹`, sans opposé représentable. C'est cet orphelin qui rend le domaine asymétrique : le zéro consomme une place du côté positif.

La recette `~x + 1` trahit l'orphelin en revenant sur lui-même. Sur 4 bits : `~1000 = 0111`, puis `0111 + 1 = 1000`.

Les bornes concrètes par type (`byte`, `short`, `int`, `long`) sont tabulées dans `c01_types_primitifs`.

## Compter / inspecter les bits

`Integer.bitCount(n)` compte les bits à 1 ; `Integer.toBinaryString(n)` donne l'écriture binaire du motif de bits, y compris pour un `n` négatif.

## Pièges

- **Asymétrie des bornes** : `Integer.MIN_VALUE` est l'orphelin du domaine — `−Integer.MIN_VALUE` déborde et redonne `Integer.MIN_VALUE` (de même `Math.abs(Integer.MIN_VALUE) == Integer.MIN_VALUE`).
- **`toBinaryString` sur un négatif** n'affiche pas de signe `-` : `Integer.toBinaryString(-1)` donne 32 caractères `1`, pas `"-1"`.
- **`~n` n'est pas `−n`** mais `−(n + 1)` : NOT seul, sans le `+1` de la recette du complément à deux.

## Exemple

```java
Integer.toBinaryString(42);   // "101010"
Integer.toBinaryString(-1);   // 32 fois "1" -- motif de bits, pas un signe "-"
~42 + 1;                      // == -42 : NOT puis +1, la recette du complément à deux
Integer.bitCount(-1);         // 32
```
