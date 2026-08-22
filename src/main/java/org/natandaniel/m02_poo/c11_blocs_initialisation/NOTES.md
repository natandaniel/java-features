# Blocs d'initialisation

> Module `m02_poo / c11_blocs_initialisation`
> Leçons : `Ex01_BlocDInstance`, `Ex02_BlocStatique`, `Ex03_OrdreComplet`, `Ex04_CasDUsage`
> Exercices : `Exo01_OrdreExecution`, `Exo02_UniciteDuBlocStatique`
> Prérequis : `c01_classes_objets`, `c04_membres_statiques` (distinction instance/`static`,
> reprise ici pour les deux formes de bloc) ; `c06_heritage` pour `Ex03_OrdreComplet` (ordre
> entre superclasse et sous-classe).

Un **bloc d'initialisation** est un bloc `{ ... }` déclaré directement dans le corps d'une
classe, en dehors de tout constructeur ou méthode. Il existe sous deux formes, qui n'ont ni la
même fréquence d'exécution ni le même rôle :

```java
class Employe {
    // Bloc d'initialisation d'instance (JLS §8.6) — exécuté à CHAQUE création d'instance.
    {
        System.out.println("nouvelle instance");
    }

    // Bloc d'initialisation statique (JLS §8.7) — exécuté UNE SEULE FOIS pour la classe.
    static {
        System.out.println("classe chargée");
    }
}
```

---

## 1. Le bloc d'instance : exécuté à chaque création, indépendamment du constructeur (`Ex01`)

Un bloc d'instance s'exécute avant le corps de **chaque** constructeur de la classe (JLS §12.5,
étape 6 avant étape 7) — y compris quand plusieurs constructeurs ne se chaînent pas entre eux via
`this(...)` (`c03_mot_cle_this`). C'est son intérêt principal : factoriser une initialisation
commune sans dépendre du chaînage des constructeurs, qui n'est pas toujours possible ou souhaité.

Un bloc d'instance peut assigner un champ `final` non encore initialisé (`matricule`,
`dateEmbauche` dans `Ex01`) — au même titre qu'un initialiseur de champ ou un constructeur, à
condition que chaque champ `final` soit assigné **exactement une fois** sur tout chemin
d'exécution (`c08_references_variables`/`m01_fondamentaux`).

## 2. Le bloc statique : exécuté une seule fois, à l'initialisation de la classe (`Ex02`)

Un bloc statique s'exécute au moment de l'**initialisation de la classe** (JLS §12.4), pas à
chaque instance. L'initialisation d'une classe est déclenchée par sa première "utilisation
active" (JLS §12.4.1) — entre autres la création d'une première instance, l'invocation d'une
méthode `static`, ou l'accès à un champ `static` non constant — et ne se reproduit jamais
ensuite, quel que soit le nombre d'instances créées par la suite.

## 3. Ordre complet avec héritage (`Ex03`)

Deux règles se combinent dès qu'une hiérarchie de classes est en jeu :

- **Initialisation de classe** (JLS §12.4.1) : quand une classe est initialisée, sa superclasse
  est initialisée en premier, si elle ne l'est pas déjà. Les blocs statiques d'une superclasse
  s'exécutent donc toujours avant ceux de sa sous-classe.
- **Création d'instance** (JLS §12.5) : la superclasse termine **entièrement** son
  initialisation (initialiseurs d'instance, blocs d'instance, corps du constructeur) avant que
  la sous-classe ne commence la sienne — invocation implicite ou explicite de `super(...)`
  d'abord (`c08_mot_cle_super`), étape 5 de la procédure JLS, avant l'étape 6.

Pour une première création de `Manager extends Employe` : `[static Employe]` → `[static
Manager]` → `[instance Employe]` → `[constructeur Employe]` → `[instance Manager]` →
`[constructeur Manager]`. Les blocs statiques n'apparaissent plus aux créations suivantes.

## 4. Pourquoi un bloc plutôt qu'un simple initialiseur de champ (`Ex04`)

Un initialiseur de champ (`static final Map<...> X = ...;`, JLS §8.3.2) n'accepte qu'**une seule
expression**. Un bloc statique accepte n'importe quelle suite d'instructions Java — boucle,
condition, gestion d'erreur. Dès qu'une constante de classe se construit par calcul (assembler
deux tableaux parallèles dans `Ex04`, par exemple), un simple `= ...` ne suffit plus : le bloc
statique devient le seul moyen de garder cette constante `static final` tout en la construisant
par un algorithme.

## Piège classique : confondre les deux blocs

La syntaxe ne diffère que par le mot-clé `static` en tête du bloc — une omission ou un ajout
involontaire change radicalement la fréquence d'exécution (à chaque instance vs une seule fois
pour la classe). C'est exactement ce que distinguent `Exo01` (bloc d'instance, une exécution par
création) et `Exo02` (bloc statique, une seule exécution malgré trois créations).

## Hors scope (concepts suivants ou hors périmètre)

- **Verrouillage concurrent de l'initialisation d'une classe** (JLS §12.4.2, étapes 1-12 de la
  procédure complète, non citées ici en entier) — plusieurs threads peuvent se disputer
  l'initialisation d'une même classe ; la JLS spécifie un protocole de verrou dédié. Relève de
  `m07_concurrence`, pas une mécanique d'initialisation en tant que telle.
