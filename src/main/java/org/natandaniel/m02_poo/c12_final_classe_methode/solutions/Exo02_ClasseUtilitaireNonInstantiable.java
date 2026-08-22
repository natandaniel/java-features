package org.natandaniel.m02_poo.c12_final_classe_methode.solutions;

/** Solution de référence — délégation simple à la classe utilitaire, élément par élément. */
class Exo02_ClasseUtilitaireNonInstantiable {

    static final class ConvertisseurTemperature {
        private ConvertisseurTemperature() {
        }

        static double celsiusVersFahrenheit(double celsius) {
            return celsius * 9.0 / 5.0 + 32.0;
        }
    }

    static double[] convertirPlusieurs(double... celsius) {
        double[] resultat = new double[celsius.length];
        for (int i = 0; i < celsius.length; i++) {
            resultat[i] = ConvertisseurTemperature.celsiusVersFahrenheit(celsius[i]);
        }
        return resultat;
    }
}
