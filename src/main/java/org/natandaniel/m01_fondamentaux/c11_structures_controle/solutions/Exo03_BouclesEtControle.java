package org.natandaniel.m01_fondamentaux.c11_structures_controle.solutions;

/** Solution de référence — do-while, for, break. */
class Exo03_BouclesEtControle {

    static int compterChiffres(int n) {
        int reste = n;
        int chiffres = 0;
        do {
            reste = reste / 10;
            chiffres++;
        } while (reste != 0);
        return chiffres;
    }

    static boolean estPremier(int n) {
        boolean premier = true;
        for (int diviseur = 2; diviseur * diviseur <= n; diviseur++) {
            if (n % diviseur == 0) {
                premier = false;
                break;
            }
        }
        return premier;
    }
}
