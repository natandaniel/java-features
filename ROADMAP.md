# ROADMAP — java-features

Catalogue pédagogique des fonctionnalités Java, support d'une plateforme de cours en ligne.
Organisation **par concept** (progression débutant → avancé) ; la version Java d'introduction
est **annotée dans le code** (`// @since Java N`) et dans les `NOTES.md`, jamais dans l'arborescence.

## Source de vérité : `THEMES_JAVA.md`

La structure de ce catalogue suit le **Domaine I — « Le langage Java »** de
`../online-course-platform-project/THEMES_JAVA.md` (sous-thèmes lettrés A à N). Chaque item de ce
domaine doit avoir, à terme, un ou plusieurs concepts `mNN_module/cNN_concept` correspondants ici,
avec code repris tel quel par `ocp-curriculum` et sourcé (JLS en priorité). La correspondance est
**many-to-one dans un sens comme dans l'autre** : un item du thème peut se scinder en plusieurs
concepts (ex. « Héritage et polymorphisme » → `c06_heritage` + `c07_polymorphisme`), et un concept
peut couvrir plusieurs items proches.

**Hors périmètre, explicitement** : les domaines II à XI de `THEMES_JAVA.md` (ingénierie logicielle,
conception/architecture, écosystème Spring, données, tests avancés, sécurité, infra/cloud,
observabilité, systèmes distribués, IA appliquée) ne concernent pas ce dépôt — ce sont des sujets
d'ingénierie logicielle générale, pas des fonctionnalités du **langage** Java, et ils ont leurs
propres dépôts frères (`software-architecture`, `software-engineering`,
`ref-spring-boot-rest-api-project`, etc.). Le « Domaine 0 » (hors périmètre, socle machine
agnostique au langage) est également exclu, pour la même raison, telle que documentée dans
`THEMES_JAVA.md` lui-même.

## Convention d'arborescence

```
org.natandaniel/
└── mNN_<module>/             module = grand domaine (ordre = NN)
    └── cNN_<concept>/         concept = une notion (peut contenir des sous-concepts cNN_)
        ├── lecon/             Ex01_*, Ex02_*  → démos exécutables graduées (main + commentaires)
        ├── exercices/         Exo01_*         → méthodes-stub à compléter (testables)
        ├── solutions/         Exo01_*         → corrigés de référence
        └── NOTES.md           le « cours »
```
Les tests JUnit miroitent les solutions sous `src/test/java/.../solutions/` et valident les corrigés
(ils servent aussi de spécification aux exercices). `mvn clean test` doit rester vert.

## Progression

Un module par lettre du Domaine I, dans l'ordre du document — sauf le thème C (Modèle mémoire et
exécution), fusionné dans `m01_fondamentaux` (voir note du bloc F ci-dessous) plutôt que dupliqué
dans un module séparé.

Statuts : ✅ fait · 🔶 partiel (contenu existant mais pas un concept dédié) · ⬜ à faire.

### m01_fondamentaux — Thème A (Fondamentaux) + Thème C (Modèle mémoire et exécution)

Une seule table, chaque concept annoté de l'item `THEMES_JAVA.md` (lettre A ou C) qu'il réalise —
« approfondissement » quand le concept creuse un aspect Java-spécifique de l'item plutôt que de
l'introduire au sens le plus littéral. Les 10 premiers concepts sont groupés en 4 blocs (ordre de
prérequis, inchangé) ; c11-c14 sont l'écart comblé le 2026-08-21 (agrégés/ajoutés pour matcher
exactement le contenu du thème, sans concept orphelin ni item non couvert).

