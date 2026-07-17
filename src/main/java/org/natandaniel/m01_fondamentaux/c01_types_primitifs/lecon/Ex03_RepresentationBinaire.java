package org.natandaniel.m01_fondamentaux.c01_types_primitifs.lecon;

/**
 * Leçon 3/3 — Comment les entiers sont stockés en binaire (complément à deux).
 */
class Ex03_RepresentationBinaire {

    static String bits8(int n) {
        return String.format("%8s", Integer.toBinaryString(n & 0xFF)).replace(' ', '0');
    }

    static String bits32(int n) {
        return String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0');
    }

    public static void main(String[] args) {
        System.out.println("=== Entiers positifs : chaque bit vaut une puissance de 2 ===");
        System.out.println("  Valeur   :  128     64     32     16      8      4      2      1");
        System.out.println("  42  → " + bits8(42)  + "  (32+8+2 = 42)");
        System.out.println("  127 → " + bits8(127) + "  (max d'un byte signé : 7 bits à 1)");

        System.out.println("\n=== Entiers négatifs : le complément à deux ===");
        System.out.println("Pour obtenir -n : inverser tous les bits de n, puis ajouter 1.");
        System.out.println("  42  → " + bits32(42));
        System.out.println("  ~42 → " + bits32(~42)  + "  (NOT bit à bit)");
        System.out.println("  ~42 + 1 = " + (~42 + 1) + "  (= -42 : vérification)");
        System.out.println("  -1  → " + bits32(-1) + "  (TOUS les bits à 1)");
        System.out.println("Le bit de poids fort (bit 31) est le bit de signe : 0 = positif, 1 = négatif.");

        System.out.println("\n=== Integer.bitCount : compter les bits à 1 ===");
        System.out.println("  bitCount(15)  = " + Integer.bitCount(15)  + "  (1111)");
        System.out.println("  bitCount(255) = " + Integer.bitCount(255) + "  (11111111)");
        System.out.println("  bitCount(-1)  = " + Integer.bitCount(-1)  + "  (32 bits à 1)");
    }
}
