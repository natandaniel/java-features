package org.natandaniel.m01_fondamentaux.c08_variables.exercices;

/**
 * Exercices — Comprendre le passage par valeur.
 *
 * Implémente ces méthodes de façon à refléter le comportement réel de Java.
 */
class Exo01_PassageParValeur {

    /**
     * Double n. Comme le paramètre est une COPIE, la seule façon de transmettre le
     * résultat à l'appelant est de le RENVOYER.
     */
    static int doubler(int n) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Met à 0 tous les éléments du tableau reçu. Comme on modifie l'objet pointé,
     * le changement est visible de l'appelant (pas de valeur de retour nécessaire).
     */
    static void remplirDeZeros(int[] tableau) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
