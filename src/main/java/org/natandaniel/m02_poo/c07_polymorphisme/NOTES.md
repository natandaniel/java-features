# Polymorphisme

> Module `m02_poo / c07_polymorphisme`
> Leçons : `Ex01_RedefinitionMethode`, `Ex02_MasquageVsRedefinition`, `Ex03_PolymorphismeUtilisation`
> Exercices : `Exo01_CatalogueMixte`, `Exo02_RemiseEtMasquage`

Comment une méthode d'instance redéfinie (JLS §8.4.8) est choisie à l'exécution d'après le type
*réel* de l'objet, et non le type *déclaré* de la référence qui le désigne — le mécanisme qui
rend le polymorphisme comportemental possible. **Hors scope ici** : override côté interface
(nécessite les méthodes `default`, non traitées), accès explicite à la version masquée/redéfinie
via `super` (concept séparé).

---

## Redéfinition (override)

Une sous-classe `C` redéfinit une méthode d'instance `mA` de sa superclasse `A` (JLS §8.4.8.1) si :
- `C` est sous-classe de `A` ;
- la signature de la méthode dans `C` est identique (plus précisément, *sous-signature* — accepte
  les generics, hors scope ici) à celle de `mA` ;
- la visibilité de la redéfinition est **au moins aussi large** : `public` reste `public`,
  `protected` reste `protected` ou devient `public`, accès package reste au moins accès package.

`@Override` (JLS §9.6.4.4, disponible depuis Java 5) n'est pas obligatoire mais fait détecter par
le compilateur toute signature qui ne correspondrait pas réellement à une redéfinition (faute de
frappe dans le nom, paramètres différents…).

## Retour covariant

Depuis Java SE 5.0 (JLS §8.4.8.3), une redéfinition peut **restreindre** le type de retour à un
sous-type du type déclaré par la méthode redéfinie — jamais l'élargir. `Produit.appliquerRemise`
retourne `Produit` ; `ProduitPhysique.appliquerRemise` peut retourner `ProduitPhysique` sans cast
nécessaire côté appelant.

## Exceptions vérifiées

Une redéfinition ne peut pas déclarer de nouvelle exception **vérifiée** (checked) que la méthode
redéfinie ne déclarait pas déjà — sinon le contrat vu depuis une référence du type superclasse
serait rompu (JLS §8.4.8.3).

## Piège : une méthode `private` n'est jamais redéfinie

Les méthodes `private` ne sont pas héritées (JLS §8.4.8, §8.4.8.1 : la redéfinition exige que
`mA` soit `public`, `protected`, ou accès package — jamais `private`). Une sous-classe qui déclare
une méthode avec la même signature qu'une méthode `private` de sa superclasse ne la redéfinit
pas : elle déclare une méthode **sans rapport**, invisible depuis le code de la superclasse.

## Masquage (hiding) : champs et méthodes `static`

