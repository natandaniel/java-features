package org.natandaniel.m01_fondamentaux.c05_conversions.lecon;

/**
 * Leçon 3/3 — Promotion dans les expressions mixtes.
 */
class Ex03_PromotionExpressions {

    public static void main(String[] args) {
        System.out.println("=== Java promeut le « plus petit » type vers le « plus grand » avant de calculer ===");

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
