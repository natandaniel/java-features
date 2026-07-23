# Références et variables

> Module `m01_fondamentaux / c08_references_variables`
> Leçons : `Ex01_NullEtNPE`, `Ex02_EgaliteEtEquals`, `Ex03_StringPool`, `Ex04_KindsOfVariables`, `Ex05_Final`, `Ex06_PassageParValeur`
> Exercices : `Exo01_Egalite`, `Exo02_PassageParValeur`

Deux faces d'une même notion : ce qu'est une **référence** (le modèle : adresse, `null`, identité) puis ce qu'elle **implique** quand on la range dans une variable et qu'on la passe à une méthode.

---

## Primitif vs référence

Un **primitif** stocke directement sa valeur. Une **référence** stocke une *adresse* vers un objet. `String x = s;` ne copie pas la chaîne : `x` et `s` pointent vers le même objet.

## null

Valeur par défaut de toute référence (JLS §4.12.5) ; signifie « ne pointe vers aucun objet ». Appeler une méthode sur `null` lève une `NullPointerException` — l'erreur la plus fréquente en Java.

Depuis Java 14 (JEP 358, par défaut depuis Java 15), le message de la `NullPointerException` précise **quelle** référence était `null` (« *Cannot invoke "String.length()" because "s" is null* ») au lieu d'une trace nue — utile dès qu'une expression chaîne plusieurs appels (`a.b().c()`).

## `==` vs `.equals()`

- `==` sur des références teste l'**identité** (même objet en mémoire).
- `.equals()` teste l'**égalité de contenu** (si la classe la définit).
- Comparer des `String` avec `==` est un piège classique → utiliser `.equals()`.
- Pour gérer `null` proprement, tester `a == null` d'abord (ou `Objects.equals`).
- **Piège tableau** : un tableau n'a pas d'`equals()` de contenu — `Object.equals` hérité compare l'identité, donc `array1.equals(array2)` équivaut à `array1 == array2`. Utiliser `Arrays.equals(...)`.

## String pool

Les littéraux identiques sont *internés* : `"hello" == "hello"` est `true` (même objet du pool). `new String("hello")` crée un objet **hors** du pool (`==` faux) ; `.intern()` le ramène dans le pool.

---

## Les huit « kinds of variables » (JLS §4.12.3)

1. **Champ d'instance** — un par objet, durée de vie liée à l'objet
2. **Champ de classe** (`static`) — partagé, durée de vie liée à la classe
3. **Composant de tableau** — chaque case est une variable distincte
4. **Variable locale** — dans une méthode, sans valeur par défaut
5. **Paramètre de méthode** — copie de la valeur passée à l'appel
6. **Paramètre d'exception** — la variable du `catch`
7. **Paramètre de constructeur** — même mécanique que le paramètre de méthode ; illustré une fois les constructeurs vus (m02_poo)
8. **Paramètre de lambda** — idem, une fois les lambdas vues (m05_fonctionnel)

Les six premières sont illustrées ici ; les deux dernières attendent leur propre concept pour un exemple exécutable, mais suivent exactement la même règle que le paramètre de méthode (copie à l'appel, cf. passage par valeur ci-dessous).

## `final`

`final` fige la **variable** (pas de réaffectation), pas la **valeur** :
- sur un primitif : valeur immuable (`final int MAX = 100`) ;
- sur une référence : le *pointeur* est figé, mais l'objet pointé reste modifiable (`final List` → `add()` autorisé, réaffectation interdite).

Un objet vraiment immuable suppose que la classe l'interdit (ex. `String`).

## Passage par valeur

Java passe **toujours par valeur** :
- primitif → la méthode reçoit une copie ; pour transmettre un résultat, il faut le **renvoyer** ;
- référence → c'est l'*adresse* qui est copiée. Modifier le **contenu** de l'objet est visible de l'appelant ; **réaffecter** la variable locale ne l'est pas.
