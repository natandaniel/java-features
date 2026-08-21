package org.natandaniel.m02_poo.c05_encapsulation.solutions;

/** Solution de référence — PlageEntiers : invariant min <= max garanti à la construction. */
class Exo02_PlageEntiers {

    static class PlageEntiers {
        private final int min;
        private final int max;

        PlageEntiers(int min, int max) {
            if (min > max) {
                throw new IllegalArgumentException("min > max");
            }
            this.min = min;
            this.max = max;
        }

        boolean contient(int valeur) {
            return valeur >= min && valeur <= max;
        }

        int getMin() {
            return min;
        }

        int getMax() {
            return max;
        }
    }
}
