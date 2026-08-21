package org.natandaniel.m02_poo.c10_methodes_fabriques_statiques.lecon;

/**
 * Leçon 1/3 — nommer l'intention : une fabrique statique porte un nom, contrairement à un
 * constructeur (toujours nommé comme la classe). Ici, deux façons de construire une Temperature
 * ("depuis Celsius", "depuis Fahrenheit") seraient impossibles à distinguer avec deux
 * constructeurs Temperature(double) — signatures identiques, donc surcharge illégale (JLS §8.4.2).
 */
class Ex01_NommerLIntention {

    static class Temperature {
        private final double kelvin; // représentation canonique interne, unique

        // Constructeur private (JLS §8.8.10) : seul le passage par une fabrique nommée est possible.
        private Temperature(double kelvin) {
            this.kelvin = kelvin;
        }

        static Temperature depuisCelsius(double celsius) {
            return new Temperature(celsius + 273.15);
        }

        static Temperature depuisFahrenheit(double fahrenheit) {
            return new Temperature((fahrenheit - 32) * 5.0 / 9.0 + 273.15);
        }

        double enCelsius() {
            return kelvin - 273.15;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Deux fabriques nommées, un seul constructeur (privé) derrière ===");
        Temperature ambiante = Temperature.depuisCelsius(20.0);
        Temperature corporelle = Temperature.depuisFahrenheit(98.6);

        System.out.println("ambiante.enCelsius()   = " + ambiante.enCelsius() + " °C");
        System.out.println("corporelle.enCelsius() = " + corporelle.enCelsius() + " °C");

        System.out.println("\n=== Ce que deux constructeurs ne pourraient pas faire ===");
        System.out.println("Temperature(double) depuis Celsius et Temperature(double) depuis Fahrenheit");
        System.out.println("auraient exactement la même signature : impossible à surcharger (JLS §8.4.2).");
        System.out.println("Deux méthodes nommées, en revanche, coexistent sans ambiguïté.");

        // new Temperature(293.15); // ERREUR de compilation : constructeur private, hors de la classe.
        System.out.println("\n'new Temperature(293.15)' ne compilerait pas hors de la classe (constructeur private).");
    }
}
