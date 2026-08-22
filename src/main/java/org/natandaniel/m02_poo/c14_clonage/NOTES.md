# Le clonage d'objets

> Module `m02_poo / c14_clonage`
> Leçons : `Ex01_CopieSansClonage`, `Ex02_CloneableEtSuperClone`,
> `Ex03_ClonageSuperficiel`, `Ex04_ClonageProfond`
> Exercices : `Exo01_ClonageProfondUnNiveau`, `Exo02_ClonageProfondDeuxNiveaux`
> Prérequis : `c09_classe_object` (`Object` comme superclasse universelle, `clone()` y est nommé
> — JLS §4.3.2), `c07_polymorphisme` (retour covariant, réutilisé dans l'override de `clone()`),
> `c13_classes_immuables` (le même geste — `tableau.clone()` — y servait déjà à la copie
> défensive)
> Correspondance `ocp-curriculum` : `lessons/I-langage/clonage.md` (domaine `I.B`,
> `prerequis: [classe-object]`)

`Cloneable`/`Object.clone()` permettent de dupliquer un objet, mais avec une plomberie
particulière et des pièges qui en limitent l'usage pratique. `Object.clone()` existe depuis
Java 1.0.

```java
class Commande implements Cloneable {
    private String[] articles;
    @Override
    public Commande clone() {
        try {
            Commande clone = (Commande) super.clone();
            clone.articles = articles.clone();   // sans cette ligne : copie superficielle
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
```

---

## 1. `clone()` est `protected`, et conditionné à `Cloneable` (`Ex01`)

`Object` déclare `clone()` en `protected` : aucun code extérieur à la classe (ni même une
sous-classe qui ne l'a pas explicitement republié) ne peut l'invoquer. Et republier `clone()` ne
suffit pas : la Javadoc `Object#clone()` pose une condition supplémentaire — *"if the class of
this object does not implement the interface `Cloneable`, then a `CloneNotSupportedException`
is thrown"*. C'est une exception **vérifiée** (*checked*) : toute méthode qui appelle
`super.clone()` doit la déclarer (`throws`) ou la traiter.

## 2. La plomberie correcte : `Cloneable` + `super.clone()` (`Ex02`)

`Cloneable` est une **interface marqueur** : elle ne déclare aucune méthode. Javadoc
`Cloneable` : *"does not contain the `clone` method... it is not possible to clone an object
merely by virtue of the fact that it implements this interface"*. Son seul rôle est
d'autoriser `Object.clone()`, à l'exécution, à faire une copie champ à champ — sans elle,
`super.clone()` échoue quel que soit le code qui l'entoure.

La convention complète est donc en trois pièces : `implements Cloneable` (autorise),
`@Override public ... clone()` (republie en `public`, avec un **retour covariant** — le type de
la classe, pas `Object`, cf. `c07_polymorphisme`), et un corps qui appelle `super.clone()` en
convertissant `CloneNotSupportedException` — devenue inatteignable une fois `Cloneable`
implémenté — en `AssertionError`.

## 3. Le piège : copie superficielle (`Ex03`)

`super.clone()` ne fait qu'une copie **champ à champ** — Javadoc `Object#clone()` : *"initializes
all its fields with exactly the contents of the corresponding fields of this object... the
contents of the fields are not themselves cloned... a 'shallow copy'... not a 'deep copy'"*.
Pour un champ référence (tableau, collection, objet mutable), le clone obtenu **partage** encore
la même instance que l'original. Modifier ce champ depuis l'un des deux objets affecte
silencieusement l'autre : le clone n'est indépendant qu'en apparence.

## 4. La correction : clonage profond (`Ex04`)

La Javadoc `Object#clone()` prescrit directement la correction : *"it may be necessary to modify
one or more fields of the object returned by `super.clone`... typically, this means copying any
mutable objects... and replacing the references to these objects with references to the
copies"*. Concrètement : après l'appel à `super.clone()`, réassigner chaque champ mutable du
clone à sa propre copie (`champ.clone()` pour un tableau, ou récursivement `champ.clone()` si le
champ est lui-même `Cloneable` — cf. `Exo02`, deux niveaux d'imbrication).

## Hors scope (concepts suivants ou hors périmètre)

- **Constructeur ou fabrique de copie** (*Effective Java*, Item 13, « Override clone
  judiciously ») — l'alternative généralement recommandée à `Cloneable`, plus simple et plus
  sûre (pas d'exception vérifiée, pas de contrat implicite avec les sous-classes). Non traité en
  leçon ici : source non disponible localement (voir « Ancrage dans la spec »).
- **`record`** (Java 14+) — un type immuable n'a en principe pas besoin de `clone()` (rien à
  dupliquer contre une mutation qui ne peut pas survenir). Renvoyé à `c21_records`, qui s'appuie
  sur `c13_classes_immuables`.
- **Sérialisation comme mécanisme de copie profonde générique** — hors périmètre langage, sujet
  propre de `m08_entrees_sorties`.

## Ancrage dans la spec

- **JLS §4.3.2 « The Class Object »** (`jls26.pdf`, p.70 imprimée) :
  > *"The method clone is used to make a duplicate of an object."*
  Une seule phrase — la JLS **nomme** `clone()` parmi les méthodes héritées d'`Object`, exactement
  comme elle le fait pour `equals`/`hashCode`/`toString` en `c09_classe_object`, sans en détailler
  le contrat, les conditions d'échec, ni `Cloneable`.
- **Gap identifié, même cas que `c09_classe_object`** : le contrat complet (`protected`,
  condition sur `Cloneable`, copie « field-for-field », garantie d'indépendance) vit dans le
  **Javadoc de l'API Java SE**, pas dans la JLS. WebFetch du Javadoc officiel confirmé par Natan
  (règle validée le 2026-08-21 pour `c09`, réappliquée ici) :
  - `java.lang.Object#clone()` : *"Creates and returns a copy of this object... The method clone
    for class Object performs a specific cloning operation. First, if the class of this object
    does not implement the interface Cloneable, then a CloneNotSupportedException is thrown...
    Otherwise, this method creates a new instance of the class of this object and initializes
    all its fields with exactly the contents of the corresponding fields of this object, as if
    by assignment; the contents of the fields are not themselves cloned. Thus, this method
    performs a 'shallow copy' of this object, not a 'deep copy' operation... By convention, the
    object returned by this method should be independent of this object... it may be necessary
    to modify one or more fields of the object returned by super.clone before returning it."*
  - `java.lang.Cloneable` : *"A class implements the Cloneable interface to indicate to the
    Object.clone() method that it is legal for that method to make a field-for-field copy of
    instances of that class... this interface does not contain the clone method... it is not
    possible to clone an object merely by virtue of the fact that it implements this
    interface... Invoking Object's clone method on an instance that does not implement the
    Cloneable interface results in the exception CloneNotSupportedException being thrown."*
  Fondement direct d'`Ex01` (protected + condition `Cloneable`), `Ex02` (plomberie), `Ex03`
  (« shallow copy »), `Ex04` (correction prescrite mot pour mot par la dernière phrase citée).
- **Hors JLS, signalé explicitement** : le constructeur/fabrique de copie (Item 13) n'a pas de
  section JLS — la source usuelle (*Effective Java*) n'est **pas disponible en local** dans
  `sources/Effective Java/` (seul l'Item 18 y est photographié) ; non traité en leçon, seulement
  mentionné en « Hors scope » ci-dessus, sans citation sur pièce.

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — face à `Ex03` (`Commande` à copie superficielle), identifier
  précisément quelle ligne manque dans `clone()` pour que le tableau `articles` cesse d'être
  partagé, et pourquoi `super.clone()` seul ne suffit pas. Vérifie que la distinction
  « `super.clone()` copie les références, pas ce qu'elles pointent » est acquise avant d'aller
  plus loin.
- **Application** — étant donné une nouvelle classe à un seul champ mutable (ex. une liste de
  tags), écrire `clone()` de bout en bout (`implements Cloneable`, retour covariant,
  `try`/`catch` → `AssertionError`, copie du champ), en transférant le patron d'`Ex04` à un
  nouveau domaine.
- **Transfert / cas limite** — une classe dont **tous** les champs sont immuables (primitifs ou
  `String`) a-t-elle encore besoin de retoucher quoi que ce soit après `super.clone()` ? Fait
  raisonner sur la condition réelle d'application du clonage profond (le champ référencé est-il
  lui-même mutable ?), pas seulement sur son mécanisme — le même raisonnement que la piste
  « transfert » de `c13_classes_immuables`, appliqué ici au clonage plutôt qu'à la copie
  défensive.

## Pistes d'approfondissement (DeepDive)

- **Le constructeur/fabrique de copie comme alternative (*Effective Java* Item 13)** — pourquoi
  cette approche est généralement préférée à `Cloneable` en pratique (pas d'exception vérifiée,
  pas de contrat fragile hérité par les sous-classes, pas de cast). Optionnel : angle
  rationale/comparaison, pas nécessaire pour utiliser correctement `Cloneable` tel qu'enseigné
  ici.
- **Pourquoi les tableaux sont un cas à part** — Javadoc `Object#clone()` : *"all arrays are
  considered to implement the interface Cloneable"* implicitement (leur `clone()` est toujours
  disponible, sans déclaration explicite). Optionnel : curiosité de plateforme, sans impact sur
  l'écriture de `clone()` pour une classe ordinaire.
- **Clonage dans une hiérarchie avec des sous-classes** — ce qui se passe quand une sous-classe
  hérite d'un `clone()` déjà correct mais ajoute elle-même un champ mutable (doit-elle
  redéfinir `clone()` à son tour ?). Optionnel : anticipe des questions de conception plus larges
  sur l'héritage, hors du cœur du mécanisme.
