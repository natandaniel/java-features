# Classes et objets

> Module `m02_poo / c01_classes_objets`
> Leçon : `Ex01_DefinirUneClasse`
> Exercices : `Exo01_Etudiant`

Premier concept du module POO : le vocabulaire de base pour définir une classe et
l'instancier. **Hors scope ici** : constructeur explicite (`c02_constructeurs`), `this`
(`c03_mot_cle_this`), membres `static` (`c04_membres_statiques`) — chacun devient un concept à
part, dans cet ordre, une fois la notion de classe/objet posée.

---

## Classe vs objet vs instance (JLS §8.1, §8.3)

Une **classe** est un modèle : elle décrit quels champs (état) et quelles méthodes
(comportement) auront les objets construits à partir d'elle. Un **objet** (ou **instance**)
est une réalisation concrète de ce modèle, créée avec `new`. Deux instances de la même classe
ont chacune leur propre copie des champs d'instance : modifier l'une ne modifie pas l'autre.

Sans constructeur explicite, `new Classe()` invoque le constructeur implicite fourni par le
compilateur (JLS §8.8.9 — nuance différée à `c02_constructeurs`) : l'objet existe avec les
valeurs par défaut de ses champs, prêtes à être affectées directement.

---

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — deux instances de la même classe, champs modifiés séparément :
  prédire l'état de chaque objet après une séquence d'affectations. Teste directement « chaque
  objet a sa propre copie des champs », pas de la syntaxe.
- **Application** — écrire une petite classe à partir d'un énoncé (champs publics, méthodes
  d'instance qui les combinent), sans constructeur explicite. Transfert direct de
  `Ex01_DefinirUneClasse`.

## Pistes d'approfondissement (DeepDive)

- Lien avec le modèle mémoire (`c09_modele_memoire` de `m01_fondamentaux`) : ce qui se passe
  concrètement sur le tas à chaque `new`. Optionnel — pont vers un concept déjà acquis, pas
  nécessaire pour comprendre `classes_objets` lui-même.
