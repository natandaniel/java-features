# Classes et objets

> Module `m02_poo / c01_classes_objets`
> Leçons : `Ex01_DefinirUneClasse`, `Ex02_Constructeurs`, `Ex03_This`, `Ex04_MethodesEtChampsStatiques`
> Exercices : `Exo01_Point`, `Exo02_Rectangle`

Premier concept du module POO : le vocabulaire et la mécanique de base pour définir une
classe, l'instancier, l'initialiser via un constructeur, et distinguer ce qui appartient à
chaque instance de ce qui est partagé par la classe entière.

---

## Classe vs objet vs instance

Une **classe** est un modèle : elle décrit quels champs (état) et quelles méthodes
(comportement) auront les objets construits à partir d'elle. Un **objet** (ou **instance**)
est une réalisation concrète de ce modèle, créée avec `new`. Deux instances de la même classe
ont chacune leur propre copie des champs d'instance : modifier l'une ne modifie pas l'autre.

## Constructeur

Le constructeur initialise un objet à sa création (`new Classe(...)`). Il porte le même nom
que la classe et n'a pas de type de retour.

- Si aucun constructeur n'est écrit, le compilateur en fournit un **implicite**, sans
  argument, qui ne fait rien de plus qu'appeler le constructeur de la superclasse.
- Dès qu'un constructeur explicite est écrit, ce constructeur par défaut **disparaît** —
  `new Classe()` ne compile plus, sauf à écrire soi-même un constructeur sans argument.
- Une classe peut avoir **plusieurs constructeurs** (surcharge), tant que leurs listes de
  paramètres diffèrent.

## `this`

`this` désigne l'objet courant, à l'intérieur d'une méthode ou d'un constructeur.

- **Désambiguïser** : quand un paramètre porte le même nom qu'un champ, `this.champ` désigne
  le champ, `champ` seul désigne le paramètre (le paramètre *masque* le champ).
- **Chaîner les constructeurs** : `this(...)` appelle un autre constructeur de la **même**
  classe, pour éviter de dupliquer la logique d'initialisation. Doit être la **première**
  instruction du constructeur.

## `static` : membres de classe vs membres d'instance

Un membre `static` appartient à la **classe**, pas à chaque instance : un seul exemplaire est
partagé par tous les objets, et il existe déjà avant qu'aucune instance ne soit créée.

- **Champ static** — une seule valeur, vue et modifiée par toutes les instances (ex. un
  compteur du nombre d'objets créés).
- **Méthode static** — n'a pas de `this` implicite, ne peut donc accéder qu'aux membres
  static ; on l'appelle via le nom de la classe (`Classe.methode()`), pas via une instance.
- À l'inverse, un membre d'**instance** (sans `static`) existe une fois par objet et a besoin
  d'une instance pour être utilisé.
