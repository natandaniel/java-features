package org.natandaniel.m01_fondamentaux.c07_conversions.lecon;

/**
 * Leçon 3/3 — Promotion dans les expressions mixtes.
 */
class Ex03_PromotionExpressions {

    public static void main(String[] args) {
        System.out.println("=== Promotion des petits types entiers : byte/short/char → int ===");
        byte x = 10, y = 20;
        // byte z = x + y;  // ERREUR : x + y est déjà un int
        int z = x + y;                  // x et y promus en int AVANT l'addition
        System.out.println("byte(10) + byte(20) → type int : " + z);

        System.out.println("\n=== Puis vers le « plus grand » type quand l'expression est mixte ===");

        int unInt = 5;
        double unDouble = 2.0;
        double r1 = unInt + unDouble;   // 5 promu en 5.0 avant l'addition
        System.out.println("5 (int) + 2.0 (double) = " + r1 + "   [type double]");

        long unLong = 10L;
        long r2 = unInt + unLong;       // 5 promu en long
        System.out.println("5 (int) + 10L (long)   = " + r2 + "   [type long]");

        System.out.println("\n=== Conséquence sur la division ===");
        System.out.println("7 / 2     = " + (7 / 2) + "   (deux int → division entière)");
        System.out.println("7 / 2.0   = " + (7 / 2.0) + " (un double → division flottante)");
    }
}
