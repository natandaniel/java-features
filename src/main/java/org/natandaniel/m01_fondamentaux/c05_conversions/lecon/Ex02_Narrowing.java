package org.natandaniel.m01_fondamentaux.c05_conversions.lecon;

/**
 * Leçon 2/3 — Narrowing : conversions rétrécissantes, cast explicite obligatoire (JLS §5.1.3).
 */
class Ex02_Narrowing {

    public static void main(String[] args) {
        System.out.println("=== Le cast explicite est obligatoire dans le sens rétrécissant ===");
        int valeur = 300;
        // byte b = valeur;       // ERREUR : possible lossy conversion from int to byte
        byte b = (byte) valeur;   // on garde les 8 bits de poids faible : 300 = 100101100 → 00101100 = 44
        System.out.println("(byte) 300 = " + b + "  (les bits de poids fort sont perdus)");

        System.out.println("\n=== double → int : TRONCATURE vers zéro (pas d'arrondi) ===");
        System.out.println("(int) 3.99999 = " + (int) 3.99999 + "  (3, pas 4)");
        System.out.println("(int) -3.7    = " + (int) -3.7 + "  (-3, pas -4)");
    }
}
