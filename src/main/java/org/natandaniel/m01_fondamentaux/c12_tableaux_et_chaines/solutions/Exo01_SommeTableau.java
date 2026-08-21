package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.solutions;

/** Solution de référence — accès indexé, parcours de tableau. */
class Exo01_SommeTableau {

    static int somme(int[] valeurs) {
        int total = 0;
        for (int valeur : valeurs) {
            total += valeur;
        }
        return total;
    }

    static int indexDuMaximum(int[] valeurs) {
        int indexMax = 0;
        for (int i = 1; i < valeurs.length; i++) {
            if (valeurs[i] > valeurs[indexMax]) {
                indexMax = i;
            }
        }
        return indexMax;
    }
}
