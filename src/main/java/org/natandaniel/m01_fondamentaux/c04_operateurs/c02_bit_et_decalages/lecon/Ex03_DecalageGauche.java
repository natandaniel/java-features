package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.lecon;

/**
 * Leçon 1/4 — Décalage à gauche ({@code <<}) : multiplier par une puissance de 2.
 */
class Ex03_DecalageGauche {

    static String bits8(int n) {
        return String.format("%8s", Integer.toBinaryString(n & 0xFF)).replace(' ', '0');
    }

    public static void main(String[] args) {
        System.out.println("=== <<  : les bits glissent à gauche, des 0 entrent à droite ===");
        System.out.println("Chaque position décalée multiplie par 2.");
        System.out.println("  1 << 0 = " + bits8(1) + "  = " + (1 << 0));
        System.out.println("  1 << 1 = " + bits8(1 << 1) + "  = " + (1 << 1));
        System.out.println("  1 << 2 = " + bits8(1 << 2) + "  = " + (1 << 2));
        System.out.println("  1 << 3 = " + bits8(1 << 3) + "  = " + (1 << 3));
        System.out.println("  → 1 << k vaut exactement 2^k");

        System.out.println("\n=== Multiplication rapide ===");
        System.out.println("  42 << 2 = " + (42 << 2) + "  (= 42 × 4)");

        System.out.println("\n=== Attention à l'overflow : un bit non nul qui sort à gauche est perdu ===");
        System.out.println("  64 (byte) << 1 = " + bits8(64 << 1) + "  = " + ((byte) (64 << 1)) + "  (déborde dans un byte !)");
    }
}
