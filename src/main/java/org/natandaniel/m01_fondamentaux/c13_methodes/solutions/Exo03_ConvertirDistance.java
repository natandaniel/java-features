package org.natandaniel.m01_fondamentaux.c13_methodes.solutions;

/** Solution de référence — surcharge de méthodes. */
class Exo03_ConvertirDistance {

    static int convertirEnMetres(int kilometres) {
        return kilometres * 1000;
    }

    static double convertirEnMetres(double kilometres) {
        return kilometres * 1000.0;
    }
}
