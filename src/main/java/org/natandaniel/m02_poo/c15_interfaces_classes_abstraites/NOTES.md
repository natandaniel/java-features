# Interfaces et classes abstraites

> Module `m02_poo / c15_interfaces_classes_abstraites`
> Leçons : `Ex01_InterfaceEtImplementation`, `Ex02_ChampsConstantsDInterface`,
> `Ex03_ClasseAbstraiteEtSousClasse`, `Ex04_InterfaceVsClasseAbstraite`
> Exercices : `Exo01_ImplementationDInterface`, `Exo02_SousClasseDeClasseAbstraite`
> Prérequis : `c01_classes_objets` (classe vs objet), `c06_heritage` (`extends`, une seule
> superclasse directe — réutilisé pour expliquer pourquoi une classe abstraite ne peut pas être
> multipliée comme une interface peut l'être)
> Correspondance `ocp-curriculum` : **deux** leçons distinctes, toutes deux `domaine: I.B`,
> `statut: brouillon` — `lessons/I-langage/interfaces.md` (`prerequis: [classes-objets]`) et
> `lessons/I-langage/classes-abstraites.md` (`prerequis: [heritage, interfaces]`)

Une interface et une classe abstraite servent toutes deux à exprimer qu'un type est *incomplet
par construction* — ni l'une ni l'autre ne peut être instantiée directement. Elles divergent sur
ce qu'elles peuvent porter : une interface ne porte aucun état et une classe peut en implémenter
plusieurs ; une classe abstraite peut porter un état partagé (champs, méthodes concrètes), mais
une classe ne peut en étendre qu'une seule.

```java
interface MoyenDePaiement {
    boolean payer(double montant);           // implicitement public abstract
}

abstract class TraitementPaiement {
    private final double montant;
    TraitementPaiement(double montant) { this.montant = montant; }
    abstract boolean verifierFonds();          // corps réduit à ";"
    boolean executer() {                        // méthode concrète, partagée
        return verifierFonds() ? /* ... */ true : false;
    }
}
```

---

## 1. Une interface est un contrat pur (`Ex01`)

Une interface (JLS §9.1) « définit un nouveau type qui peut être implémenté par une ou plusieurs
classes ». Une méthode d'interface sans modificateur est **implicitement `public abstract`**
(JLS §9.4) : `boolean payer(double montant);` équivaut à
`public abstract boolean payer(double montant);`. Une classe qui l'implémente (`implements`,
JLS §8.1.5) doit fournir un corps pour chaque méthode abstraite héritée, sinon elle doit
elle-même être déclarée abstraite. Une interface ne peut jamais être instantiée directement.

## 2. Un champ d'interface est implicitement `public static final` (`Ex02`)

Tout champ déclaré dans une interface (JLS §9.3, « Field (Constant) Declarations ») est
implicitement `public static final` — une constante, pas un champ d'instance. Conséquence
directe : chaque déclarateur **doit** avoir un initialiseur (JLS §9.3.1), sinon erreur de
compilation — contrairement à un champ de classe, qui peut rester à sa valeur par défaut. C'est
la raison structurelle pour laquelle une interface ne peut jamais porter d'état mutable partagé
entre instances : ses champs sont des constantes, pas des variables.

## 3. Une classe abstraite porte une implémentation partielle (`Ex03`)

