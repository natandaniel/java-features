# Variables

> Module `m01_fondamentaux / c08_variables`
> Leçons : `Ex01_KindsOfVariables`, `Ex02_Final`, `Ex03_PassageParValeur`
> Exercices : `Exo01_PassageParValeur`

---

## Les six « kinds of variables » (JLS §4.12.1)

1. **Champ d'instance** — un par objet, durée de vie liée à l'objet
2. **Champ de classe** (`static`) — partagé, durée de vie liée à la classe
3. **Composant de tableau** — chaque case est une variable distincte
4. **Variable locale** — dans une méthode, sans valeur par défaut
5. **Paramètre de méthode** — copie de la valeur passée à l'appel
6. **Paramètre d'exception** — la variable du `catch`

---

## `final`

`final` fige la **variable** (pas de réaffectation), pas la **valeur** :
- sur un primitif : valeur immuable (`final int MAX = 100`) ;
- sur une référence : le *pointeur* est figé, mais l'objet pointé reste modifiable (`final List` → `add()` autorisé, réaffectation interdite).

Un objet vraiment immuable suppose que la classe l'interdit (ex. `String`).

---

## Passage par valeur

Java passe **toujours par valeur** :
- primitif → la méthode reçoit une copie ; pour transmettre un résultat, il faut le **renvoyer** ;
- référence → c'est l'*adresse* qui est copiée. Modifier le **contenu** de l'objet est visible de l'appelant ; **réaffecter** la variable locale ne l'est pas.
