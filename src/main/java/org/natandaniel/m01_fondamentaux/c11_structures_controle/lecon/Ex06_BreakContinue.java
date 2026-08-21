package org.natandaniel.m01_fondamentaux.c11_structures_controle.lecon;

/**
 * Leçon 6/7 — break et continue (JLS §14.15, §14.16).
 *
 * `break` quitte immédiatement la boucle (ou le switch) qui le contient.
 * `continue` saute directement à l'itération suivante, sans quitter la boucle.
 * Un label (`nomDeLabel:`) permet de viser une boucle englobante précise
 * plutôt que la plus proche.
 */
class Ex06_BreakContinue {

    public static void main(String[] args) {
        System.out.println("=== break : sort de la boucle dès la condition atteinte ===");
        for (int i = 0; i < 10; i++) {
            if (i == 3) {
                break;
            }
            System.out.println("i = " + i);
        }
        System.out.println("boucle interrompue à i == 3, jamais allée jusqu'à 9");

        System.out.println("\n=== continue : saute cette itération, la boucle continue ===");
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                continue;   // ne rien afficher pour les valeurs paires
            }
            System.out.println("impair : " + i);
        }

        System.out.println("\n=== piège : continue dans un while/do-while ne saute PAS l'incrément ===");
        // Dans un for, continue saute directement à la clause d'incrément.
        // Dans un while, continue saute au test de condition — si l'incrément
        // est placé APRÈS le continue dans le corps, il est sauté aussi, et la
        // boucle peut tourner indéfiniment si la seule mise à jour était là.
        int i = 0;
        while (i < 6) {
            i++;   // incrément placé AVANT le continue : jamais sauté
            if (i % 2 == 0) {
                continue;
            }
            System.out.println("impair (while) : " + i);
        }

        System.out.println("\n=== labels : viser une boucle englobante précise ===");
        // Sans label, `break`/`continue` ne visent que la boucle la plus proche
        // (ici la boucle intérieure `colonne`). Le label permet de sortir de
        // TOUTES les boucles englobantes d'un coup.
        recherche:
        for (int ligne = 0; ligne < 3; ligne++) {
            for (int colonne = 0; colonne < 3; colonne++) {
                if (ligne == 1 && colonne == 1) {
                    System.out.println("trouvé en (" + ligne + "," + colonne + "), on sort des DEUX boucles");
                    break recherche;
                }
                System.out.println("essai (" + ligne + "," + colonne + ")");
            }
        }
    }
}