Une classe abstraite (JLS §8.1.1.1) « est une classe incomplète, ou considérée comme telle ».
Elle peut mélanger méthodes concrètes (avec corps) et méthodes abstraites (JLS §8.4.3.1) : une
méthode abstraite ne fournit que sa signature, son corps est réduit à un point-virgule
(JLS §8.4.7 : *"The body of a method must be a semicolon if the method is abstract or
native"*). Une classe abstraite ne peut pas être instantiée ; toute sous-classe qui n'est pas
elle-même abstraite doit implémenter toutes les méthodes abstraites héritées, sinon erreur de
compilation (JLS §8.4.3.1 : *"Every subclass of A that is not abstract must provide an
implementation for m"*). C'est ce qui permet à une classe abstraite de partager de l'état et du
comportement concret entre ses sous-classes — une interface ne le peut pas.

## 4. Interface vs classe abstraite : un choix de conception (`Ex04`)

| | Interface | Classe abstraite |
|---|---|---|
| État (champs d'instance) | Non — uniquement des constantes (§9.3) | Oui |
| Méthodes concrètes | Non, dans ce concept — voir `c16` (`default`) | Oui |
| Nombre par classe | Plusieurs (`implements`, §8.1.5) | Une seule (`extends`, §8.1.4) |
| Instantiable | Non | Non |

Le choix n'est pas arbitraire : s'il faut partager un champ ou une méthode déjà implémentée entre
plusieurs sous-classes, une classe abstraite s'impose ; s'il faut qu'un même type respecte
plusieurs contrats indépendants, seule une interface le permet (JLS §8.1.5, `implements` accepte
une liste).

## Hors scope (concepts suivants)

- **Méthodes `default` d'interface** (Java 8+, JLS §9.4) — une interface *peut* porter du code
  partagé depuis Java 8, ce qui nuance le tableau ci-dessus. Différé à `c16_methodes_par_defaut`
  (résolution de conflit incluse). **Méthodes `static`/`private` d'interface** (même section
  JLS §9.4) : périmètre tranché le 2026-08-22 — hors scope de `c16` (leçon `ocp-curriculum`
  ciblée centrée sur `default` uniquement), différé à `c25_methodes_statiques_et_privees_interface`.
- **Une classe qui implémente plusieurs interfaces à la fois** — démontré ici seulement en
  théorie (`Ex04`) ; le code (résolution des méthodes partagées entre superinterfaces) est
  différé à `c17_heritage_multiple_types`.
- **`sealed`/`non-sealed`/`permits`** sur une interface ou une classe abstraite — différé à
  `c19_classes_scellees`.
- **`Comparable<T>`** comme exemple canonique d'interface — différé à `c18_interface_comparable`
  (dépend des génériques).

## Ancrage dans la spec

- **JLS §8.1.1.1 « `abstract` Classes »** (`jls26.pdf`, p.248-249 imprimées) :
  > *"An abstract class is a class that is incomplete, or to be considered incomplete. It is a
  > compile-time error if an attempt is made to create an instance of an abstract class using a
  > class instance creation expression... A normal class may have abstract methods... only if it
  > is an abstract class... Every subclass of A that is not abstract must provide an
  > implementation for m, or a compile-time error occurs."*
  Fondement direct d'`Ex03` (non-instanciabilité, obligation d'implémenter dans une sous-classe
  concrète).
- **JLS §8.4.3.1 « `abstract` Methods »** (p.294-295) :
  > *"An abstract method declaration introduces the method as a member, providing its signature,
  > result, and throws clause if any, but does not provide an implementation... The declaration of
  > an abstract method m must appear directly within an abstract class."*
- **JLS §8.4.7 « Method Body »** (p.302) :
  > *"The body of a method must be a semicolon if the method is abstract or native... It is a
  > compile-time error if a method declaration is neither abstract nor native and has a semicolon
  > for its body."*
  Fondement du corps `;` d'`abstract boolean verifierFonds();` en `Ex03`.
- **JLS §8.1.5 « Superinterfaces »** (p.261-263) : la clause `implements`, et
  > *"Unless the class being declared is abstract, all the abstract member methods of each direct
  > superinterface must be implemented... either by a declaration in this class or by an existing
  > method declaration inherited."*
  Fondement d'`Ex01` (implémentation obligatoire) et du tableau comparatif d'`Ex04`
  (« plusieurs interfaces », en écho à §8.1.4 pour la superclasse unique — déjà cité dans
  `c06_heritage`).
- **JLS §9.1 « Interface Declarations »** (p.353-354) et **§9.1.1.1** (p.355) :
  > *"An interface declaration specifies a new reference type... Every interface is implicitly
  > abstract."*
- **JLS §9.2 « Interface Members »** (p.360) : les membres d'une interface — déclarés, hérités,
  et les méthodes implicites héritées d'`Object`.
- **JLS §9.3 « Field (Constant) Declarations »** et **§9.3.1** (p.361-363) :
  > *"ConstantModifier: (one of) Annotation public static final"* — et *"Every declarator in a
  > field declaration of an interface must have a variable initializer, or a compile-time error
  > occurs."*
  Fondement d'`Ex02`.
- **JLS §9.4 « Method Declarations »** (p.364-365) :
  > *"A method in the body of an interface declaration may be declared public or private... If no
  > access modifier is given, the method is implicitly public... An interface method lacking a
  > private, default, or static modifier is implicitly abstract. Its body is represented by a
  > semicolon, not a block."*
  Fondement direct d'`Ex01` (`public abstract` implicite).

Couvert dans ce concept : §8.1.1.1, §8.4.3.1, §8.4.7, §8.1.5, §9.1/§9.1.1.1, §9.2, §9.3/§9.3.1,
§9.4 (partie « implicitement abstract » uniquement). Explicitement non couvert, renvoyé aux
concepts listés en « Hors scope » ci-dessus : §9.4 (`default`, `static`, `private` d'interface,
§9.4.1 résolution d'héritage/override), §9.1.1.4/§8.1.1.2 (`sealed`).

## Pistes d'exercices (Lesson Exercises)

Deux leçons `ocp-curriculum` distinctes s'appuient sur ce concept — pistes séparées pour chacune.

**`interfaces.md`** (prérequis `classes-objets` seul — pas encore `heritage`) :
- **Rappel/compréhension** — face à une interface dont un champ constant n'est pas initialisé à
  la déclaration, identifier pourquoi ça ne compile pas (JLS §9.3.1) plutôt que de supposer une
  valeur par défaut comme pour un champ de classe (§8.3.2) — teste la distinction champ de classe
  vs champ d'interface, source directe de confusion pour qui vient d'apprendre `c04_membres_statiques`.
- **Application** — étant donné une nouvelle interface à une seule méthode (ex. un contrat
  `Notifiable` avec `void notifier(String message)`), écrire une classe qui l'implémente,
  transférant le patron d'`Ex01`/`Exo01` à un nouveau domaine.
- **Transfert / cas limite** — deux classes sans aucun lien de superclasse commun (autre que
  `Object`) doivent partager un même comportement observable : faut-il une interface ou une
  classe abstraite ? Fait raisonner sur le rôle propre de l'interface — un contrat détaché de la
  hiérarchie d'héritage — avant même que `classes-abstraites.md` (leçon suivante) n'introduise
  l'alternative.

**`classes-abstraites.md`** (prérequis `heritage` + `interfaces`) :
- **Rappel/compréhension** — face à une classe abstraite avec une méthode abstraite et une
  méthode concrète qui l'appelle (comme `executer()` dans `Ex03`), expliquer pourquoi la classe
  abstraite elle-même ne peut pas être instantiée alors que sa méthode concrète, elle,
  s'exécute normalement une fois héritée par une sous-classe.
- **Application** — étant donné une nouvelle classe abstraite à une méthode abstraite et une
  méthode concrète (ex. un traitement avec une étape variable et une étape commune), écrire une
  sous-classe qui fournit l'implémentation manquante, transférant le patron d'`Ex03`/`Exo02`.
- **Transfert / cas limite** — plusieurs classes concrètes doivent à la fois partager un état
  commun (implémentation partielle) **et** respecter un contrat indépendant de cette hiérarchie
  (une interface) : faire identifier qu'une classe abstraite et une interface ne s'excluent pas
  — une classe abstraite peut elle-même `implements` une interface — et amorcer le pont vers
  `c17_heritage_multiple_types`.

## Pistes d'approfondissement (DeepDive)

**`interfaces.md`** :
- **Pourquoi Java interdit l'héritage multiple de classes mais autorise l'implémentation
  multiple d'interfaces** — rationale historique (éviter le « diamond problem » de C++ tout en
  gardant la possibilité de contrats multiples). Optionnel : angle historique/comparaison de
  langages, sans impact sur l'écriture d'une interface telle qu'enseignée ici.
- **Les interfaces marqueurs** (`Cloneable`, déjà rencontrée en `c14_clonage`, ou `Serializable`)
  — une interface sans aucune méthode, utilisée uniquement pour marquer un type à l'exécution
  (`instanceof`). Optionnel : cas particulier curieux, à l'opposé du contrat « normal » à une ou
  plusieurs méthodes qui est le cœur de la leçon.

**`classes-abstraites.md`** :
- **Une classe abstraite a un constructeur alors qu'elle ne peut jamais être instantiée
  directement** — pourquoi (il est invoqué implicitement via `super(...)` depuis chaque
  sous-classe, cf. `c08_mot_cle_super`) et à quoi il sert (initialiser l'état partagé). Optionnel :
  éclaire un point qui surprend souvent, mais ne conditionne pas la compréhension du mécanisme
  principal (méthode abstraite + sous-classe qui complète).
- **Skeletal implementation** (*Effective Java*, à proximité de l'Item 20 « Prefer interfaces to
  abstract classes ») — utiliser une classe abstraite comme implémentation partielle *à côté*
  d'une interface publique, plutôt qu'à sa place. Optionnel : angle conception/rationale, source
  non disponible localement (seul l'Item 18 est photographié dans `sources/Effective Java/`) —
  à ne développer que si une source vérifiable est trouvée.
