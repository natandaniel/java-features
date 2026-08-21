# Encapsulation

> Module `m02_poo / c02_encapsulation`
> Leçons : `Ex01_ChampsPrivesEtAccesseurs`, `Ex02_InvariantsEtValidation`, `Ex03_ModificateursAcces`
> Exercices : `Exo01_CompteBancaire`, `Exo02_PlageEntiers`

L'encapsulation restreint l'accès direct à l'état d'un objet, pour que ses seules portes
d'entrée soient les méthodes que la classe expose — ce qui permet à la classe de **garantir**
ses propres invariants plutôt que de les espérer. **Hors scope ici** : la nuance d'accès
`protected` hors package via une référence typée sous-classe (JLS §6.6.2.1) — suppose la
notion de sous-classe, vue dans `c03_heritage`.

---

## Champs privés + accesseurs

Un champ `private` n'est visible que dans la classe qui le déclare. De l'extérieur, on passe
par des méthodes dédiées — un **accesseur** (`getX()`) pour lire, un **mutateur** (`setX(...)`)
pour écrire, si l'écriture doit rester possible. Un champ sans accesseur en écriture est
protégé de toute modification externe.

## Invariant de classe

Un **invariant** est une propriété que les objets d'une classe doivent toujours respecter
(ex. : un solde de compte ne devient jamais négatif). Sans encapsulation, un champ public
laisse n'importe quel code externe casser cet invariant en une affectation directe. En passant
l'écriture par une méthode qui valide les valeurs (dans le constructeur et dans chaque méthode
de modification), la classe garantit l'invariant **elle-même**, en un seul endroit.

## Les quatre modificateurs d'accès (du plus restrictif au plus ouvert) — JLS §6.6.1

| Modificateur | Portée |
|---|---|
| `private` | la **classe de premier niveau** qui le déclare, uniquement |
| *(aucun, package-private)* | le package courant |
| `protected` | le package courant + les sous-classes, même dans un autre package |
| `public` | partout |

**Piège** — `private` se limite à la classe de **premier niveau** (top-level), pas à la classe
imbriquée qui déclare le membre : du code situé dans une autre classe imbriquée de la *même*
classe de premier niveau peut donc accéder à un membre `private` d'une classe voisine (JLS
§6.6.1). La frontière réelle d'encapsulation à retenir est le fichier/la classe de premier
niveau, pas chaque `class`/`static class` qu'il contient.

## Hors scope (concept suivant du module)

- **`protected` hors package** : l'accès n'est permis, hors du package de déclaration, que par
  du code « responsable de l'implémentation » de l'objet — concrètement, via une référence
  typée sous-classe (ou plus précise), jamais via une référence typée superclasse (JLS
  §6.6.2.1). Nuance différée à `c03_heritage`, qui introduit la notion de sous-classe.

---

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — une classe à champs `public` mutables qui viole un invariant
  annoncé en commentaire (ex. un total qui ne doit jamais être négatif) : identifier quelle
  ligne de code externe casse l'invariant et pourquoi rien ne l'empêche.
- **Application** — refactorer cette classe (ou une classe neuve) en privatisant les champs et
  en ajoutant un mutateur qui valide, pour garantir l'invariant en un seul endroit. Transfert
  direct de `Ex02_InvariantsEtValidation`.
- **Transfert/cas limite** — deux classes du même package, sans `private` (package-private) :
  prédire ce qui est accessible depuis l'extérieur du package vs depuis une classe voisine du
  même package (JLS §6.6.1, Example 6.6-4).

## Pistes d'approfondissement (DeepDive)

- Convention JavaBean (`getX`/`setX`) vs accesseurs générés par les records — comparaison
  historique/inter-versions. Pas de section JLS propre (les records relèvent de §8.10, un autre
  chapitre) ; angle stylistique, réellement optionnel.
