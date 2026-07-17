# Idées de cours Java

Pile d'idées pour de futurs cours/contenus Java, à produire après le projet d'exploration `java-features`.
Chaque entrée note le **principe général** de l'idée. La pile est réorganisée au fil des idées nouvelles.

## Fondations / coulisses

### Métaphysique d'un programme Java (cours-préambule)
- **Principe** : cours d'ouverture, en amont de tous les cours sur le langage, qui explique ce qui se passe « en coulisses » quand un programme Java s'exécute — du shell au CPU. Couvre processus, shell, noyau, RAM, CPU, appels système, PID, JVM et ses zones mémoire (tas, pile, metaspace), adressage virtuel. Objectif : poser le socle mental qui rendra compréhensibles plus tard les erreurs `StackOverflowError`, `OutOfMemoryError` et les fuites mémoire.
- _ajoutée le 2026-06-28_

### Le terminal n'est qu'un proxy branché sur le process
- **Principe** : démystifier le terminal pour le débutant. Un hello world « s'affiche dans le terminal » non parce que tout programme Java serait un programme terminal, mais parce que le terminal au premier plan est branché sur la sortie standard du process JVM (comme il est un proxy sur le shell). En arrière-plan (`&`), on ne voit rien ; et un programme Java peut aussi ouvrir sa propre fenêtre graphique. Casser l'illusion « Java = terminal ».
- **Fond** : stdout/stderr/stdin comme trois « tuyaux » branchant terminal et process ; redirection (`> fichier`, `|`) montrant que le terminal n'est qu'un destinataire parmi d'autres ; premier plan vs arrière-plan (`&`, `nohup`, SIGHUP) ; piège « fermer le terminal tue toujours le programme » (vrai au premier plan, faux si détaché).
- **Forme** : article ou vidéo courte (5–8 min), une seule idée, en capsule juste après le premier hello world. Schéma central « Terminal ↔ Process JVM » relié par trois flèches stdin/stdout/stderr, décliné en variantes (flèche coupée = background, déviée = redirection). Démo live en 3 temps : `java Hello` (texte) → `java Hello &` (rien) → fenêtre graphique.
- **Style** : grand débutant juste après le premier run. Nommer la fausse croyance à voix haute (« tu crois que tout programme Java vit dans le terminal — démontons ça ») ; analogie de l'écran/prise (débrancher l'écran ≠ éteindre la machine) ; garder la charge cognitive basse en restant sur une seule nuance (terminal = proxy), repousser PID/signaux vers « Métaphysique ».
- _ajoutée le 2026-06-28_

### Un même `main()`, deux sorties : terminal ou fenêtre
- **Principe** : la JVM ne « sait » pas si elle tourne dans un terminal — c'est le code qui décide de sa sortie. Un même `main()` peut au choix faire des `println` (texte, terminal) ou ouvrir une fenêtre graphique (Swing/JavaFX). Montre que terminal et interface graphique sont deux débouchés du même programme, et ouvre la porte aux cours UI.
- _ajoutée le 2026-06-28_
