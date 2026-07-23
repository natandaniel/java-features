package org.natandaniel.m01_fondamentaux.c09_modele_memoire.solutions;

/**
 * Solutions — Copie superficielle et copie profonde.
 */
class Exo01_Copies {

    /** Un nouveau tableau externe ; les lignes restent partagées avec la source. */
    static int[][] copieSuperficielle(int[][] source) {
        int[][] copie = new int[source.length][];
        for (int i = 0; i < source.length; i++) {
            copie[i] = source[i];   // on copie l'ADRESSE de la ligne, pas ses valeurs
        }
        return copie;
        // équivalent en une ligne : return source.clone();
    }

    /** Un nouveau tableau externe ET une nouvelle ligne par ligne source. */
    static int[][] copieProfonde(int[][] source) {
        int[][] copie = new int[source.length][];
        for (int i = 0; i < source.length; i++) {
            copie[i] = source[i].clone();   // nouvel objet ligne sur le tas
        }
        return copie;
    }

    /** Comparaison d'identité : les deux cases contiennent-elles la même adresse ? */
    static boolean partagentLaLigne(int[][] a, int[][] b, int i) {
        return a[i] == b[i];
    }
}
