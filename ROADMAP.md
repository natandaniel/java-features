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

| Ordre | Concept | Contenu | Statut |
|-------|---------|---------|--------|
| c01 | types_primitifs | les 8 types, tailles, valeurs par défaut, représentation binaire | ✅ |
| c02 | litteraux | notations déc/hex/oct/`0b`, underscores (Java 7), suffixes, char | ✅ |
| c03 | operateurs | arithmétiques · bit-à-bit · décalages | ✅ |
| c04 | debordement | overflow silencieux, pièges, arithmétique sûre, BigInteger | ✅ |
| c05 | conversions | widening, narrowing, promotion en expression | ✅ |
| c06 | flottants | IEEE 754, précision, Infinity, NaN | ✅ |
| c07 | references | null, `==` vs `equals`, String pool | ✅ |
| c08 | variables | 6 kinds of variables, `final`, passage par valeur | ✅ |
| c09 | modele_memoire | pile/tas/metaspace, allocation, durée de vie, GC, fuites | 🟡 notes seules |
| c10 | execution | source/bytecode/processus, lancement (fork+exec), mémoire virtuelle, JVM comme processus, isolation, copy-on-write, processus vs threads | 🟡 notes seules |

> 🟡 = `NOTES.md` rédigée ; leçon/exercices/solutions/tests à produire (`java-mentor`).

### Modules à venir (ordre indicatif)

| Module | Thème | Versions clés |
|--------|-------|---------------|
| m02_poo | classes, interfaces, héritage, énumérations, records | Java 1, 5 (enum), 16 (record) |
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
