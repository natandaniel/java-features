package org.natandaniel.m01_fondamentaux.c05_debordement.solutions;

/** Solution de référence — Détection d'overflow. */
class Exo01_ArithmetiqueSure {

    static boolean additionDeborde(int a, int b) {
        long somme = (long) a + b;   // calcul en long : jamais d'overflow ici
        return somme < Integer.MIN_VALUE || somme > Integer.MAX_VALUE;
    }

    static int sommeSure(int a, int b) {
        if (additionDeborde(a, b)) {
            throw new ArithmeticException("integer overflow");
        }
        return a + b;
    }
}
