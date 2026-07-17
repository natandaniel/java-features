# Exécution d'un programme Java

> Module `m01_fondamentaux / c10_execution`
> Leçon / exercices / solutions / tests : **à produire** (`java-mentor`).
> Voir aussi : [`c09_modele_memoire`](../c09_modele_memoire/NOTES.md) (mémoire **interne** de la JVM).

Un programme Java existe sous trois formes successives : un fichier source (`.java`, texte inerte), du bytecode (`.class`, produit par `javac`, inerte aussi), et un **processus** — une instance vivante chargée en mémoire par le système d'exploitation. Cette fiche décrit le passage du fichier au processus, au niveau OS. Elle se situe **au-dessus** de `c09_modele_memoire` : ici on décrit le processus qui contient le heap, là-bas le heap lui-même.

## Trois formes d'un programme

| Forme | Produite par | Nature | Lieu |
|-------|-------------|--------|------|
| `Main.java` | l'humain | texte source, inerte | système de fichiers |
| `Main.class` | `javac` | bytecode, inerte | système de fichiers |
| Processus JVM | le noyau, à `java Main` | instance vivante | RAM + CPU |

- Le `.java` et le `.class` sont des **fichiers** : des octets statiques. Rien ne « s'exécute » tant qu'on ne lance pas un processus.
- `Main.class` n'est **pas** un exécutable natif pour le noyau. C'est de la donnée que la JVM lit et interprète. Seul `java` (le *launcher*) est un binaire natif que le noyau sait lancer.
- Depuis Java 11, `java Main.java` compile et exécute en une étape (JEP 330) — `javac` est invoqué en mémoire, le `.class` n'est pas écrit. Le modèle source → bytecode → exécution reste le même.

## Le lancement : du shell au processus

`java Main` au terminal déclenche, côté noyau (Unix/macOS) :

1. Le shell appelle `fork()` → le noyau crée un processus enfant, copie du shell.
2. L'enfant appelle `execve()` sur le binaire `java` → le noyau **remplace** l'image mémoire de l'enfant par celle de `java`.
3. Le noyau charge le code natif de `java` + ses bibliothèques (`libjvm.dylib` sur macOS, `libjvm.so` sur Linux), met en place l'espace d'adressage, puis lance l'exécution.
4. `java` (le launcher) crée la JVM, qui charge `Main.class`, le vérifie, puis **interprète** son bytecode (et le compile à chaud via le **JIT**).

`fork` + `exec` est le mécanisme universel de création de processus sous Unix : un processus ne naît jamais de rien, il est toujours forké d'un parent existant.

## Le processus

Un **processus** est une instance isolée d'un programme en cours d'exécution. À sa création, le noyau lui attribue :

| Ressource | Rôle |
|-----------|------|
| **PID** | identifiant unique du processus (ex. 4201) |
| **Espace d'adressage virtuel** | mémoire privée : code, données, pile, tas |
| **Table de descripteurs de fichiers** | `stdin` (0), `stdout` (1), `stderr` (2), fichiers/sockets ouverts |
| **UID / GID** | identité (utilisateur, groupe) → droits d'accès |
| **Contexte d'exécution** | compteur de programme, registres, état des threads |

Le processus est l'**unité d'isolation** et d'allocation des ressources du système. Le noyau l'ordonnance (*scheduling*) sur les cœurs CPU, comptabilise sa mémoire, et le détruit à sa fin en récupérant tout.

## Mémoire virtuelle

Chaque processus voit un espace d'adressage **virtuel**, privé, comme s'il avait toute la machine pour lui seul.

- Les adresses manipulées par le processus sont virtuelles. Le noyau, via la MMU, les traduit en adresses **physiques** (RAM réelle) par **pages** (souvent 4 Ko ou 16 Ko).
- Deux processus peuvent utiliser la même adresse virtuelle sans conflit : elle pointe vers des pages physiques différentes.
- La mémoire physique peut être moins grande que la somme des espaces virtuels : les pages inactives sont écrites sur disque (*swap*).

Conséquence : un processus ne peut pas lire ni écrire la mémoire d'un autre. L'isolation est garantie par le matériel (MMU) et le noyau.

## La JVM est un processus ordinaire

La JVM n'a rien de spécial du point de vue du noyau. C'est un programme natif (écrit en C/C++) qui tourne **comme `ls` ou un navigateur**.