| Bloc | Ordre | Concept | Item(s) `THEMES_JAVA.md` | Contenu | Statut |
|------|-------|---------|--------------------------|---------|--------|
| **A · Système de types** | c01 | types_primitifs | A · Syntaxe et types | les 8 types, tailles, valeurs par défaut | ✅ |
| | c02 | representation_binaire | A · Syntaxe et types (approfondissement) | binaire, complément à deux | ✅ |
| | c03 | litteraux | A · Syntaxe et types | notations déc/hex/oct/`0b`, underscores (Java 7), suffixes, char | ✅ |
| **B · Calcul sur les entiers** | c04 | operateurs | A · Syntaxe et types (approfondissement) | `c01_arithmetiques` · `c02_bit_et_decalages` | ✅ |
| | c05 | debordement | A · Syntaxe et types (approfondissement) | overflow silencieux, pièges, arithmétique sûre, BigInteger | ✅ |
| **C · Flottants & conversions** | c06 | flottants | A · Syntaxe et types (approfondissement) | IEEE 754, précision, Infinity, NaN | ✅ |
| | c07 | conversions | A · Syntaxe et types (approfondissement) | widening, narrowing, promotion (unifiée ici) | ✅ |
| **D · Références, variables & mémoire** | c08 | references_variables | A · Variables et portée + C · Références, égalité, immutabilité | null, `==` vs `equals`, pool, 6 kinds, `final`, passage par valeur | ✅ |
| | c09 | modele_memoire | C · Pile/tas/metaspace + C · Ramasse-miettes | pile/tas/metaspace, aliasing, allocation, durée de vie, GC, fuites | ✅ |
| | c10 | execution | C · JVM et bytecode (partiel — pas un cours dédié au format bytecode) | source/bytecode/processus, lancement (fork+exec), mémoire virtuelle, JVM comme processus, isolation, copy-on-write, processus vs threads | 🔶 |
| **E · Fondamentaux impératifs** (écart comblé 2026-08-21) | c11 | structures_controle | A · Structures de contrôle + A · Variables et portée (blocs/shadowing) | `if`/`else`, `switch` (statement), `for`/`while`/`do-while`, portée de bloc, `break`/`continue` | ✅ |
| | c12 | tableaux_et_chaines | A · Tableaux et chaînes de caractères | tableaux (objets, taille fixe, covariance, `ArrayStoreException`) ; `String` (immutabilité, pool — renvoi c08, `StringBuilder`, comparaison `==`/`equals`) — **fusionné** (le thème liste les deux dans un seul item) | ✅ |
| | c13 | methodes | A · Méthodes et structuration d'un programme | déclaration, paramètres/retour, résolution de surcharge (renvoi `m02_poo/c02_constructeurs`), varargs | ✅ |
| **F · Modèle mémoire, complément** | c14 | chargement_classes | C · Chargement de classes | class loaders (bootstrap/platform/app), modèle de délégation, chargement dynamique (renvoi c10) | ⬜ |

**Couverture exacte des thèmes A + C** : les 5 items de A (Syntaxe et types, Variables et portée,
Structures de contrôle, Méthodes et structuration, Tableaux et chaînes) et les 5 items de C (JVM et
bytecode, Chargement de classes, Pile/tas/metaspace, GC, Références/égalité/immutabilité) ont chacun
au moins un concept qui les couvre — aucun n'est orphelin, aucun concept de `m01` n'est hors thème.

**`m01_fondamentaux` reste marqué complet pour les blocs A-D** (10 concepts, déjà leçon + exercices +
solutions + tests + NOTES) ; les blocs E et F (c11-c14) sont l'écart à combler, à traiter concept par
concept selon la procédure habituelle.

### m02_poo — Thème B (Programmation orientée objet)

**Déjà en place :**

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | classes_objets | classe vs objet, instanciation | ✅ |
| c02 | constructeurs | constructeur explicite, disparition de l'implicite, surcharge | ✅ |
| c03 | mot_cle_this | `this.champ` vs paramètre, chaînage `this(...)` | ✅ |
| c04 | membres_statiques | champs/méthodes `static` vs membres d'instance | ✅ |
| c05 | encapsulation | champs privés/accesseurs, invariants, modificateurs d'accès | ✅ |
| c06 | heritage | `extends`, superclasse implicite `Object`, transitivité, restrictions | ✅ |
| c07 | polymorphisme | redéfinition (override), retour covariant, masquage (champs/`static`), liaison dynamique | ✅ |
| c08 | mot_cle_super | `super(...)` (invocation), `super.champ`/`super.methode()` (accès masqué/redéfini), ordre d'exécution | ✅ |
| c09 | classe_object | `equals`/`hashCode`/`toString` hérités d'`Object`, comportement par défaut, contrat de redéfinition | ✅ |

