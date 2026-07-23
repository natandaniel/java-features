package org.natandaniel.m01_fondamentaux.c09_modele_memoire.exercices;

/**
 * Exercices — La pile est finie : un frame par appel.
 *
 * <p>Les deux premières méthodes calculent la même somme. Elles diffèrent uniquement
 * par le nombre de frames qu'elles empilent — donc par la mémoire de PILE consommée.
 */
class Exo02_Recursion {

    /**
     * Somme de 1 à {@code n} par une boucle. Renvoie 0 si {@code n <= 0}.
     *
     * <p>Un seul frame, quelle que soit la valeur de {@code n} : la consommation de
     * pile est constante.
     */
    static long sommeIterative(int n) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Même résultat que {@link #sommeIterative(int)}, mais par récursion : la méthode
     * s'appelle elle-même jusqu'au cas de base. Renvoie 0 si {@code n <= 0}.
     *
     * <p>Un frame par appel : la consommation de pile est proportionnelle à {@code n}.
     * Pour un {@code n} assez grand, la JVM lève {@link StackOverflowError} — ce n'est
     * pas un bug de ton code, c'est la limite physique de la pile (option {@code -Xss}).
     */
    static long sommeRecursive(int n) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Descend en récursion jusqu'à saturer la pile, attrape la {@link StackOverflowError}
     * et renvoie la profondeur atteinte (nombre d'appels imbriqués).
     *
     * <p>Le résultat varie d'une exécution à l'autre : il dépend de {@code -Xss}, de la
     * taille des frames et du JIT. On ne peut donc en attendre qu'un ordre de grandeur.
     *
     * <p>Note : attraper une {@code Error} est réservé à ce genre de mesure. En code de
     * production, on ne rattrape jamais un {@code StackOverflowError}.
     */
    static int profondeurAtteinte() {
        throw new UnsupportedOperationException("À implémenter");
    }
}