Contrairement aux méthodes d'instance, les **champs** (JLS §8.3) et les **méthodes `static`**
(JLS §8.4.8.2) ne sont jamais redéfinis — ils sont *masqués*. La résolution se fait sur le
**type déclaré** de la référence, à la compilation, jamais sur le type réel de l'objet à
l'exécution. `Ex02_MasquageVsRedefinition` reproduit l'exemple canonique de la JLS
(Example 8.4.8.2-1) : `Produit p = new ProduitImporte(); p.origine()` appelle
`Produit.origine()`, alors que `p.etiquette()` (méthode d'instance, redéfinie) appelle bien
`ProduitImporte.etiquette()`.

## Liaison dynamique (dynamic dispatch)

Pour les méthodes d'instance redéfinies, c'est l'inverse : la version exécutée est choisie
d'après le type **réel** de l'objet, à l'exécution — jamais le type déclaré de la référence qui
le désigne. C'est ce qui permet à `Ex03_PolymorphismeUtilisation` de parcourir une
`List<Produit>` mélangeant plusieurs sous-types et d'appeler `etiquette()` sans jamais tester le
type réel de chaque élément.

## Redéfinition vs surcharge (terminologie)

*Redéfinir* (override, §8.4.8) et *surcharger* (overload, §8.4.9) sont deux mécanismes
différents : la redéfinition garde la même signature dans une sous-classe (résolution à
l'exécution) ; la surcharge déclare des signatures **différentes** dans la même classe
(résolution à la compilation, d'après les types des arguments — algorithme détaillé en §15.12.2,
hors scope ici).

## Hors scope (concepts suivants)

- **Override côté interface** (JLS §9.4.1) : suppose des méthodes `default` et des interfaces,
  non traitées dans le catalogue à ce stade — la résolution de conflit entre méthodes `default`
  héritées (ex. diamant `Top`/`Left`/`Right`/`Bottom` de la JLS) sera vue avec les interfaces.
- **`super.membre`** (JLS §15.11.2) : accès explicite à un membre masqué ou redéfini — concept
  séparé (`mot-cle-super` côté `ocp-curriculum`), qui s'appuie sur celui-ci.
- **Héritage de signatures override-équivalentes entre types génériques** (JLS §8.4.8.4) :
  suppose les generics, non traités dans le catalogue à ce stade.
- **Algorithme de résolution de surcharge** (JLS §15.12.2, phases 1-3) : mentionné en passant
  ci-dessus, jamais détaillé.

## Ancrage dans la spec

Sourcé sur `jls26.pdf` (édition SE26, local — voir mémoire `jls_table_of_contents`) :
- **§8.4.8.1** (p.304-307) — redéfinition par méthode d'instance.
- **§8.4.8.2** (p.308-309) — masquage par méthode `static` (exemple `Super`/`Sub` repris ici).
- **§8.4.8.3** (p.309-313) — retour covariant (Java 5+), exceptions, visibilité, piège `private`.
- **§8.3** — masquage de champ (pour mémoire, déjà connu depuis `m01_fondamentaux`).
- **§9.4.1** (p.366-370) — override côté interface, confirmé hors scope (suppose `default`).
- **§9.6.4.4** (p.386) — `@Override`.

## Pistes d'exercices (Lesson Exercises)

- **Rappel** — extrait avec une méthode redéfinie ; prédire ce qu'affiche un appel via une
  référence typée superclasse. Teste le dispatch dynamique de base (résolution sur le type réel,
  pas le type déclaré).
- **Application** — extrait avec un champ, une méthode `static` et une méthode d'instance de même
  nom dans la superclasse et la sous-classe ; prédire les trois résultats obtenus via une même
  référence typée superclasse. Teste la distinction masquage (champ, `static`) vs redéfinition
  (méthode d'instance) — vient après l'exercice de rappel, qui ne porte que sur la redéfinition
  seule.
- **Transfert** — variante de l'exemple JLS `BufferOutput`/`putchar` (Example 8.4.8.1-2, §8.4.8.1) :
  une méthode de la superclasse en appelle une autre en interne (self-use) ; prédire le
  comportement quand la sous-classe redéfinit la méthode appelée en interne. Teste un cas limite
  réel (l'effet d'une redéfinition se propage même à des appels internes non redéfinis) plutôt
  qu'une simple relecture de syntaxe — vient en dernier, une fois le mécanisme de base acquis.

## Pistes d'approfondissement (DeepDive)

- **Pourquoi le retour covariant seulement depuis Java 5** — rationale historique liée à
  l'arrivée des generics dans la même version. Optionnel : comprendre la redéfinition ne demande
  pas de savoir depuis quand chaque règle existe.
- **Comparaison avec C++** — méthodes virtuelles seulement si déclarées `virtual`, contrairement à
  Java où c'est le comportement par défaut des méthodes d'instance non `static`/`private`/`final`.
  Optionnel : angle comparatif entre langages, non nécessaire pour utiliser Java correctement.
- **Coût du dispatch dynamique et optimisations JIT** — sites d'appel monomorphes, polymorphes,
  mégamorphes, et comment le JIT peut les optimiser (inlining spéculatif). Optionnel : angle
  performance/implémentation, sans impact sur la correction du code écrit dans ce concept.
