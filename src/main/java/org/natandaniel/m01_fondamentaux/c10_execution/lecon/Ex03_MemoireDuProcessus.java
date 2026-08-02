package org.natandaniel.m01_fondamentaux.c10_execution.lecon;

import java.lang.management.ManagementFactory;

/**
 * Leçon 3/6 — `-Xmx` borne le heap, pas le processus.
 *
 * <p>La mémoire que `top` ou `ps` attribuent au processus (le RSS) est TOUJOURS
 * plus grande que le heap Java : s'y ajoutent le metaspace, une pile par thread,
 * le code compilé par le JIT et les buffers natifs.
 *
 * <p>À essayer : relancer avec `java -Xmx64m ... Ex03_MemoireDuProcessus`,
 * puis comparer avec la colonne RSS de `ps -o rss= -p <PID>`.
 */
class Ex03_MemoireDuProcessus {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        System.out.println("=== Ce que Runtime mesure : LE HEAP, rien d'autre ===");
        afficher("maxMemory   (plafond du heap, ≈ -Xmx)", runtime.maxMemory());
        afficher("totalMemory (heap réservé pour l'instant)", runtime.totalMemory());
        afficher("freeMemory  (libre DANS le heap réservé)", runtime.freeMemory());
        afficher("utilisé     (total - free)", runtime.totalMemory() - runtime.freeMemory());

        System.out.println("\n=== Allouons 20 Mo d'objets ===");
        long avant = runtime.totalMemory() - runtime.freeMemory();
        byte[] bloc = new byte[20 * 1024 * 1024];   // 20 Mo sur le TAS
        bloc[0] = 1;                                // empêche l'élimination par le JIT
        long apres = runtime.totalMemory() - runtime.freeMemory();
        afficher("heap utilisé avant", avant);
        afficher("heap utilisé après", apres);
        afficher("delta", apres - avant);
        System.out.println("   → ces 20 Mo sont dans le heap : ils comptent dans -Xmx.");

        System.out.println("\n=== Ce que Runtime ne voit PAS (et qui est pourtant dans le RSS) ===");
        System.out.println("- metaspace       : " + classesChargees()
                + " classes chargées à cet instant, hors heap");
        System.out.println("- piles           : ~1 Mo par thread, hors heap (-Xss)");
        System.out.println("- code JIT        : le natif compilé à chaud, hors heap");
        System.out.println("- buffers natifs  : ByteBuffer directs, sockets, fichiers mappés");
        System.out.println("- la JVM elle-même: le code de libjvm chargé en mémoire");
        System.out.println("PID de ce processus : " + ProcessHandle.current().pid()   // @since Java 9
                + "   → compare avec `ps -o rss= -p " + ProcessHandle.current().pid() + "`");

        System.out.println("\nPiège : `-Xmx512m` NE dit PAS « ce processus prendra au plus 512 Mo ».");
        System.out.println("Il dit « le heap Java ne dépassera pas 512 Mo ». Le processus, lui, prendra plus.");
    }

    /** Le nombre réel de classes actuellement chargées : elles vivent dans le metaspace. */
    static int classesChargees() {
        // Chiffre mesuré, pas estimé : `-verbose:class` liste les mêmes chargements une à une.
        return ManagementFactory.getClassLoadingMXBean().getLoadedClassCount();
    }

    static void afficher(String libelle, long octets) {
        System.out.printf("%-42s : %,15d octets  (%6.1f Mo)%n", libelle, octets, octets / 1048576.0);
    }
}
