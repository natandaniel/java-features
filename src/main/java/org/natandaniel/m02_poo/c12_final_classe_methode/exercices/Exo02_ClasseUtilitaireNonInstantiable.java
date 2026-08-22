package org.natandaniel.m02_poo.c12_final_classe_methode.exercices;

/**
 * Exercice — classe utilitaire {@code final}, non instantiable (constructeur {@code private},
 * JLS §8.8.10) et non extensible (JLS §8.1.1.2) : le patron illustré par {@code java.lang.Math}
 * lui-même.
 */
class Exo02_ClasseUtilitaireNonInstantiable {

    static final class ConvertisseurTemperature {
        private ConvertisseurTemperature() {
        }

        static double celsiusVersFahrenheit(double celsius) {
            return celsius * 9.0 / 5.0 + 32.0;
        }
    }

    /**
     * Convertit chaque température Celsius du tableau donné en Fahrenheit, dans le même ordre.
     */
    static double[] convertirPlusieurs(double... celsius) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
