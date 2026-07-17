package org.natandaniel.m01_fondamentaux.c04_operateurs.c01_arithmetiques.lecon;

/**
 * Leçon 3/3 — Opérateurs composés (+=, ...) et pré/post-incrément.
 * (Les règles de promotion de type dans les expressions sont traitées en c07_conversions.)
 */
class Ex03_Promotion {

    public static void main(String[] args) {
        System.out.println("=== Opérateurs composés (+=, etc.) : cast implicite ===");
        System.out.println("a op= b  équivaut à  a = (type de a)(a op b).");
        byte c = 120;
        c += 20;   // (byte)(140) → overflow silencieux (voir c05_debordement)
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
