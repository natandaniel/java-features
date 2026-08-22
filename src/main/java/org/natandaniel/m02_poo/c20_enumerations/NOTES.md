# Énumérations

> Module `m02_poo / c20_enumerations`
> Leçons : `Ex01_EnumDeBase`, `Ex02_ConstructeurEtChampsParConstante`,
> `Ex03_CorpsDeConstanteEtMethodeAbstraite`, `Ex04_EnumImplementantDesInterfaces`,
> `Ex05_PiegeCirculariteInitialisation`
> Exercices : `Exo01_MethodeAbstraiteParConstante`, `Exo02_ConstructeurEtChampParConstante`
> Prérequis : `c01_classes_objets` (classe/objet), `c11_blocs_initialisation` (bloc `static`,
> réutilisé pour `Ex05`), `c15_interfaces_classes_abstraites` (méthode abstraite, `implements`),
> `c17_heritage_multiple_types` (plusieurs interfaces, réutilisé pour `Ex04`),
> `c19_classes_scellees` (`sealed` — `Ex03` referme le renvoi que `c19` avait laissé ouvert)
> Correspondance `ocp-curriculum` : `lessons/I-langage/B-poo/enumerations.md` (`domaine: I.B`,
> `prerequis: [classes-objets]`, `niveau: debutant`, `duree_min: 8`, `statut: brouillon` — sans
> contenu à ce jour, le périmètre ci-dessous est fixé depuis la JLS et le `ROADMAP`, pas hérité
> d'un brouillon existant)

`enum` (JLS §8.9, `// @since Java 5`) déclare une classe **restreinte** à un ensemble fixe et
nommé d'instances : chaque constante est un champ implicite `public static final` du type de
l'enum (§8.9.3), et il ne peut jamais en exister d'autre. La classe a pour superclasse directe
implicite `Enum<E>` — aucune clause `extends` n'est permise, et toute tentative d'instanciation
explicite (`new`) est une erreur de compilation.

```java
enum StatutCommande { EN_ATTENTE, EXPEDIEE, LIVREE, ANNULEE }
```

---

## 1. Les bases : constantes, `values()`, `==`, `switch` (`Ex01`)

Chaque constante est nommée, ordonnée (son `ordinal()` reflète l'ordre de déclaration, §8.9.3),
et unique : `values()` renvoie toutes les constantes dans cet ordre, `valueOf(String)` retrouve
une constante par son nom. Comme il ne peut jamais exister deux instances de la même constante,
`==` suffit pour comparer deux valeurs d'enum (§8.9.1) — inutile de passer par `equals` (lui-même
`final` dans `Enum`, délègue à l'identité). Un `switch` (`c11_structures_controle`) peut porter
sur une constante d'enum sans qualification (`case LIVREE`, pas `case StatutCommande.LIVREE`).

## 2. Constructeur et champs par constante (`Ex02`)

Une constante peut passer des arguments à un constructeur (§8.9.1, `Example 8.9.2-1`), invoqué une
seule fois par constante lors de l'initialisation de la classe. Ce constructeur est implicitement
`private` (§8.9.2) — le déclarer `public` ou `protected` est une erreur de compilation, puisque
rien en dehors de la déclaration de l'enum ne doit jamais créer de nouvelle instance.

## 3. Corps de constante et méthode abstraite (`Ex03`)

Une constante peut avoir un corps de classe (§8.9.1) : elle déclare alors implicitement une
sous-classe anonyme, `final`, de l'enum — c'est l'alternative à un `switch` externe pour « ajouter »
un comportement différent par constante (`Example 8.9.3-3`). Une méthode abstraite n'est autorisée
dans un enum que si **toutes** ses constantes fournissent une implémentation concrète (§8.9.2) ;
sinon, c'est une erreur de compilation. Conséquence directe sur `sealed` (§8.1.1.2,
`c19_classes_scellees`) : dès qu'au moins une constante a un corps, l'enum devient **implicitement
sealed** — ses seules sous-classes permises sont ces corps anonymes.

## 4. Une enum peut implémenter des interfaces (`Ex04`)

La déclaration d'un enum accepte une clause `implements`, au même titre qu'une classe ordinaire
(§8.9 : `EnumDeclaration ::= {ClassModifier} enum TypeIdentifier [ClassImplements] EnumBody`).
Les règles d'héritage multiple de type (`c17_heritage_multiple_types`) s'appliquent sans aucune
différence — seule la classe qui implémente est ici un enum plutôt qu'une classe ordinaire.

## 5. Piège : référencer un champ `static` non-constant depuis le constructeur (`Ex05`)

Il est interdit, à la compilation, de référencer depuis le constructeur (ou un initialiseur
d'instance) d'un enum un champ `static` qui n'est pas une constante — `final` avec une valeur
d'expression constante, §4.12.4 (§8.9.2, `Example 8.9.2-2`). Sans cette règle, le code
compilerait mais échouerait à l'exécution avec une `NullPointerException` : les constantes d'un
enum sont initialisées **avant** les autres champs `static` déclarés dans son corps, donc un tel
champ vaudrait encore `null` au moment où le premier constructeur s'exécute. La correction :
peupler ce genre de table dans un bloc d'initialisation `static`
(`c11_blocs_initialisation`) placé après les constantes, exécuté seulement une fois qu'elles
existent toutes.

## Hors scope (concepts suivants ou hors périmètre)

- **`Comparable<T>`** — `Enum<E>` l'implémente nativement (`compareTo` sur l'ordre de déclaration,
  §8.9.3 `Example 8.9.3-4` : `Card` s'appuie sur `Rank`/`Suit` pour son propre `compareTo`), mais
  aucun exemple de code ici ne l'illustre : différé à `c18_interface_comparable`, qui dépend des
  génériques (`m05_generiques`, pas encore traité).
- **`record` (§8.10)** — chapitre immédiatement suivant dans la JLS, concept à part entière :
  `c21_records`.
- **`switch` pattern matching sur enum** — `m11_modernite/c03_pattern_matching`.
- **Sérialisation spéciale des enums** (mécanisme anti-duplication garanti par la spec, §8.9) —
  non illustrée : aucun module I/O/sérialisation n'est encore traité dans ce catalogue.
- **Classes/interfaces membres et initialiseurs d'instance dans le corps d'un enum** (§8.9.2,
  `EnumBodyDeclarations` complet) — seuls les membres utilisés dans les leçons ci-dessus (champs,
  constructeur, méthodes, bloc `static`) sont illustrés ; le reste suit les règles ordinaires
  d'une classe (`c01`-`c17`), sans particularité propre aux enums qui justifierait un exemple.

## Ancrage dans la spec

- **JLS §8.9 « Enum Classes »** (`jls26.pdf`, p.332-333 imprimée) :
  > *"An enum declaration specifies a new enum class, a restricted kind of class that defines a
  > small set of named class instances. [...] The direct superclass type of an enum class E is
  > Enum<E>."*
  Fondement d'`Ex01` (constantes, superclasse implicite, pas d'instanciation).
- **JLS §8.9.1 « Enum Constants »** (p.333-334) :
  > *"Because there is only one instance of each enum constant, it is permitted to use the ==
  > operator in place of the equals method [...] The optional class body of an enum constant
  > implicitly declares an anonymous class."*
  Fondement d'`Ex01` (`==`) et `Ex03` (corps de constante, sous-classe anonyme).
- **JLS §8.9.2 « Enum Body Declarations »** (p.334-336, `Example 8.9.2-1` et `8.9.2-2`) :
  > *"In an enum declaration, a constructor declaration with no access modifiers is private. [...]
  > It is a compile-time error to refer to a static field of an enum class from a constructor,
  > instance initializer, or instance variable initializer [...] unless the field is a constant
  > variable. [...] It is a compile-time error if an enum declaration E has an abstract method m
  > as a member, unless [...] all of E's enum constants have class bodies that provide concrete
  > implementations of m."*
  Fondement d'`Ex02` (constructeur `private`), `Ex03` (exigence sur la méthode abstraite) et `Ex05`
  (restriction anti-circularité, `Example 8.9.2-2` repris tel quel avec un domaine différent).
- **JLS §8.9.3 « Enum Members »** (p.336-340, `Example 8.9.3-1` à `8.9.3-4`) :
  > *"An implicitly declared method public static E[] values() [...] An implicitly declared method
  > public static E valueOf(String name)."*
  Fondement d'`Ex01` (`values()`, itération, `switch`, `Example 8.9.3-2`).
- **JLS §8.1.1.2** (déjà citée en `c19_classes_scellees`, p.250) :
  > *"An enum class E is implicitly sealed if its declaration contains at least one enum constant
  > that has a class body. The permitted direct subclasses of E are the anonymous classes
  > implicitly declared by the enum constants that have a class body."*
  Fondement du lien explicite fait dans `Ex03` vers `c19`.

Couvert dans ce concept : §8.9 et §8.9.1 à §8.9.3 en entier (constantes, constructeur, corps de
constante, membres implicites), plus le rappel ciblé de §8.1.1.2 sur le lien avec `sealed`.
Explicitement non couvert, renvoyé aux points listés en « Hors scope » ci-dessus : `Comparable`,
`record`, pattern matching, sérialisation, membres avancés du corps (`EnumBodyDeclarations`).

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — face à un enum sans corps de constante et une méthode qui simule un
  comportement par constante via `switch` (comme `Example 8.9.3-2`), identifier ce qui casse
  silencieusement si une nouvelle constante est ajoutée sans mettre à jour le `switch` (pas
  d'erreur de compilation, juste un `default`/cas manquant) — teste la compréhension du danger
  avant de voir la solution (`Ex03`).
- **Application** — étant donné une nouvelle énumération à modéliser avec un corps de constante
  par valeur (un domaine différent, ex. types de notification avec un canal d'envoi différent
  par type), écrire l'enum avec la méthode abstraite et un corps par constante — transfère le
  patron d'`Ex03`/`Exo01` à un nouveau domaine.
- **Transfert / cas limite** — face au code buggé d'`Ex05` (référence à un champ `static` non
  constant depuis le constructeur), expliquer pourquoi ça ne compile pas et pourquoi ce n'est pas
  qu'un style de code déconseillé mais une règle imposée par le compilateur — teste la
  distinction entre un piège runtime (que Java empêcherait ailleurs par convention) et une
  vraie erreur de compilation, après avoir vu plusieurs erreurs de compilation dans d'autres
  concepts (`c19`) qui, elles, viennent d'un choix de conception plutôt que d'un ordre
  d'initialisation.

## Pistes d'approfondissement (DeepDive)

- **Pourquoi les enums ne peuvent pas être génériques** (`enum Foo<T>` est interdit) — lien avec
  le fait que `Enum<E>` fixe déjà `E` au type de l'enum lui-même (auto-référence, §8.9) ; angle
  purement théorique sur le système de types, sans effet observable dans le code du concept.
- **Le mécanisme anti-duplication de la sérialisation des enums** (cité en « Hors scope ») —
  approfondissement naturel une fois un module I/O/sérialisation traité ; optionnel ici car aucun
  exemple du concept ne sérialise quoi que ce soit.
