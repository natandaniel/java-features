package org.natandaniel.m01_fondamentaux.c11_structures_controle.solutions;

/** Solution de référence — switch statement, fall-through volontaire. */
class Exo02_JoursDuMois {

    static int joursDansLeMois(int mois, boolean anneeBissextile) {
        switch (mois) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return anneeBissextile ? 29 : 28;
            default:
                return 31;
        }
    }
}
