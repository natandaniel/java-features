package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.lecon;

/**
 * Leçon 3/4 — Décalage à droite NON signé ({@code >>>}) : toujours des 0 à gauche.
 */
class Ex05_DecalageDroitNonSigne {

    static String bits32(int n) {
        return String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0');
    }

    public static void main(String[] args) {
        System.out.println("=== >>> : décale à droite en insérant TOUJOURS des 0 (pas d'extension de signe) ===");
        System.out.println("  -1        → " + bits32(-1));
        System.out.println("  -1 >>> 1  → " + bits32(-1 >>> 1) + "  = " + (-1 >>> 1));
        System.out.println("  -1 >>> 1 == Integer.MAX_VALUE : " + ((-1 >>> 1) == Integer.MAX_VALUE));

        System.out.println("\n=== >> vs >>> sur un négatif ===");
        System.out.println("  -8 >> 1  = " + (-8 >> 1) + "   (signé : reste négatif)");
        System.out.println("  -8 >>> 1 = " + (-8 >>> 1) + "   (non signé : devient un grand positif)");

        System.out.println("\nJava n'a pas de type entier non signé ; >>> permet de traiter les bits comme non signés.");
    }
}
