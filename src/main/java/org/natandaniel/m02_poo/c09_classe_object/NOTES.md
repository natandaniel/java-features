# La classe Object

> Module `m02_poo / c09_classe_object`
> Leçons : `Ex01_ToStringParDefaut`, `Ex02_EqualsParDefaut`, `Ex03_RedefinirEqualsHashCode`, `Ex04_RedefinirToString`
> Exercices : `Exo01_EgaliteParIsbn`, `Exo02_RepresentationLivre`
> Prérequis : `c06_heritage` (Object comme superclasse implicite), `c07_polymorphisme` (mécanisme d'override), `m01_fondamentaux/c08_references_variables` (`==` vs `.equals()`)

`java.lang.Object` est la superclasse implicite de toute classe (`c06_heritage`) et impose,
via les méthodes qu'elle définit, un contrat commun à tous les objets. Ce concept traite trois
de ces méthodes — `equals`, `hashCode`, `toString` — leur comportement par défaut, et le
contrat à respecter quand on les redéfinit. **Hors scope ici** : `clone()`, `getClass()`,
`finalize()`, `wait`/`notify`/`notifyAll` — autres méthodes d'`Object`, sujets distincts.

---

## Comportement par défaut

Sans redéfinition, une classe hérite tel quel du comportement d'`Object` :

- **`toString()`** renvoie `getClass().getName() + '@' + Integer.toHexString(hashCode())` —
  l'identité de l'objet (classe + code de hachage en hexadécimal), jamais son contenu.
- **`equals(Object)`** renvoie `x == y` — comparaison de **référence**, pas de contenu (prolonge
  `c08_references_variables`, qui établit déjà ce fait côté opérateur `==`).
- **`hashCode()`** renvoie une valeur cohérente avec cette identité (deux objets distincts
  produisent, en pratique, des entiers distincts).

Tant qu'aucune de ces trois méthodes n'est redéfinie, deux objets « qui se ressemblent en
contenu » restent des objets **distincts** aux yeux d'`equals()`, et un `HashSet`/`HashMap` ne
peut détecter aucun doublon de contenu entre eux.

## Le contrat d'`equals()` (Javadoc `java.lang.Object`)

> *"The equals method implements an equivalence relation on non-null object references."*

Redéfinir `equals()` engage à respecter cinq propriétés, précisément énoncées dans le Javadoc :

- **Réflexif** : `x.equals(x)` doit renvoyer `true`.
- **Symétrique** : `x.equals(y)` doit renvoyer `true` si et seulement si `y.equals(x)` le fait.
- **Transitif** : si `x.equals(y)` et `y.equals(z)` renvoient `true`, alors `x.equals(z)` doit
  aussi renvoyer `true`.
- **Cohérent** : plusieurs invocations de `x.equals(y)` doivent renvoyer systématiquement le même
  résultat, tant que rien d'utilisé dans la comparaison n'a changé.
- Pour toute référence non nulle `x`, **`x.equals(null)` doit renvoyer `false`**.

Ces cinq propriétés font d'`equals()` une **relation d'équivalence** : elle partitionne les
objets comparés en classes d'équivalence, dont les membres sont substituables entre eux (au
moins pour certains usages).

## Le lien obligatoire avec `hashCode()`

Le Javadoc d'`equals()` le précise explicitement :

> *"It is generally necessary to override the hashCode method whenever this method is
> overridden, so as to maintain the general contract for the hashCode method, which states
> that equal objects must have equal hash codes."*

Le contrat de `hashCode()` (Javadoc `java.lang.Object`) tient en trois points :

- **Cohérence intra-exécution** : appelé plusieurs fois sur le même objet durant une même
  exécution, `hashCode()` doit renvoyer systématiquement le même entier, tant que rien
  d'utilisé dans les comparaisons `equals()` n'a changé.
- **Objets égaux ⇒ même `hashCode()`** — *obligatoire* : si `x.equals(y)` renvoie `true`, alors
  `x.hashCode() == y.hashCode()`.
- **Objets inégaux ⇒ `hashCode()` différents** — *non obligatoire*, mais souhaitable : réduit
  les collisions et améliore les performances des tables de hachage (`HashMap`, `HashSet`).

**Piège classique** : redéfinir `equals()` sans redéfinir `hashCode()` viole ce deuxième point.
Deux objets peuvent alors être « égaux » selon `equals()` tout en ayant des `hashCode()`
différents (celui d'`Object`, basé sur l'identité, reste actif) — un `HashSet`/`HashMap` les
range dans des compartiments différents et ne les compare **jamais** entre eux via `equals()`.
Conséquence observable : `HashSet.add(...)` accepte deux éléments « égaux » comme s'ils étaient
distincts (`Ex03_RedefinirEqualsHashCode`, cas `ProduitEqualsSeul`).

## Redéfinir `toString()`

Le Javadoc recommande explicitement la redéfinition :

