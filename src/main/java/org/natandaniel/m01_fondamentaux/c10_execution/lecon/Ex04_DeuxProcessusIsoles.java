package org.natandaniel.m01_fondamentaux.c10_execution.lecon;

import java.io.IOException;

/**
 * Leçon 4/6 — Deux exécutions = deux processus = deux mémoires.
 *
 * <p>Cette classe se relance ELLE-MÊME dans une seconde JVM (comme ferait un shell :
 * fork puis exec du binaire `java`). Chaque processus incrémente le même champ `static`…
 * et pourtant chacun voit 1. Un `static` est partagé entre les THREADS d'un processus,
 * jamais entre PROCESSUS.
 */
class Ex04_DeuxProcessusIsoles {

    /** Une seule case pour toute la classe — mais une case PAR PROCESSUS. */
    static int compteurStatique = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        boolean estLEnfant = args.length > 0 && args[0].equals("enfant");
        String role = estLEnfant ? "ENFANT" : "PARENT";
        long pid = ProcessHandle.current().pid();   // @since Java 9

        compteurStatique++;
        System.out.println("[" + role + " pid=" + pid + "] compteurStatique = " + compteurStatique);

        if (estLEnfant) {
            System.out.println("[" + role + " pid=" + pid + "] mon heap n'a rien à voir avec celui du parent.");
            return;   // l'enfant s'arrête ici
        }

        System.out.println("[PARENT] je lance une SECONDE JVM sur la même classe…\n");
        int code = lancerSecondeJvm();
        System.out.println();
        System.out.println("[PARENT] l'enfant s'est terminé avec le code " + code);

        compteurStatique++;
        System.out.println("[PARENT] mon compteurStatique vaut maintenant " + compteurStatique
                + " : les ++ de l'enfant ne m'ont jamais touché.");

        System.out.println("\n=== Ce qui est partagé, ce qui ne l'est pas ===");
        System.out.println("PARTAGÉ    : le fichier .class sur le disque (lu par les deux, en lecture seule)");
        System.out.println("PARTAGÉ    : le code natif de la JVM (libjvm), mappé en lecture seule");
        System.out.println("             → mêmes PAGES PHYSIQUES en RAM, chargées une seule fois");
        System.out.println("ISOLÉ      : le heap, les piles, le metaspace, donc tous les `static`");
        System.out.println("             → l'isolation est garantie par la MMU et le noyau");
    }

    /** Reproduit ce que fait un shell : exécuter le binaire `java` sur notre propre classe. */
    static int lancerSecondeJvm() throws IOException, InterruptedException {
        // Le binaire natif réellement exécuté par le noyau — pas notre .class.
        String java = ProcessHandle.current().info().command()
                .orElse(System.getProperty("java.home") + "/bin/java");

        ProcessBuilder constructeur = new ProcessBuilder(
                java,
                "-cp", System.getProperty("java.class.path"),
                Ex04_DeuxProcessusIsoles.class.getName(),
                "enfant");
        constructeur.inheritIO();   // l'enfant écrit dans NOTRE stdout : les descripteurs, eux, s'héritent

        Process enfant = constructeur.start();
        return enfant.waitFor();    // attend la fin de l'enfant, récupère son code de retour
    }
}
