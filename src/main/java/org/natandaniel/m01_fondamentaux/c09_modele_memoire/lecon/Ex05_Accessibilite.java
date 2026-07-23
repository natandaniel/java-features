package org.natandaniel.m01_fondamentaux.c09_modele_memoire.lecon;

import java.lang.ref.WeakReference;   // @since Java 1.2

/**
 * Leçon 5/6 — Un objet vit tant qu'il est ACCESSIBLE, pas tant qu'on le « veut ».
 */
class Ex05_Accessibilite {

    public static void main(String[] args) {
        System.out.println("=== `null` ne libère rien : il coupe un chemin ===");
        StringBuilder sb = new StringBuilder("bonjour");
        StringBuilder autreChemin = sb;      // deuxième chemin vers le MÊME objet

        sb = null;                           // on coupe un chemin sur deux
        System.out.println("sb = null, mais l'objet vit encore : " + autreChemin
                + "   (toujours accessible par autreChemin)");

        System.out.println("\n=== Observer une collecte avec WeakReference ===");
        Object cible = new Object();
        WeakReference<Object> faible = new WeakReference<>(cible);
        // Une référence faible NE MAINTIENT PAS l'objet en vie : elle permet de l'observer.

        System.out.println("tant que `cible` pointe l'objet → faible.get() != null : "
                + (faible.get() != null));

        cible = null;                        // plus aucun chemin FORT vers l'objet
        System.gc();                         // SUGGESTION au GC, jamais un ordre
        attendreUnPeu();

        Object apres = faible.get();
        System.out.println("après cible = null et System.gc() → faible.get() = " + apres);
        System.out.println(apres == null
                ? "  → l'objet a bien été collecté cette fois-ci."
                : "  → pas encore collecté : c'est parfaitement légal, le GC décide seul.");

        System.out.println("\n⚠ Ce résultat n'est PAS déterministe.");
        System.out.println("  C'est précisément pourquoi aucun test JUnit de ce concept ne s'appuie");
        System.out.println("  sur System.gc() : un test doit être reproductible.");

        System.out.println("\n=== Ce que le GC garantit, et ce qu'il ne garantit pas ===");
        System.out.println("GARANTI     : un objet inaccessible ne sera jamais ressuscité.");
        System.out.println("NON GARANTI : le MOMENT de la collecte, et même qu'elle ait lieu.");
        System.out.println("CONSÉQUENCE : pour libérer un fichier ou un socket, on utilise");
        System.out.println("              try-with-resources / AutoCloseable — jamais le GC.");
    }

    /** Laisse au GC une fenêtre pour s'exécuter. Aucune garantie, seulement une chance. */
    static void attendreUnPeu() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
