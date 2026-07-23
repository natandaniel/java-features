package org.natandaniel.m01_fondamentaux.c09_modele_memoire.lecon;

/**
 * Leçon 2/6 — Un frame par appel : la pile est finie.
 */
class Ex02_FramesEtPile {

    public static void main(String[] args) {
        System.out.println("=== Empilement et dépilement (LIFO) ===");
        descendre(3);

        System.out.println("\n=== La pile a une taille bornée (-Xss) ===");
        int atteint = profondeurMaximale(1);
        System.out.println("StackOverflowError après environ " + atteint + " appels imbriqués.");
        System.out.println("Le chiffre varie d'une exécution à l'autre : il dépend de -Xss,");
        System.out.println("de la taille de chaque frame et des optimisations du JIT.");

        System.out.println("\n=== Le programme continue : l'Error a été attrapée ===");
        System.out.println("La pile est vidée jusqu'au catch ; rien n'est corrompu.");
    }

    /** Chaque appel empile un frame ; chaque retour le dépile, dans l'ordre inverse. */
    static void descendre(int n) {
        System.out.println("  ↓ entrée dans descendre(" + n + ")");
        if (n > 0) {
            descendre(n - 1);
        }
        System.out.println("  ↑ sortie de descendre(" + n + ")");
    }

    /**
     * Descend jusqu'à saturer la pile. Attraper une {@link StackOverflowError} est
     * exceptionnel : on le fait ici pour MESURER, jamais en code de production.
     */
    static int profondeurMaximale(int profondeur) {
        try {
            return profondeurMaximale(profondeur + 1);
        } catch (StackOverflowError e) {
            // À ce point, la pile est saturée : ne rien faire de coûteux ici.
            return profondeur;
        }
    }
}
