package org.natandaniel.m01_fondamentaux.c10_execution.lecon;

/**
 * Leçon 5/6 — Dans UN processus : les threads partagent le tas, pas les piles.
 *
 * <p>Miroir exact de la leçon 4. Là-bas, deux processus ne voyaient pas le même `static`.
 * Ici, deux threads du MÊME processus voient le même objet et le même `static` — parce
 * qu'ils partagent l'espace d'adressage. Chacun n'a en propre que sa PILE.
 */
class Ex05_ThreadsPartagentLeHeap {

    static int compteurPartage = 0;                 // metaspace : une case pour le processus
    static final int[] TABLEAU_PARTAGE = new int[2];  // tas : un seul objet pour les deux threads

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Un seul processus, pid = " + ProcessHandle.current().pid());   // @since Java 9

        System.out.println("\n=== Le tas est commun ===");
        Thread premier = new Thread(() -> {
            int localAuThread = 100;      // SA pile à lui : invisible de l'autre thread
            TABLEAU_PARTAGE[0] = localAuThread;
            System.out.println("  thread 1 : ma variable locale = " + localAuThread
                    + ", j'écris dans le tableau partagé");
        });
        Thread second = new Thread(() -> {
            int localAuThread = 200;      // même NOM, autre pile, autre case
            TABLEAU_PARTAGE[1] = localAuThread;
            System.out.println("  thread 2 : ma variable locale = " + localAuThread
                    + ", j'écris dans le MÊME tableau");
        });
        premier.start();
        second.start();
        premier.join();
        second.join();
        System.out.println("Depuis main : TABLEAU_PARTAGE = ["
                + TABLEAU_PARTAGE[0] + ", " + TABLEAU_PARTAGE[1] + "]");
        System.out.println("   → les deux écritures sont visibles : un seul objet, un seul tas.");

        System.out.println("\n=== La contrepartie du partage : la course de données ===");
        Thread a = new Thread(Ex05_ThreadsPartagentLeHeap::incrementerUnMillionDeFois);
        Thread b = new Thread(Ex05_ThreadsPartagentLeHeap::incrementerUnMillionDeFois);
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println("compteurPartage attendu : 2000000");
        System.out.println("compteurPartage obtenu  : " + compteurPartage);
        System.out.println("   → souvent MOINS : `compteur++` n'est pas atomique (lire, ajouter, écrire).");
        System.out.println("     Deux processus n'auraient pas ce problème — ils n'ont rien à partager.");
        System.out.println("     C'est le compromis : threads = communication rapide + risque de course ;");
        System.out.println("     processus = sûrs par isolation, mais communication explicite (pipes, sockets).");

        System.out.println("\n=== Le processus a toujours plus de threads que les tiens ===");
        System.out.println("Thread courant : " + Thread.currentThread().getName());
        System.out.println("   → plus les threads internes de la JVM : GC, compilation JIT, signaux…");
    }

    static void incrementerUnMillionDeFois() {
        for (int i = 0; i < 1_000_000; i++) {   // underscores dans les littéraux : @since Java 7
            compteurPartage++;                  // NON atomique : c'est là que des incréments se perdent
        }
    }
}