- Un programme Java en cours = **un processus dont le code natif est celui de la JVM**, et dont le travail consiste à exécuter ton bytecode.
- Le **heap Java**, le **metaspace**, les **piles de threads** (→ [`c09_modele_memoire`](../c09_modele_memoire/NOTES.md)) vivent **à l'intérieur** de l'espace d'adressage de ce processus.
- `-Xmx` (taille max du heap) borne une **sous-partie** de la mémoire du processus, pas tout le processus. La JVM consomme aussi de la mémoire hors heap (metaspace, piles, buffers natifs, code JIT).

## Deux exécutions simultanées : ce qui est isolé, ce qui est partagé

Lancer `java Main` deux fois crée **deux processus** (deux PID), avec **deux heaps Java indépendants**. L'état applicatif est totalement isolé. Mais le noyau **partage** tout ce qui est immuable, pour économiser la RAM.

| Élément | Partagé entre les deux processus ? |
|---------|-----------------------------------|
| Heap Java, objets, variables | Non — isolation totale |
| Piles, état d'exécution | Non |
| Fichier `Main.class` sur le disque | Oui — même fichier lu (lecture seule) |
| Code natif de la JVM en RAM (`libjvm`) | Oui — mêmes pages physiques, en lecture seule |

- Le **code natif partagé** (bibliothèques `.so`/`.dylib`) est chargé **une seule fois** en RAM physique, puis **mappé en lecture seule** dans l'espace virtuel de chaque processus. Chacun croit avoir sa copie ; physiquement, ce sont les mêmes pages.
- **Copy-on-write** : les pages partagées modifiables (après un `fork`) restent communes tant qu'on les lit. À la première **écriture**, le noyau en fait une copie privée pour l'auteur. Le partage ne brise jamais l'isolation, car ce qui est partagé sans copie est en lecture seule.

## Processus vs threads

- Un **processus** possède son propre espace d'adressage. Deux processus sont isolés.
- Un **thread** est un fil d'exécution **à l'intérieur** d'un processus. Tous les threads d'un processus **partagent le même heap** et le même metaspace ; chacun a sa **propre pile** (→ [`c09_modele_memoire`](../c09_modele_memoire/NOTES.md)).
- D'où le compromis : les threads communiquent vite (mémoire partagée) mais exposent aux *data races* ; les processus sont sûrs par isolation mais communiquent par des canaux explicites (*pipes*, sockets, fichiers).
- La JVM démarre toujours plusieurs threads : le thread `main` (qui exécute `main()`), plus des threads internes (GC, compilation JIT, finaliseurs).

## Pièges

- **« Le code source s'exécute »** : faux. Le source et le bytecode sont des fichiers inertes. Seul un **processus** s'exécute, en RAM. Le source ne « tourne » jamais.
- **`.class` confondu avec un exécutable natif** : le noyau ne sait pas lancer un `.class`. Il lance `java` ; c'est la JVM qui lit le `.class`. Double-cliquer un `.class` ne lance rien sans la JVM.
- **`-Xmx` = mémoire totale du processus** : non. C'est la borne du seul heap Java. La mémoire du processus (vue par `top`/`ps`, soit le *RSS*) inclut metaspace, piles, buffers natifs, code JIT — souvent bien plus que `-Xmx`.
- **Deux exécutions « partagent les variables statiques »** : non. Chaque processus a son metaspace et ses `static`. Le partage de `static` n'existe qu'**entre threads d'un même processus**, jamais entre processus.
- **Mettre `null` ou fermer le programme « libère » pour les autres** : la fin d'un processus libère **sa** mémoire. Elle n'affecte ni le fichier `.class` sur disque, ni les autres processus.

## Schéma mental

```
Disque (statique)                  RAM (dynamique, à `java Main`)
┌────────────────────┐            ┌──────────────────────────────────────┐
│ Main.java  (texte) │            │ Processus JVM  PID 4201                │
│ Main.class (bytecode)──lu par──▶│  ┌────────────────────────────────┐   │
└────────────────────┘            │  │ Heap Java   (tes objets)       │   │
                                  │  │ Metaspace   (classes, static)  │   │
   libjvm.dylib ───mappé en───────┼─▶│ Piles       (1 par thread)     │   │
   (code natif,  lecture seule    │  │ Thread main → exécute main()   │   │
    partagé)                      │  │ Threads JVM (GC, JIT…)          │   │
        │                         │  └────────────────────────────────┘   │
        │                         └──────────────────────────────────────┘
        │                         ┌──────────────────────────────────────┐
        └──mêmes pages physiques─▶│ Processus JVM  PID 4202 (heap isolé)  │
           (lecture seule)        └──────────────────────────────────────┘
```
