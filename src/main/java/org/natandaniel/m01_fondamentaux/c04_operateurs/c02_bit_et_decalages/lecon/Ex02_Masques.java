package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.lecon;

/**
 * Leçon 2/2 — Les masques de bits : activer, désactiver, basculer, tester un bit.
 */
class Ex02_Masques {

    static String bits8(int n) {
        return String.format("%8s", Integer.toBinaryString(n & 0xFF)).replace(' ', '0');
    }

    public static void main(String[] args) {
        System.out.println("=== Stocker plusieurs drapeaux dans un seul entier ===");
        int flags = 0b00000000;
        int BIT0 = 0b00000001;
        int BIT2 = 0b00000100;

        flags |= BIT0;                       // activer : OR avec le masque
        System.out.println("activer  bit 0 (|=)  : " + bits8(flags));
        flags |= BIT2;
        System.out.println("activer  bit 2 (|=)  : " + bits8(flags));

        flags ^= BIT0;                       // basculer : XOR avec le masque
        System.out.println("basculer bit 0 (^=)  : " + bits8(flags) + "  (était à 1 → 0)");

        boolean bit2Actif = (flags & BIT2) != 0;  // tester : AND puis comparer à 0
        System.out.println("bit 2 actif ? (&)    : " + bit2Actif);

        flags &= ~BIT2;                      // désactiver : AND avec le complément du masque
        System.out.println("désactiver bit 2 (&=~): " + bits8(flags));
    }
}
