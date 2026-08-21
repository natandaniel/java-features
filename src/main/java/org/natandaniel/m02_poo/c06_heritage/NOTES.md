# Héritage

> Module `m02_poo / c06_heritage`
> Leçons : `Ex01_ChaineDHeritage`, `Ex02_HeritageSimple`, `Ex03_RestrictionsExtends`
> Exercices : `Exo01_Produit`, `Exo02_ListeProduits`

Mécanique structurelle du mot-clé `extends` (JLS §8.1.4) : comment une classe réutilise et
prolonge une autre classe. **Hors scope ici** : la redéfinition de méthode (override) et le
polymorphisme comportemental — vus dans un concept séparé, qui s'appuie sur celui-ci.

---

## Superclasse et sous-classe

`extends` déclare la **superclasse directe** d'une classe — une seule : Java pratique
l'**héritage simple** (contrairement au C++, qui autorise l'héritage multiple de classes).
Une classe qui hérite d'une autre est sa **sous-classe** (descendante) ; celle dont elle
hérite est sa **superclasse** (ancêtre).

Sans `extends`, la superclasse directe est implicitement `java.lang.Object` — toute classe en
hérite donc, directement ou transitivement.

## Réutilisation d'implémentation

Une sous-classe hérite de tous les champs et méthodes accessibles de sa superclasse, sans
avoir à les redéclarer : elle n'a besoin d'écrire que ce qui lui est **propre** (nouveaux
champs, nouvelles méthodes), ou ce qu'elle veut **redéfinir** (hors scope de ce concept).

## Transitivité

La relation de superclasse est la **fermeture transitive** de la relation directe : si `C`
étend `B` et `B` étend `A`, alors `C` est aussi sous-classe de `A` (pas seulement de `B`). Une
instance de `C` hérite donc des membres de `B` **et** de `A`.

## Substituabilité de référence (EST-UN)

Une instance de sous-classe **est aussi** une instance de chacune de ses superclasses : une
variable de type superclasse peut référencer un objet de type sous-classe sans conversion
explicite (`Produit p = unProduitPhysique;`). C'est la relation « EST-UN », prérequis structurel du
polymorphisme (traité dans un concept ultérieur).

## Restrictions sur ce qui peut être étendu

- Une classe déclarée `final` ne peut pas être étendue (erreur de compilation).
- `Enum` ne peut être étendue que par une déclaration `enum` (implicitement, pas via
  `extends` écrit à la main) ; de même pour `Record` et les déclarations `record`.
- Une classe `sealed` ne peut être étendue que par les sous-classes qu'elle autorise
  explicitement (`permits`).
- L'héritage **circulaire** est impossible : une classe ne peut pas dépendre d'elle-même dans
  sa chaîne de superclasses (ex. `A extends B`, `B extends A`) — `ClassCircularityError` au
  chargement si le cas se présente.

## Hors scope (concepts suivants du module)

- **Override** (redéfinition de méthode) et **polymorphisme** comportemental.
- **Composition vs héritage** : quand `extends` est le bon choix de conception plutôt qu'une
  référence vers un objet contenu — l'héritage viole l'encapsulation (la sous-classe dépend de
  détails d'implémentation de la superclasse) et n'est légitime que si la sous-classe est
  *vraiment* un sous-type de la superclasse (test du principe de substitution de Liskov).
- **Invocation explicite de `super(...)`** (règles complètes, JLS §8.8.7.1) et **accès
  `super.membre`** à un membre masqué/redéfini (§15.11.2) : différés à un concept dédié
  (mot-clé `super`), pas encore au ROADMAP — même périmètre que `mot-cle-super` côté
  `ocp-curriculum`. Les leçons/exercices de ce concept utilisent déjà `super(...)` pour
  chaîner les constructeurs (voir `Ex01_ChaineDHeritage`) ; seul un commentaire minimal au
  site d'appel en signale le rôle, sans en détailler la mécanique.
