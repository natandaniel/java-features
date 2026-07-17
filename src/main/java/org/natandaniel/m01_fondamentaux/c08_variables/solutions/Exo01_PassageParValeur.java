package org.natandaniel.m01_fondamentaux.c08_variables.solutions;

/** Solution de référence — Passage par valeur. */
class Exo01_PassageParValeur {

    static int doubler(int n) {
        return n * 2;
    }

    static void remplirDeZeros(int[] tableau) {
        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = 0;
        }
    }
}
