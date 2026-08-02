package org.natandaniel.m01_fondamentaux.c10_execution.exercices;

/**
 * Exercices — Pourquoi le processus pèse toujours plus que {@code -Xmx}.
 *
 * <p>{@code -Xmx512m} borne le <b>heap Java</b>. La mémoire du processus (le RSS que montre
 * {@code top}) y ajoute le metaspace, une pile par thread, le code JIT et les buffers natifs.
 * Ces exercices modélisent ce calcul.
 */
class Exo02_EmpreinteMemoire {

    /**
     * Convertit une taille façon ligne de commande JVM en octets.
     *
     * <p>Suffixes acceptés, insensibles à la casse : {@code k}/{@code K} = 1024,
     * {@code m}/{@code M} = 1024², {@code g}/{@code G} = 1024³. Sans suffixe, la valeur
     * est déjà en octets. Les espaces de début et de fin sont ignorés.
     *
     * <p>Exemples : {@code "512m"} → {@code 536870912} ; {@code "2G"} → {@code 2147483648} ;
     * {@code "1024"} → {@code 1024}.
     *
     * <p>Lève {@link IllegalArgumentException} si {@code taille} est {@code null}, vide,
     * mal formée (lettres parasites, suffixe inconnu, valeur absente) ou négative.
     */
    static long enOctets(String taille) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Estime l'empreinte totale du processus : {@code heapMax + metaspace + nbThreads × pileParThread}.
     *
     * <p>Lève {@link IllegalArgumentException} si l'un des trois premiers arguments est
     * négatif, ou si {@code pileParThread} est négative.
     */
    static long empreinteEstimee(long heapMax, long metaspace, int nbThreads, long pileParThread) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Même calcul, mais à partir des tailles telles qu'on les écrit sur la ligne de commande.
     *
     * <p>Exemple : {@code empreinteEstimee("512m", "64m", 10, "1m")} → l'empreinte en octets
     * de {@code java -Xmx512m -Xss1m …} avec 10 threads et 64 Mo de metaspace.
     */
    static long empreinteEstimee(String heapMax, String metaspace, int nbThreads, String pileParThread) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Renvoie la part de l'empreinte qui vit <b>hors du heap</b> — donc invisible pour
     * {@code Runtime.maxMemory()} et non bornée par {@code -Xmx}.
     */
    static long horsHeap(long heapMax, long metaspace, int nbThreads, long pileParThread) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Indique si le processus consommera plus que ce que {@code -Xmx} laisse croire,
     * c'est-à-dire si l'empreinte estimée dépasse strictement {@code heapMax}.
     *
     * <p>Dès qu'il existe du metaspace ou au moins un thread avec une pile non nulle,
     * la réponse est {@code true} : c'est tout l'intérêt de l'exercice.
     */
    static boolean depasseHeapMax(long heapMax, long metaspace, int nbThreads, long pileParThread) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
