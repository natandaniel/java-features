# Représentation binaire et complément à deux

> Module `m01_fondamentaux / c02_representation_binaire`
> Leçons : `lecon/Ex01_RepresentationBinaire`
> Exercices : `exercices/Exo01_RepresentationBinaire` (solution + test miroirs)

Comment les entiers sont réellement stockés en mémoire. On pose cette notion juste après les types et avant les opérateurs : c'est le prérequis des opérations bit à bit et des décalages (c04_operateurs), du débordement (c05_debordement) et des conversions (c07_conversions).

---

## Entiers positifs

Chaque bit vaut une puissance de 2 ; la valeur est la **somme des bits à 1**. Sur un octet : `128 64 32 16 8 4 2 1`. Ainsi `42 = 32 + 8 + 2 = 0010 1010`. L'exercice reconstruit cette valeur par la **méthode de Horner** (`valeur = valeur * 2 + bit`) — de l'arithmétique pure, sans opérateur de bits.

## Entiers négatifs : le complément à deux

Pour obtenir `−n` : **inverser tous les bits** de `n`, puis **ajouter 1**. C'est pourquoi `−1` a ses 32 bits à 1. Le **bit de poids fort** est le bit de signe : `0` = positif, `1` = négatif.

Conséquences directes, exploitées dans les modules suivants :
- un seul zéro (pas de `−0`), et une asymétrie des bornes (`Integer.MIN_VALUE` n'a pas d'opposé représentable) ;
- l'addition/soustraction fonctionne sans traiter le signe à part — d'où l'overflow silencieux (c05_debordement) ;
- `~n = −(n + 1)` relie le NOT bit à bit à l'arithmétique (c04_operateurs).

## Compter / inspecter les bits

`Integer.bitCount(n)` compte les bits à 1 ; `Integer.toBinaryString(n)` en donne l'écriture. Réimplémenter `bitCount` **avec des opérateurs de bits** (`>>>`, `& 1`) est l'objet de `Exo04_CompterBits`, placé en c04_operateurs — car il suppose les décalages.
