package org.natandaniel.m02_poo.c12_final_classe_methode.lecon;

/**
 * Leçon 4/4 — le patron de {@code java.lang.Math} lui-même (JLS §8.1.1.2, exemple cité
 * textuellement par la spec) : une classe utilitaire {@code final}, avec un constructeur {@code
 * private} pour empêcher toute instanciation (JLS §8.8.10, c10_methodes_fabriques_statiques),
 * qui ne contient que des méthodes {@code static}.
 */
class Ex04_ClasseUtilitaire {

    static final class ConvertisseurTemperature {
        private ConvertisseurTemperature() {
            // jamais instanciée : uniquement des méthodes static ci-dessous
        }

        static double celsiusVersFahrenheit(double celsius) {
            return celsius * 9.0 / 5.0 + 32.0;
        }

        static double fahrenheitVersCelsius(double fahrenheit) {
            return (fahrenheit - 32.0) * 5.0 / 9.0;
        }
    }

    // Ne compile pas (constructeur private, JLS §8.8.10) :
    // ConvertisseurTemperature c = new ConvertisseurTemperature();

    // Ne compile pas non plus (classe final, JLS §8.1.1.2) :
    // class ConvertisseurAvance extends ConvertisseurTemperature { }

    public static void main(String[] args) {
        System.out.println("=== Classe utilitaire : ni instantiable, ni extensible ===");
        System.out.println("0°C   = " + ConvertisseurTemperature.celsiusVersFahrenheit(0) + "°F");
        System.out.println("100°C = " + ConvertisseurTemperature.celsiusVersFahrenheit(100) + "°F");
        System.out.println("98.6°F = " + ConvertisseurTemperature.fahrenheitVersCelsius(98.6) + "°C");
        System.out.println("Comme java.lang.Math : constructeur private + classe final.");
    }
}
