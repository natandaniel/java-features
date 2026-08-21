package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.solutions;

/** Solution de référence — création d'un nouveau tableau, tableau original non modifié. */
class Exo02_InverserTableau {

    static int[] inverser(int[] valeurs) {
        int[] resultat = new int[valeurs.length];
        for (int i = 0; i < valeurs.length; i++) {
            resultat[i] = valeurs[valeurs.length - 1 - i];
        }
        return resultat;
    }
}
