package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.lecon;

/**
 * Leçon 2/4 — Décalage à droite signé ({@code >>}) : diviser par 2 en gardant le signe.
 */
class Ex04_DecalageDroitSigne {

    static String bits32(int n) {
        return String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0');
    }

    public static void main(String[] args) {
        System.out.println("=== >>  : décale à droite, RÉPLIQUE le bit de signe à gauche ===");
        System.out.println("Sur les positifs, c'est une division entière par 2^k :");
        System.out.println("  8 >> 1 = " + (8 >> 1));
        System.out.println("  8 >> 2 = " + (8 >> 2));

        System.out.println("\n=== Extension de signe sur les négatifs ===");
        System.out.println("  -8       → " + bits32(-8));
        System.out.println("  -8 >> 1  → " + bits32(-8 >> 1) + "  = " + (-8 >> 1) + "  (reste négatif : 1 réinjectés à gauche)");
        System.out.println("  -8 >> 2  → " + bits32(-8 >> 2) + "  = " + (-8 >> 2));

        System.out.println("\nNote : >> n'égale PAS toujours / 2 sur les négatifs impairs (arrondi vers -∞).");
        System.out.println("  -1 >> 1 = " + (-1 >> 1) + "   alors que -1 / 2 = " + (-1 / 2));
    }
}
