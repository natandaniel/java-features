# Membres statiques et membres d'instance

> Module `m02_poo / c04_membres_statiques`
> Leçon : `Ex01_MembresStatiques`
> Exercices : `Exo01_Commande`
> Prérequis : `c01_classes_objets` (le constructeur utilisé dans la leçon a déjà été vu en
> `c02_constructeurs`, mais la notion de `static` elle-même n'en dépend pas)

Dernier concept de ce bloc : ce qui appartient à la **classe** plutôt qu'à chaque instance.

---

## `static` : membres de classe vs membres d'instance

Un membre `static` appartient à la **classe**, pas à chaque instance : un seul exemplaire est
partagé par tous les objets, et il existe déjà avant qu'aucune instance ne soit créée.

- **Champ static** (JLS §8.3.1.1) — une seule valeur, vue et modifiée par toutes les instances
  (ex. un compteur du nombre d'objets créés).
- **Méthode static** (JLS §8.4.3.2) — n'a pas de `this` implicite, ne peut donc accéder qu'aux
  membres static ; on l'appelle via le nom de la classe (`Classe.methode()`), pas via une
  instance.
- À l'inverse, un membre d'**instance** (sans `static`) existe une fois par objet et a besoin
  d'une instance pour être utilisé.

---

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — une méthode `static` qui tenterait de lire un champ d'instance
  sans objet explicite : pourquoi ça ne compile pas (pas de `this` implicite, JLS §8.4.3.2).
- **Transfert** — un compteur `static` incrémenté dans le constructeur : prédire la valeur lue
  après plusieurs créations d'objets, via une instance *et* via le nom de la classe (JLS
  §8.3.1.1). Transfert direct de `Ex01_MembresStatiques`.

## Pistes d'approfondissement (DeepDive)

- Bloc d'initialisation `static { ... }` : mécanisme voisin, hors périmètre — leçon dédiée
  côté `ocp-curriculum` (`blocs-initialisation`). Optionnel — les champs `static` de ce concept
  s'initialisent déjà par simple affectation, sans bloc.