**Écart identifié le 2026-08-21 face à `THEMES_JAVA.md` — items du thème B non encore couverts,
classés en ordre de prérequis :**

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c10 | methodes_fabriques_statiques | méthodes fabriques statiques (`of`, `valueOf`...), vs constructeur public | ✅ |
| c11 | blocs_initialisation | blocs d'initialisation d'instance et statiques, ordre avec constructeurs | ✅ |
| c12 | final_classe_methode | `final` sur une classe (non extensible) ou une méthode (non redéfinissable) | ✅ |
| c13 | classes_immuables | champs `final`, pas de mutateurs, copie défensive — s'appuie sur c05 + c12 | ⬜ |
| c14 | clonage | `Cloneable`, `Object.clone()`, copie superficielle vs profonde, pièges | ⬜ |
| c15 | interfaces_classes_abstraites | `interface` vs classe abstraite, méthodes abstraites, contrats | ⬜ |
| c16 | methodes_par_defaut | méthodes `default` d'interface (Java 8), résolution de conflit | ⬜ |
| c17 | heritage_multiple_types | une classe implémente plusieurs interfaces — s'appuie sur c15 | ⬜ |
| c18 | interface_comparable | `Comparable<T>` — **⚠ dépend des génériques** (`m05_generiques`, thème F, plus loin dans l'ordre des modules) ; à traiter en dernier dans le module, ou avec une introduction minimale aux génériques différée depuis `m05` | ⬜ |
| c19 | classes_scellees | `sealed`/`permits`/`non-sealed` — rattachée ici (pas à un module « modernité ») suite à l'audit `THEMES_JAVA.md` du 2026-08-09 qui l'a déplacée de I.L vers I.B | ⬜ |
| c20 | enumerations | `enum`, corps de constante, implémentation d'interface — s'appuie sur c15 | ⬜ |
| c21 | records | `record`, constructeur canonique, composants — s'appuie sur c13 (immutabilité) et c15 | ⬜ |
| c22 | classes_imbriquees_statiques | classes imbriquées statiques | ⬜ |
| c23 | classes_internes | classes internes (non statiques), classes locales/anonymes — s'appuie sur c22 | ⬜ |
| c24 | composition_vs_heritage | synthèse — favoriser la composition, limites de l'héritage | ⬜ |

**Note d'organisation (héritage historique, inchangée)** — `classes_objets`, `constructeurs`,
`mot_cle_this` et `membres_statiques` formaient à l'origine un seul concept fusionné ; ils ont été
scindés en quatre (2026-08-21) pour correspondre à la granularité des leçons `ocp-curriculum`.
`classes_objets` et `encapsulation` précèdent `heritage` (prérequis directs). `polymorphisme`
s'appuie sur `heritage`. `mot_cle_super` s'appuie sur les quatre premiers. `classe_object` s'appuie
sur `heritage` et `polymorphisme`.

### m03_gestion_erreurs — Thème D (Gestion des erreurs)

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | exceptions_checked_unchecked | hiérarchie `Throwable`, checked vs unchecked, `throws`, `try`/`catch`/`finally` | ⬜ |
| c02 | try_with_resources | `AutoCloseable`, fermeture garantie, suppression d'exceptions | ⬜ |
| c03 | strategies_gestion_erreur | quand catcher/propager/wrapper, exceptions personnalisées | ⬜ |

### m04_collections — Thème E (Collections et structures de données)

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | collections_framework | `List`/`Set`/`Map`/`Queue`, implémentations courantes | ⬜ |
| c02 | iterateurs_comparateurs | `Iterator`, for-each, `Comparator`, tri | ⬜ |
| c03 | structures_avancees | `TreeMap`, `PriorityQueue`, `Deque` | ⬜ |

### m05_generiques — Thème F (Génériques et sûreté de type)

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | generiques | classes/méthodes génériques, bornes | ⬜ |
| c02 | wildcards | `? extends`/`? super`, PECS | ⬜ |
| c03 | type_erasure | effacement de type, conséquences, limites | ⬜ |

### m06_fonctionnel — Thème G (Programmation fonctionnelle)

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | lambdas_references_methodes | interfaces fonctionnelles, lambdas, `::` | ⬜ |
| c02 | stream_api | pipeline, opérations intermédiaires/terminales | ⬜ |
| c03 | optional | `Optional<T>`, éviter `null` | ⬜ |

### m07_concurrence — Thème H (Concurrence)

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | threads_synchronisation | `Thread`, `synchronized`, sections critiques | ⬜ |
| c02 | executors_pools_threads | `ExecutorService`, pools | ⬜ |
| c03 | java_util_concurrent | atomics, collections concurrentes | ⬜ |
| c04 | futures_completablefuture | `Future`, `CompletableFuture`, composition async | ⬜ |
| c05 | virtual_threads | Project Loom (Java 21) — déplacé ici depuis l'ancien module « modernité » | ⬜ |
| c06 | patterns_concurrence | producteur/consommateur, autres patterns | ⬜ |

### m08_entrees_sorties — Thème I (Entrées/sorties)

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | io_classique | `InputStream`/`OutputStream`, `Reader`/`Writer` | ⬜ |
| c02 | nio | `java.nio`, buffers, channels | ⬜ |
| c03 | fichiers_systeme_fichiers | `java.nio.file`, `Path`, `Files` | ⬜ |
| c04 | serialisation | `Serializable`, risques, alternatives | ⬜ |

### m09_date_temps — Thème J (Date et temps)

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | api_java_time | `LocalDate`/`LocalDateTime`/`ZonedDateTime`, `Duration`/`Period` (Java 8) | ⬜ |

### m10_reflexion — Thème K (Réflexion et métaprogrammation)

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | annotations | annotations standard et personnalisées | ⬜ |
| c02 | reflexion | `java.lang.reflect`, introspection à l'exécution | ⬜ |
| c03 | annotation_processing | génération de code par traitement d'annotations | ⬜ |

### m11_modernite — Thème L (Fonctionnalités modernes du langage)

Recentré sur son périmètre réel après l'audit `THEMES_JAVA.md` du 2026-08-09 : `sealed` a été
déplacé vers `m02_poo` (thème B) et les threads virtuels vers `m07_concurrence` (thème H) — ce
module ne couvre plus que la syntaxe/le langage à proprement parler.

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | var | inférence de type locale (Java 10) | ⬜ |
| c02 | switch_expressions | `switch` en expression, `yield`, flèches (Java 14) | ⬜ |
| c03 | pattern_matching | `instanceof` pattern, `switch` pattern, record patterns | ⬜ |
| c04 | modules_jpms | Java Platform Module System (Java 9) | ⬜ |

### m12_jvm_outillage — Thème M (JVM interne et outillage bas niveau)

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | tuning_gc | paramétrage et choix de collecteur | ⬜ |
| c02 | profiling_jfr | Java Flight Recorder | ⬜ |
| c03 | manipulation_bytecode | ASM, ByteBuddy | ⬜ |
| c04 | compilation_native | GraalVM native image | ⬜ |

### m13_algorithmique — Thème N (Algorithmique et structures de données)

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | complexite_big_o | notation Big O | ⬜ |
| c02 | structures_classiques | piles, files, arbres, graphes | ⬜ |
| c03 | tri_recherche | algorithmes de tri et de recherche | ⬜ |
| c04 | recursion | récursion, cas de base, pile d'appels | ⬜ |

## Comment ajouter un concept

1. Vérifier dans `../online-course-platform-project/THEMES_JAVA.md` (Domaine I) que l'item existe
   et repérer sa lettre — la table de progression ci-dessus doit déjà lui réserver une place.
2. Créer `mNN_module/cNN_concept/` avec `lecon/`, `exercices/`, `solutions/`, `NOTES.md`.
3. Leçons graduées (une idée par fichier), exercices = méthodes pures testables, solutions corrigées.
4. Test JUnit miroir sous `src/test/java/.../solutions/` ciblant les solutions.
5. Annoter la version d'introduction des fonctionnalités (`// @since Java N`).
6. `mvn clean test` doit rester vert. Mettre à jour ce ROADMAP (statut ⬜ → ✅).
