package org.natandaniel.m01_fondamentaux.c09_modele_memoire.exercices;

/**
 * Exercices — Copie superficielle et copie profonde.
 *
 * <p>Rappel : copier une variable de type référence copie une ADRESSE, jamais l'objet.
 * Un tableau 2D est un tableau de références vers des tableaux 1D — c'est là que
 * la distinction devient visible.
 */
class Exo01_Copies {

    /**
     * Renvoie une copie SUPERFICIELLE de {@code source} : un nouveau tableau externe,
     * de même longueur, dont chaque case contient la MÊME référence de ligne que la source.
     *
     * <p>Conséquence attendue : modifier {@code resultat[i][j]} modifie aussi
     * {@code source[i][j]}, car les deux désignent le même tableau de ligne sur le tas.
     */
    static int[][] copieSuperficielle(int[][] source) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Renvoie une copie PROFONDE de {@code source} : un nouveau tableau externe ET
     * une nouvelle ligne pour chacune des lignes de la source.
     *
     * <p>Conséquence attendue : modifier {@code resultat[i][j]} laisse la source intacte.
     */
    static int[][] copieProfonde(int[][] source) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Indique si {@code a[i]} et {@code b[i]} désignent le MÊME objet ligne sur le tas.
     *
     * <p>Il s'agit d'une question d'IDENTITÉ, pas de contenu : deux lignes de contenu
     * identique mais allouées séparément doivent donner {@code false}.
     */
    static boolean partagentLaLigne(int[][] a, int[][] b, int i) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
