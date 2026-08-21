package org.natandaniel.m01_fondamentaux.c11_structures_controle.solutions;

/** Solution de référence — if / else if / else. */
class Exo01_Classification {

    static String classerTemperature(double celsius) {
        if (celsius < 0) {
            return "gel";
        } else if (celsius < 15) {
            return "frais";
        } else if (celsius < 25) {
            return "tempere";
        } else if (celsius < 35) {
            return "chaud";
        } else {
            return "canicule";
        }
    }

    static int comparer(int a, int b) {
        if (a < b) {
            return -1;
        } else if (a > b) {
            return 1;
        } else {
            return 0;
        }
    }
}
