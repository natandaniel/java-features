package org.natandaniel.m01_fondamentaux.c10_execution.solutions;

/**
 * Solutions — Pourquoi le processus pèse toujours plus que `-Xmx`.
 */
class Exo02_EmpreinteMemoire {

    private static final long KILO = 1024L;
    private static final long MEGA = 1024L * 1024L;
    private static final long GIGA = 1024L * 1024L * 1024L;

    /** Parse une taille façon `-Xmx512m` : nombre + suffixe optionnel k/m/g. */
    static long enOctets(String taille) {
        if (taille == null) {
            throw new IllegalArgumentException("taille absente");
        }
        String coupee = taille.trim();
        if (coupee.isEmpty()) {
            throw new IllegalArgumentException("taille vide");
        }

        long facteur = 1L;
        String nombre = coupee;
        char dernier = coupee.charAt(coupee.length() - 1);
        if (!Character.isDigit(dernier)) {
            facteur = switch (Character.toLowerCase(dernier)) {   // switch fléché : @since Java 14
                case 'k' -> KILO;
                case 'm' -> MEGA;
                case 'g' -> GIGA;
                default -> throw new IllegalArgumentException("suffixe inconnu : " + dernier);
            };
            nombre = coupee.substring(0, coupee.length() - 1);
        }

        long valeur;
        try {
            valeur = Long.parseLong(nombre);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("taille mal formée : " + taille);
        }
        if (valeur < 0) {
            throw new IllegalArgumentException("taille négative : " + taille);
        }
        return valeur * facteur;
    }

    /** heap + metaspace + une pile par thread. */
    static long empreinteEstimee(long heapMax, long metaspace, int nbThreads, long pileParThread) {
        if (heapMax < 0 || metaspace < 0 || nbThreads < 0 || pileParThread < 0) {
            throw new IllegalArgumentException("aucune de ces tailles ne peut être négative");
        }
        return heapMax + metaspace + (long) nbThreads * pileParThread;
    }

    /** Même calcul, à partir des tailles telles qu'écrites sur la ligne de commande. */
    static long empreinteEstimee(String heapMax, String metaspace, int nbThreads, String pileParThread) {
        return empreinteEstimee(enOctets(heapMax), enOctets(metaspace), nbThreads, enOctets(pileParThread));
    }

    /** La part hors heap : invisible pour Runtime.maxMemory(), non bornée par -Xmx. */
    static long horsHeap(long heapMax, long metaspace, int nbThreads, long pileParThread) {
        return empreinteEstimee(heapMax, metaspace, nbThreads, pileParThread) - heapMax;
    }

    /** Vrai dès qu'il existe du metaspace ou une pile : c'est-à-dire toujours, en pratique. */
    static boolean depasseHeapMax(long heapMax, long metaspace, int nbThreads, long pileParThread) {
        return horsHeap(heapMax, metaspace, nbThreads, pileParThread) > 0;
    }
}
