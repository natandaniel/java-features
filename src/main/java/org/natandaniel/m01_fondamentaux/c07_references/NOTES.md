# Types référence

> Module `m01_fondamentaux / c07_references`
> Leçons : `Ex01_NullEtNPE`, `Ex02_EgaliteEtEquals`, `Ex03_StringPool`
> Exercices : `Exo01_Egalite`

---

## Primitif vs référence

Un **primitif** stocke directement sa valeur. Une **référence** stocke une *adresse* vers un objet. `String x = s;` ne copie pas la chaîne : `x` et `s` pointent vers le même objet.

## null

Valeur par défaut de toute référence (JLS §4.12.5) ; signifie « ne pointe vers aucun objet ». Appeler une méthode sur `null` lève une `NullPointerException` — l'erreur la plus fréquente en Java.

## `==` vs `.equals()`

- `==` sur des références teste l'**identité** (même objet en mémoire).
- `.equals()` teste l'**égalité de contenu** (si la classe la définit).
- Comparer des `String` avec `==` est un piège classique → utiliser `.equals()`.
- Pour gérer `null` proprement, tester `a == null` d'abord (ou `Objects.equals`).

## String pool

Les littéraux identiques sont *internés* : `"hello" == "hello"` est `true` (même objet du pool). `new String("hello")` crée un objet **hors** du pool (`==` faux) ; `.intern()` le ramène dans le pool.
