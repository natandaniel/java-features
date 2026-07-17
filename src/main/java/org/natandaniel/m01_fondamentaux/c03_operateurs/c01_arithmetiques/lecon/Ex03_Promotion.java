package org.natandaniel.m01_fondamentaux.c03_operateurs.c01_arithmetiques.lecon;

/**
 * Leçon 3/3 — Promotion numérique, opérateurs composés, pré/post-incrément.
 */
class Ex03_Promotion {

    public static void main(String[] args) {
        System.out.println("=== Promotion numérique (JLS §5.6.2) ===");
        System.out.println("Java promeut les opérandes en int avant tout calcul entier.");
        byte x = 10, y = 20;
        // byte z = x + y;  // ERREUR : x + y est un int
        int z = x + y;
        System.out.println("byte(10) + byte(20) → type int : " + z);

        System.out.println("\n=== Opérateurs composés (+=, etc.) : cast implicite ===");
        System.out.println("a op= b  équivaut à  a = (type de a)(a op b).");
        byte c = 120;
        c += 20;   // (byte)(140) → overflow silencieux
        System.out.println("byte c = 120; c += 20 → " + c + "  (140 > 127 : overflow silencieux !)");

        System.out.println("\n=== Pré vs post-incrément ===");
        int p = 5;
        int a = p++;   // a reçoit 5, PUIS p devient 6
        System.out.println("p = 5; a = p++  →  a = " + a + ", p = " + p);
        int q = 5;
        int d = ++q;   // q devient 6, PUIS d reçoit 6
        System.out.println("q = 5; d = ++q  →  d = " + d + ", q = " + q);
    }
}
