package org.natandaniel.m01_fondamentaux.c04_debordement.exercices;

/**
 * Exercices — Détecter l'overflow soi-même (sans Math.*Exact).
 *
 * Indice : un calcul en {@code long} ne déborde pas pour des opérandes {@code int} ;
 * compare ensuite le résultat aux bornes de int.
 */
class Exo01_ArithmetiqueSure {

    /** Renvoie true si a + b déborde le domaine d'un int. */
    static boolean additionDeborde(int a, int b) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Renvoie a + b si le résultat tient dans un int, sinon lève ArithmeticException.
     * (Réimplémente Math.addExact sans l'appeler.)
     */
    static int sommeSure(int a, int b) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
