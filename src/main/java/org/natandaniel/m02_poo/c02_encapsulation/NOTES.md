# Encapsulation

> Module `m02_poo / c02_encapsulation`
> Leçons : `Ex01_ChampsPrivesEtAccesseurs`, `Ex02_InvariantsEtValidation`, `Ex03_ModificateursAcces`
> Exercices : `Exo01_CompteBancaire`, `Exo02_PlageEntiers`

L'encapsulation restreint l'accès direct à l'état d'un objet, pour que ses seules portes
d'entrée soient les méthodes que la classe expose — ce qui permet à la classe de **garantir**
ses propres invariants plutôt que de les espérer.

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

## Les quatre modificateurs d'accès (du plus restrictif au plus ouvert)

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
