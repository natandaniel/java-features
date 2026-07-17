package org.natandaniel.m01_fondamentaux.c03_operateurs.c01_arithmetiques.lecon;

/**
 * Leçon 1/3 — Les opérations arithmétiques de base sur les entiers.
 */
class Ex01_Operations {

    public static void main(String[] args) {
        System.out.println("=== Arithmétique entière de base ===");
        int a = 17, b = 5;
        System.out.println("17 + 5 = " + (a + b));
        System.out.println("17 - 5 = " + (a - b));
        System.out.println("17 * 5 = " + (a * b));
        System.out.println("17 / 5 = " + (a / b) + "   (division ENTIÈRE : pas 3.4)");
        System.out.println("17 % 5 = " + (a % b) + "   (reste de la division)");

        System.out.println("\n=== Division entière par zéro ===");
        try {
            int r = a / 0;
            System.out.println("résultat = " + r);
        } catch (ArithmeticException e) {
            System.out.println("17 / 0 → ArithmeticException : \"" + e.getMessage() + "\"");
        }
        System.out.println("(La division d'un FLOTTANT par zéro ne lève pas d'exception → module flottants.)");
    }
}
