package org.natandaniel.m01_fondamentaux.c07_conversions.solutions;

/** Solution de référence — char comme entier. */
class Exo02_Caracteres {

    static int distanceAlphabetique(char a, char b) {
        return b - a;   // promotion en int, soustraction entière
    }

    static char enMajuscule(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char) (c - ('a' - 'A'));   // 'a' - 'A' = 32, l'écart minuscule→majuscule
        }
        return c;
    }
}
