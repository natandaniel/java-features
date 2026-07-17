package org.natandaniel.m01_fondamentaux.c05_debordement.lecon;

/**
 * Leçon 2/3 — Les pièges d'overflow les plus fréquents.
 */
class Ex02_PiegesClassiques {

    public static void main(String[] args) {
        System.out.println("=== Piège 1 : ordre du cast vers long ===");
        int i = 1000000;
        System.out.println("(long)(i * i) = " + (long) (i * i) + "  (i*i déborde en int AVANT le cast : trop tard)");
        System.out.println("(long)i * i   = " + ((long) i * i) + "  (i casté AVANT : correct)");

        System.out.println("\n=== Piège 2 : byte b = 127; b++ ===");
        byte b = 127;
        b++;   // (byte)(128) → -128
        System.out.println("byte b = 127; b++ → " + b + "  (128 hors plage byte → -128)");

        System.out.println("\n=== Piège 3 : constante calculée en int par le compilateur ===");
        int deborde = 400 * 365 * 24 * 60 * 60;
        System.out.println("400 * 365 * 24 * 60 * 60  = " + deborde + "  (overflow : résultat faux)");
        long correct = 400L * 365 * 24 * 60 * 60;
        System.out.println("400L * 365 * 24 * 60 * 60 = " + correct + "  (le L force le calcul en long)");
    }
}