> *"It is recommended that all subclasses override this method."* — la représentation doit être
> *"concise but informative"*, facile à lire pour un humain (logs, debug, messages d'erreur).

`toString()` n'a pas de contrat d'équivalence comme `equals()`/`hashCode()` : aucune propriété
à vérifier, seulement une convention de lisibilité. C'est la redéfinition la plus simple des
trois, et la seule sans risque de rupture de contrat si elle est omise ou mal écrite (au pire,
un affichage peu informatif — pas un `HashSet` cassé).

## `Object` reste la référence en l'absence de redéfinition

Aucune des trois méthodes n'est abstraite : une classe qui n'en redéfinit aucune reste
**valide**, simplement héritière du comportement d'`Object` (§4.3.2 JLS). Redéfinir est un choix
de conception, pas une obligation du langage — mais un choix presque toujours pertinent dès
qu'une classe est comparée par contenu (`equals`/`hashCode`) ou affichée (`toString`).

## Hors scope (concepts suivants)

- **`clone()`**, **`getClass()`**, **`finalize()`**, **`wait`/`notify`/`notifyAll`** : autres
  méthodes d'`Object`, sujets distincts (clonage, réflexion, concurrence) — non traités ici.
- **`Comparable`/`compareTo`** : notion d'ordre, distincte de l'égalité — concept séparé.
- **Génération automatique par les `record`** : les composants d'un `record` génèrent
  `equals()`/`hashCode()`/`toString()` conformes à ce contrat sans code à écrire — pertinent une
  fois les `record` traités (pas encore au catalogue).
- **`Objects.equals`/`Objects.hash`** (`java.util.Objects`, `// @since Java 7`) : utilitaires qui
  gèrent `null` et combinent plusieurs champs — non introduits ici pour rester sur le mécanisme
  brut (`instanceof` + cast), cohérent avec le style déjà utilisé dans le catalogue
  (`c07_polymorphisme`, `c08_mot_cle_super`) où le pattern matching `instanceof` (Java 16) n'a
  pas encore été introduit.

## Ancrage dans la spec

- **JLS §4.3.2 « The Class Object »** (`jls26.pdf`, p.70 imprimée — voir mémoire
  `jls_table_of_contents`) : `Object` est superclasse de toutes les classes ; `equals` *"defines
  a notion of object equality, which is based on value, not reference, comparison"* ; `hashCode`
  *"is very useful, together with the method equals, in hashtables such as java.util.HashMap"* ;
  `toString` *"returns a String representation of the object"*. Cette section établit **que**
  ces méthodes existent et **pourquoi** on les redéfinit — pas le détail du contrat.
- **Javadoc `java.lang.Object`** (spécification de l'API Java SE — document distinct de la JLS,
  aucune section JLS propre ne détaille ce contrat) : source du contrat exact d'`equals()` (les
  cinq propriétés), de `hashCode()` (les trois points), et de la recommandation sur `toString()`
  — citations exactes ci-dessus. Récupéré via le Javadoc officiel (`docs.oracle.com`), faute de
  source locale disponible dans `sources/` pour ce texte précis.

## Pistes d'exercices (Lesson Exercises)

- **Rappel** — extrait avec une classe qui redéfinit `equals()` mais pas `hashCode()`, deux
  instances « égales » selon `equals()` insérées dans un `HashSet` (domaine différent d'`Ex03`,
  ex. `Client` identifié par `email`) ; prédire la taille du `HashSet` et expliquer pourquoi.
  Teste directement le lien du contrat (equals/hashCode) — pas la syntaxe, le cœur de l'idée de
  la leçon.
- **Application** — classe `Employe` identifiée par son `matricule` (autres champs : `nom`,
  `poste`) ; écrire `equals()`/`hashCode()` respectant le contrat. Transfert direct de la
  technique vue dans `Ex03`/`Exo01`, à un niveau « écrire soi-même ». Vient après le rappel, qui
  ne porte que sur le diagnostic.
- **Transfert / cas limite** — une sous-classe (`ProduitPromotionnel extends Produit`, style
  `c06_heritage`/`c07_polymorphisme`) ajoute un champ à son propre `equals()` sans respecter
  celui de la superclasse ; montrer que la symétrie se casse entre une instance `Produit` et une
  instance `ProduitPromotionnel` de même référence. Cas limite réel (égalité à travers une
  hiérarchie d'héritage, piège classique) ; vient en dernier, car il réutilise `c06`/`c07` en
  plus du contrat lui-même.

## Pistes d'approfondissement (DeepDive)

- **Records et génération automatique** — les composants d'un `record` génèrent
  `equals`/`hashCode`/`toString` conformes à ce contrat, sans code à écrire. Optionnel :
  pointeur en avant vers un concept pas encore traité, purement informatif ici.
- **`hashCode()` et l'adresse mémoire — un mythe courant** — le contrat n'exige pas que
  `hashCode()` soit basé sur l'adresse mémoire, ni qu'il reste stable d'une exécution à l'autre
  (*"This integer need not remain consistent from one execution of an application to another
  execution of the same application"*, Javadoc `java.lang.Object`). Optionnel : corrige une
  intuition répandue mais fausse, sans être nécessaire pour utiliser correctement
  `equals`/`hashCode`.
- **Style moderne : `Objects.equals`/`Objects.hash` et pattern matching `instanceof`** — comment
  `java.util.Objects` (Java 7) et le pattern matching `instanceof` (Java 16) simplifient
  l'écriture vue ici en style classique (`instanceof` + cast). Optionnel : angle comparatif
  ancien/moderne, le style classique suffit à comprendre le contrat.
