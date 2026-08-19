# ROADMAP — java-features

Catalogue pédagogique des fonctionnalités Java, support d'une plateforme de cours en ligne.
Organisation **par concept** (progression débutant → avancé) ; la version Java d'introduction
est **annotée dans le code** (`// @since Java N`) et dans les `NOTES.md`, jamais dans l'arborescence.

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

### m01_fondamentaux — Types, valeurs et variables (Java 1, annotations Java 7/8 ponctuelles)

Ordre pensé en **quatre blocs** pour respecter les prérequis (chaque concept ne dépend que de ce qui précède) :

| Bloc | Ordre | Concept | Contenu | Statut |
|------|-------|---------|---------|--------|
| **A · Système de types** | c01 | types_primitifs | les 8 types, tailles, valeurs par défaut | ✅ |
| | c02 | representation_binaire | binaire, complément à deux (théorie + exo arithmétique) | ✅ |
| | c03 | litteraux | notations déc/hex/oct/`0b`, underscores (Java 7), suffixes, char (notation) | ✅ |
| **B · Calcul sur les entiers** | c04 | operateurs | `c01_arithmetiques` · `c02_bit_et_decalages` | ✅ |
| | c05 | debordement | overflow silencieux, pièges, arithmétique sûre, BigInteger | ✅ |
| **C · Flottants & conversions** | c06 | flottants | IEEE 754, précision, Infinity, NaN | ✅ |
| | c07 | conversions | widening, narrowing, promotion (unifiée ici) | ✅ |
| **D · Références, variables & mémoire** | c08 | references_variables | null, `==` vs `equals`, pool, 6 kinds, `final`, passage par valeur | ✅ |
| | c09 | modele_memoire | pile/tas/metaspace, aliasing, allocation, durée de vie, GC, fuites | ✅ |
| | c10 | execution | source/bytecode/processus, lancement (fork+exec), mémoire virtuelle, JVM comme processus, isolation, copy-on-write, processus vs threads | ✅ |

**`m01_fondamentaux` est complet** : les dix concepts ont leçon + exercices + solutions + tests + NOTES.

**Note d'organisation** — La représentation binaire est un concept à part entière (c02), *avant* les opérateurs, car elle est le prérequis des opérations bit à bit/décalages, du débordement et des conversions. Bit-à-bit et décalages sont fusionnés (`c02_bit_et_decalages`) car mutuellement dépendants. Les flottants (c06) précèdent les conversions (c07), dont le widening met en jeu `float`/`double`. La promotion de type est traitée en un seul endroit (c07). Références et variables sont fusionnés (c08) : modèle puis conséquences.

### m02_poo — Classes, objets et héritage (Java 1, annotations ponctuelles)

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | classes_objets | classe vs objet, constructeurs, `this`, `static` | ✅ |
| c02 | encapsulation | champs privés/accesseurs, invariants, modificateurs d'accès | ✅ |
| c03 | heritage | `extends`, superclasse implicite `Object`, transitivité, restrictions | ✅ |

**Note d'organisation** — `classes_objets` et `encapsulation` précèdent `heritage` car ce sont
ses prérequis directs (héritage suppose une classe déjà définie, avec un état à protéger).
Override et polymorphisme, hors scope de `heritage`, restent à ajouter en concepts suivants du
module (interfaces, énumérations, records à venir aussi).

### Modules à venir (ordre indicatif)

| Module | Thème | Versions clés |
|--------|-------|---------------|
| m03_generiques | génériques, wildcards, type erasure | Java 5 |
| m04_collections | Collections Framework, itérateurs | Java 1.2, 5 (for-each) |
| m05_fonctionnel | lambdas, Stream API, Optional | Java 8 |
| m06_modernite | var, switch expressions, sealed, pattern matching, virtual threads | Java 10 → 21+ |

## Comment ajouter un concept

1. Créer `mNN_module/cNN_concept/` avec `lecon/`, `exercices/`, `solutions/`, `NOTES.md`.
2. Leçons graduées (une idée par fichier), exercices = méthodes pures testables, solutions corrigées.
3. Test JUnit miroir sous `src/test/java/.../solutions/` ciblant les solutions.
4. Annoter la version d'introduction des fonctionnalités (`// @since Java N`).
5. `mvn clean test` doit rester vert. Mettre à jour ce ROADMAP (statut).
