# Constructeurs

> Module `m02_poo / c02_constructeurs`
> Leçon : `Ex01_Constructeurs`
> Exercices : `Exo01_Point`
> Prérequis : `c01_classes_objets`

Le constructeur initialise un objet à sa création (`new Classe(...)`). **Hors scope ici** :
`this` — désambiguïsation champ/paramètre et chaînage `this(...)` (`c03_mot_cle_this`), qui
s'appuie directement sur le constructeur introduit ici.

---

## Constructeur (JLS §8.8)

Il porte le même nom que la classe et n'a pas de type de retour.

- Si aucun constructeur n'est écrit, le compilateur en fournit un **implicite**, sans
  argument, qui ne fait rien de plus qu'appeler le constructeur de la superclasse (JLS
  §8.8.9 — même accès que la classe, pas de paramètre, pas de `throws`).
- Dès qu'un constructeur explicite est écrit, ce constructeur par défaut **disparaît** —
  `new Classe()` ne compile plus, sauf à écrire soi-même un constructeur sans argument.
- Une classe peut avoir **plusieurs constructeurs** (surcharge), tant que leurs listes de
  paramètres diffèrent (signatures non override-equivalent, JLS §8.4.2/§8.8.2).

---

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — un constructeur explicite est ajouté à une classe qui n'en avait
  pas : prédire pourquoi `new Classe()` (sans argument) ne compile plus. Teste directement la
  disparition du constructeur implicite (JLS §8.8.9).
- **Application** — écrire une petite classe à partir d'un énoncé (champs + un constructeur qui
  les initialise). Transfert direct de `Ex01_Constructeurs`.
- **Transfert/piège** — deux constructeurs à la même liste de paramètres : pourquoi ça ne
  compile pas (JLS §8.4.2/§8.8.2), à distinguer d'une vraie surcharge à listes différentes.

## Pistes d'approfondissement (DeepDive)

- Lien avec le modèle mémoire (`c09_modele_memoire` de `m01_fondamentaux`) : ce qui se passe
  concrètement sur le tas au moment où `new` invoque le constructeur. Optionnel — pont vers un
  concept déjà acquis, pas nécessaire pour comprendre `constructeurs` lui-même.
- Rationale du constructeur par défaut (JLS §8.8.9) : pourquoi il disparaît dès qu'un
  constructeur explicite existe. Optionnel — la mécanique s'utilise correctement sans connaître
  le raisonnement de conception derrière.
