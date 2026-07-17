package org.natandaniel.m01_fondamentaux.c06_flottants.solutions;

/** Solution de référence — Flottants. */
class Exo01_Flottants {

    static boolean presqueEgaux(double a, double b, double epsilon) {
        return Math.abs(a - b) <= epsilon;
    }

    static boolean estNaN(double x) {
        return x != x;   // vrai uniquement pour NaN
    }

    static String classifier(double x) {
        if (estNaN(x)) {
            return "NaN";
        }
        if (Double.isInfinite(x)) {
            return "INFINI";
        }
        return "FINI";
    }
}
