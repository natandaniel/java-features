package org.natandaniel.m01_fondamentaux.c07_conversions.solutions;

/** Solution de référence — Conversions. */
class Exo01_Conversions {

    static byte tronquerEnByte(int valeur) {
        return (byte) valeur;
    }

    static int partieEntiere(double d) {
        return (int) d;   // troncature vers zéro
    }

    static boolean tientDansByte(int valeur) {
        return valeur >= Byte.MIN_VALUE && valeur <= Byte.MAX_VALUE;
    }
}
