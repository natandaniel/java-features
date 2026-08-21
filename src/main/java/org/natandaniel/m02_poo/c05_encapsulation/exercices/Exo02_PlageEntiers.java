package org.natandaniel.m02_poo.c05_encapsulation.exercices;

/**
 * Exercices — PlageEntiers : invariant min <= max garanti à la construction.
 */
class Exo02_PlageEntiers {

    static class PlageEntiers {
        private final int min;
        private final int max;

        /** Lève IllegalArgumentException si min > max. */
        PlageEntiers(int min, int max) {
            throw new UnsupportedOperationException("À implémenter");
        }

        boolean contient(int valeur) {
            throw new UnsupportedOperationException("À implémenter");
        }

        int getMin() {
            return min;
        }

        int getMax() {
            return max;
        }
    }
}