- **`ExceptionInInitializerError`** — si un bloc statique ou un initialiseur de champ `static`
  lève une exception, la JLS (§12.4.2, étape 11) l'enveloppe dans une `ExceptionInInitializerError`
  et marque la classe comme "erroneous" pour tout usage ultérieur. Cas réel et documenté par la
  spec, mais laissé de côté ici : ce concept se concentre sur l'ordre d'exécution, pas la gestion
  d'erreur pendant l'initialisation (`m03_gestion_erreurs`, à venir).
- **Restriction de référence en avant** (JLS §8.3.3, mentionnée par §12.4) — un initialiseur
  statique ne peut pas référencer un champ `static` déclaré textuellement après lui. Renvoyée à
  `c08_references_variables`/`m01_fondamentaux`, où la portée et l'ordre textuel des déclarations
  sont déjà traités pour les variables.
- **Blocs d'initialisation dans les `enum`/`record`** — ces déclarations ont des règles
  particulières pour les constructeurs et initialiseurs (JLS §8.9, §8.10), pas encore traitées
  (`m02_poo/c20_enumerations`, `c21_records`, à venir).

## Ancrage dans la spec

- **JLS §8.6 « Instance Initializers »** (`jls26.pdf`, p.318 imprimée) :
  > *"An instance initializer declared in a class is executed when an instance of the class is
  > created (§12.5, §15.9, §8.8.7.1)."*
  Fondement direct d'`Ex01` : le bloc d'instance s'exécute à chaque création, indépendamment du
  constructeur choisi.
- **JLS §8.7 « Static Initializers »** (p.319 imprimée) :
  > *"A static initializer declared in a class is executed when the class is initialized
  > (§12.4.2). Together with any field initializers for class variables (§8.3.2), static
  > initializers may be used to initialize the class variables of the class."*
  Fondement direct d'`Ex02`/`Ex04` : exécution liée à l'initialisation de la **classe**, pas à
  l'instance.
- **JLS §12.4 « Initialization of Classes and Interfaces »**, §12.4.1 (p.446-447 imprimées) :
  > *"A class or interface T will be initialized immediately before the first occurrence of any
  > one of the following: — T is a class and an instance of T is created. — A static method
  > declared by T is invoked. — A static field declared by T is assigned. [...] When a class is
  > initialized, its superclasses are initialized (if they have not been previously
  > initialized) [...]. The static initializers and class variable initializers are executed in
  > textual order [...]."*
  Fondement de `Ex02` (déclenchement, exécution unique) et de la première moitié de `Ex03`
  (superclasse initialisée avant sous-classe).
- **JLS §12.5 « Creation of New Class Instances »** (p.451-453 imprimées) — procédure en 7
  étapes pour initialiser un nouvel objet ; étapes citées telles quelles :
  > *"5. If this constructor is for a class other than Object, then this constructor contains an
  > implicit invocation of a superclass constructor with no arguments. [...] 6. Execute the
  > instance initializers and instance variable initializers for this class [...] in the
  > left-to-right order in which they appear textually in the source code for the class. [...]
  > 7. Execute the BlockStatements, if any, of the epilogue of this constructor."*
  Fondement direct d'`Ex01` (étape 6 avant 7) et de la seconde moitié d'`Ex03` (étape 5 — la
  superclasse termine son propre cycle 5-7 avant que la sous-classe ne poursuive au sien).

## Pistes d'exercices (Lesson Exercises)

- **Rappel/compréhension** — pourquoi un bloc d'instance placé dans une classe à deux
  constructeurs non chaînés (`this(...)`) évite la duplication, et pourquoi il s'exécute
  systématiquement avant le corps de chacun des deux. Reprend directement `Ex01`, en premier
  car c'est le point de départ du raisonnement (le bloc comme partage entre constructeurs
  indépendants).
- **Application** — écrire une classe `Facture` avec un bloc statique construisant, par boucle,
  une table `Map` figée des taux de TVA par pays. Transfert mécanique direct du patron
  `Ex02`/`Ex04` (bloc statique nécessaire car construction par calcul) vers un nouveau domaine —
  vient après le rappel, qui ne portait que sur le diagnostic.
- **Transfert / cas limite** — une hiérarchie à **trois** niveaux (`Vehicule` → `Voiture` →
  `VoitureElectrique`) : prédire l'ordre exact des 3 blocs statiques + 3 blocs d'instance + 3
  constructeurs à la première création. Généralise la règle vue dans `Ex03` (2 niveaux) à un
  niveau supplémentaire — vient en dernier, car il exige d'avoir déjà intégré la règle avant de
  la généraliser.

## Pistes d'approfondissement (DeepDive)

- **`ExceptionInInitializerError`** — ce qu'il se passe quand un bloc statique lève une
  exception (JLS §12.4.2, étape 11) : la classe devient définitivement "erroneous" pour tout
  usage ultérieur. Optionnel : cas d'erreur documenté par la spec, mais qui déborde sur la
  gestion d'erreur (`m03_gestion_erreurs`, pas encore traitée), pas indispensable pour
  comprendre l'ordre d'exécution normal.
- **Verrouillage concurrent de l'initialisation de classe** — deux threads qui s'initialisent
  mutuellement peuvent interbloquer (JLS §12.4.2). Optionnel : angle concurrence
  (`m07_concurrence`, à venir), aucune notion de concurrence n'est un prérequis de ce concept.
- **Restriction de référence en avant entre initialiseurs statiques** (JLS §8.3.3) — pourquoi un
  initialiseur/bloc statique ne peut pas lire un champ `static` déclaré textuellement après lui,
  alors qu'une méthode ou un bloc d'instance le peut sans problème. Optionnel : nuance de portée
  qui affine `c08_references_variables`, pas nécessaire pour utiliser correctement un bloc
  d'initialisation.
