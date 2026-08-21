package org.natandaniel.m01_fondamentaux.c13_methodes.solutions;

/** Solution de référence — varargs. */
class Exo01_Moyenne {

    static double moyenne(double... valeurs) {
        if (valeurs.length == 0) {
            return 0.0;
        }
        double total = 0.0;
        for (double valeur : valeurs) {
            total += valeur;
        }
        return total / valeurs.length;
    }
}
