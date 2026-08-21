# Le mot-clé this

> Module `m02_poo / c03_mot_cle_this`
> Leçon : `Ex01_This`
> Exercices : `Exo01_Rectangle`
> Prérequis : `c01_classes_objets`, `c02_constructeurs`

`this` désigne l'objet courant. **Hors scope ici** : membres `static` (`c04_membres_statiques`),
qui n'ont justement pas de `this` implicite.

---

## `this` (JLS §15.8.3)

Utilisable comme expression dans le corps d'une méthode d'instance, d'un constructeur, ou d'un
initialiseur d'instance — jamais dans un contexte `static` (JLS §15.8.3 liste précisément ces
contextes).

- **Désambiguïser** : quand un paramètre porte le même nom qu'un champ, `this.champ` désigne
  le champ, `champ` seul désigne le paramètre (le paramètre *masque* le champ).
- **Chaîner les constructeurs** : `this(...)` appelle un autre constructeur de la **même**
  classe, pour éviter de dupliquer la logique d'initialisation. Convention à ce stade : en
  première instruction du constructeur (JLS §8.8.7.1 — la nuance complète, un *prologue*
  d'instructions pouvant la précéder depuis Java 25, JEP 513 Flexible Constructor Bodies, est
  différée à `m06_modernite`).
- **Piège** — une chaîne de `this(...)` qui s'invoque, directement ou indirectement,
  elle-même est une erreur de compilation (JLS §8.8.7.1) : `A(int x) { this(x, 0); }` et
  `A(int x, int y) { this(x); }` ne compilent pas ensemble.

---

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — un constructeur dont un paramètre porte le même nom qu'un champ :
  prédire l'état de l'objet selon que le corps écrit `this.champ = ...` ou `champ = ...`.
- **Application** — ajouter à une classe déjà pourvue d'un constructeur un second constructeur
  qui délègue au premier via `this(...)`. Transfert direct de `Ex01_This`.
- **Transfert/piège** — une chaîne `this(...)` circulaire entre deux constructeurs : pourquoi
  ça ne compile pas (JLS §8.8.7.1).

## Pistes d'approfondissement (DeepDive)

- Nuance complète de la place de `this(...)` dans le corps du constructeur (JEP 513, Flexible
  Constructor Bodies, Java 25) : un prologue d'instructions peut désormais la précéder.
  Optionnel — la convention « première instruction » suffit pour ce niveau ; la nuance est
  reprise en profondeur dans `m06_modernite`.
