package org.natandaniel.m01_fondamentaux.c03_operateurs.c01_arithmetiques.lecon;

/**
 * Leçon 2/3 — Division entière et modulo : troncature vers zéro et signe du reste.
 */
class Ex02_DivisionEtModulo {

    static void verifierInvariant(int a, int b) {
        int q = a / b;
        int r = a % b;
        System.out.println("  " + a + " / " + b + " = " + q + ", reste " + r
            + "   →  (" + q + " * " + b + ") + " + r + " = " + (q * b + r) + "  (== " + a + ")");
    }

    public static void main(String[] args) {
        System.out.println("=== Division entière : troncature vers zéro (JLS §15.17.2) ===");
        System.out.println("En maths, -7 ÷ 2 = -3,5 (plancher -4). En Java, on tronque vers zéro :");
        System.out.println("   7 /  2 = " + (7 / 2));
        System.out.println("  -7 /  2 = " + (-7 / 2) + "   (-3, PAS -4)");
        System.out.println("  -7 / -2 = " + (-7 / -2));

        System.out.println("\n=== Modulo (%) : le reste a le signe du DIVIDENDE ===");
        System.out.println("   7 %  2 = " + (7 % 2));
        System.out.println("  -7 %  2 = " + (-7 % 2) + "   (-1, PAS 1)");
        System.out.println("   7 % -2 = " + (7 % -2) + "    (1)");

        System.out.println("\n=== Invariant toujours vrai : a == (a/b)*b + (a%b) ===");
        verifierInvariant(7, 2);
        verifierInvariant(-7, 2);
        verifierInvariant(7, -2);
        verifierInvariant(-7, -2);
    }
}
