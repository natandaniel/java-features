package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.lecon;

/**
 * Leçon 1/2 — Les quatre opérateurs bit à bit : & | ^ ~.
 */
class Ex01_AndOrXorNot {

    static String bits8(int n) {
        return String.format("%8s", Integer.toBinaryString(n & 0xFF)).replace(' ', '0');
    }

    public static void main(String[] args) {
        int p = 0b10101010;  // 170
        int q = 0b11001100;  // 204
        System.out.println("p = " + bits8(p) + "  (" + p + ")");
        System.out.println("q = " + bits8(q) + "  (" + q + ")");

        System.out.println("\n--- AND (&) : 1 si les DEUX bits sont à 1 ---");
        System.out.println("p & q = " + bits8(p & q) + "  (" + (p & q) + ")");

        System.out.println("\n--- OR (|) : 1 si AU MOINS UN bit est à 1 ---");
        System.out.println("p | q = " + bits8(p | q) + "  (" + (p | q) + ")");

        System.out.println("\n--- XOR (^) : 1 si les bits sont DIFFÉRENTS ---");
        System.out.println("p ^ q = " + bits8(p ^ q) + "  (" + (p ^ q) + ")");

        System.out.println("\n--- NOT (~) : inverse tous les bits (complément à un) ---");
        System.out.println("~p = " + bits8(~p) + "  (" + ~p + " sur 32 bits ; ~n = -(n+1))");
    }
}
