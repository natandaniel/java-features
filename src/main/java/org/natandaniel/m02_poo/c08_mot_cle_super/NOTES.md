# Le mot-clé super

> Module `m02_poo / c08_mot_cle_super`
> Leçons : `Ex01_InvocationSuperConstructeur`, `Ex02_AccesSuperMembre`, `Ex03_OrdreExecution`
> Exercices : `Exo01_ChampMasqueParSuper`, `Exo02_MethodeEtendueParSuper`
> Prérequis : `c02_constructeurs`, `c03_mot_cle_this`, `c06_heritage`, `c07_polymorphisme`

`super` désigne, depuis le corps d'une sous-classe, la superclasse directe — pour en invoquer
le constructeur, ou pour accéder explicitement à un de ses membres masqué ou redéfini. **Hors
scope ici** : `this(...)`/chaînage de constructeurs (déjà traité dans `c03_mot_cle_this`),
`T.super.identifiant` qualifié (suppose les classes internes, non traitées), et la nuance
*prologue* devant `super(...)` (JEP 513, différée à `m06_modernite`, même choix que pour
`this(...)`).

---

## `super(...)` : invocation du constructeur de la superclasse (JLS §8.8.7, §8.8.7.1)

Un corps de constructeur peut contenir **au plus une** invocation explicite — `this(...)`
(chaîne vers un autre constructeur de la même classe, `c03_mot_cle_this`) ou `super(...)`
(invoque un constructeur de la superclasse directe) — jamais les deux : la grammaire ne
l'autorise pas (`ConstructorBody`, §8.8.7).

Si un constructeur **n'en contient aucune**, le compilateur insère implicitement
`super();` en première instruction — silencieusement, à condition que la superclasse ait un
constructeur sans argument accessible. Si ce n'est pas le cas (superclasse avec uniquement des
constructeurs paramétrés, ex. `ProduitPhysique(String nom, double prix)`), l'insertion
implicite échoue et c'est une **erreur de compilation** : `super(...)` explicite devient
obligatoire.

Comme `this(...)`, une invocation `super(...)` doit être la première instruction du corps
(convention retenue à ce stade — la nuance complète, un *prologue* d'instructions pouvant la
précéder, est un changement récent de la spec différé à `m06_modernite`, JEP 513/JEP 447).

## `super.champ` : accès à un champ masqué (JLS §15.11.2)

Un champ d'une sous-classe qui porte le même nom qu'un champ de la superclasse ne le
**redéfinit** pas (les champs ne sont jamais polymorphes, `c07_polymorphisme`) : il le
**masque**. `super.champ` contourne ce masquage et accède explicitement à la version de la
superclasse, inaccessible autrement par un simple `champ` ou `this.champ` (qui désignent tous
deux la version masquante).

## `super.methode()` : étendre plutôt que remplacer (JLS §15.12.1)

Contrairement à un champ, une méthode d'instance redéfinie n'a pas de version « inaccessible » —
sans `super`, on ne peut qu'appeler la version redéfinie (dispatch dynamique,
`c07_polymorphisme`). `super.methode()` permet à une redéfinition d'**appeler explicitement** la
version de la superclasse plutôt que de dupliquer son code : le pattern classique consiste à
récupérer le résultat de `super.methode()` puis à l'étendre, au lieu de réécrire entièrement le
comportement hérité. Sans objet pour un champ, qui n'a pas de dispatch à contourner — c'est
précisément la différence entre masquage (champs, résolu à la compilation) et redéfinition
(méthodes d'instance, résolue à l'exécution).

## Ordre d'exécution (JLS §8.8.7.1, point 3)

Le constructeur de la superclasse (implicite ou explicite) s'exécute **intégralement** avant
que les initialiseurs d'instance/de champ de la sous-classe ne s'exécutent, eux-mêmes avant le
reste du corps du constructeur de la sous-classe (l'épilogue, après l'invocation). Cet ordre
vaut que `super()` soit écrit ou inséré implicitement — seule la présence à l'écran change, pas
la séquence.

## Hors scope (concepts suivants)

- **`T.super.identifiant`** (qualifié, JLS §15.11.2, §15.12.1) : résout un membre via une
  instance englobante, pertinent seulement pour les classes internes — non traitées dans le
  catalogue à ce stade.
- **Prologue devant `super(...)`/`this(...)`** (*early construction context*, JEP 513/JEP 447,
  Flexible Constructor Bodies) : la spec SE26 autorise déjà des instructions avant l'invocation
  explicite sous restrictions ; différé à `m06_modernite`, même choix que `c03_mot_cle_this`.
- **`super` en contexte d'interface (méthodes `default`)** : suppose les interfaces, non
  traitées dans le catalogue à ce stade (déjà signalé hors scope dans `c07_polymorphisme`).

## Ancrage dans la spec

Sourcé sur `jls26.pdf` (édition SE26, local — voir mémoire `jls_table_of_contents`) :
- **§8.8.7** (p.323-324) — corps de constructeur, grammaire (prologue/`ConstructorInvocation`/
  épilogue), insertion implicite de `super();`.
- **§8.8.7.1** (p.325-329) — invocations `this(...)`/`super(...)` non qualifiées et qualifiées
  (qualifiées : classes internes, hors scope), ordre d'exécution (constructeur superclasse puis
  initialiseurs d'instance de la sous-classe).
- **§15.11.2** (p.626) — `super.Identifier` pour un champ masqué ; erreur de compilation si
  `Object`/interface ou contexte `static`/*early construction context*.
- **§15.12.1** (p.629), 4ᵉ cas — `super.Identifier(...)` : type à rechercher = superclasse
  directe.

## Pistes d'exercices (Lesson Exercises)

- **Rappel** — extrait avec une superclasse sans constructeur sans argument ; identifier
  pourquoi omettre `super(...)` dans la sous-classe est une erreur de compilation. Teste la
  condition de l'insertion implicite (elle échoue si la superclasse n'a rien à insérer).
- **Application** — compléter une redéfinition pour qu'elle appelle `super.methode()` et étende
  le résultat plutôt que dupliquer le code hérité (transfert direct de `Ex02`/`Exo02`). Vient
  après le rappel, qui ne porte que sur le constructeur.
- **Transfert** — variante de l'exemple JLS §15.11.2 (`T1`/`T2`/`T3`, chaîne de masquage sur
  trois niveaux) : prédire `super.x` vs `x` depuis la classe la plus dérivée. Teste un cas
  limite réel — `super.` ne remonte qu'**un seul** niveau, jamais toute la chaîne — plutôt
  qu'une simple relecture de syntaxe ; vient en dernier, une fois le mécanisme de base acquis.

## Pistes d'approfondissement (DeepDive)

- **JEP 513/447 (Flexible Constructor Bodies)** — pourquoi un prologue d'instructions est
  désormais permis avant `super(...)`/`this(...)`. Optionnel : la convention « première
  instruction » suffit à ce niveau ; la nuance complète est reprise dans `m06_modernite`.
- **`T.super.identifiant`** — la forme qualifiée, pertinente pour les classes internes. Optionnel :
  aucune classe interne n'a encore été traitée dans le catalogue, angle syntaxique rare sans
  impact sur la compréhension de `super` telle qu'utilisée ici.
- **Pourquoi pas de `super.super`** — un seul niveau de remontée possible en Java, contrairement
  à des langages qui autorisent l'appel explicite à n'importe quel ancêtre de la chaîne.
  Optionnel : angle comparatif entre langages, non nécessaire pour utiliser `super` correctement.
