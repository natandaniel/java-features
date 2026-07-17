package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.lecon;

/**
 * Leçon 4/4 — Ce à quoi servent VRAIMENT les décalages.
 */
class Ex06_ApplicationsPratiques {

    public static void main(String[] args) {
        System.out.println("=== 1. Multiplier / diviser par une puissance de 2 ===");
        System.out.println("  7 << 3 = " + (7 << 3) + "  (= 7 × 8)");
        System.out.println("  56 >> 3 = " + (56 >> 3) + "  (= 56 / 8)");

        System.out.println("\n=== 2. Tester qu'un nombre est une puissance de 2 ===");
        System.out.println("Astuce : n > 0 et (n & (n-1)) == 0.");
        for (int n : new int[]{1, 2, 3, 8, 12, 16}) {
            boolean p2 = n > 0 && (n & (n - 1)) == 0;
            System.out.println("  " + n + " → " + p2);
        }

        System.out.println("\n=== 3. Empaqueter une couleur RVB dans un int ===");
        int r = 0xFF, g = 0xEC, b = 0xD1;
        int rgb = (r << 16) | (g << 8) | b;
        System.out.println("  empaqueté = #" + String.format("%06X", rgb));
        System.out.println("  rouge = " + ((rgb >>> 16) & 0xFF)
            + ", vert = " + ((rgb >>> 8) & 0xFF)
            + ", bleu = " + (rgb & 0xFF));
    }
}
