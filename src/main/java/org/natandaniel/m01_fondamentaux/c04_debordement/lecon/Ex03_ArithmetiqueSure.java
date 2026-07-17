package org.natandaniel.m01_fondamentaux.c04_debordement.lecon;

import java.math.BigInteger;

/**
 * Leçon 3/3 — Détecter ou éviter l'overflow : Math.*Exact et BigInteger.
 */
class Ex03_ArithmetiqueSure {

    public static void main(String[] args) {
        System.out.println("=== Math.addExact / multiplyExact : exception plutôt que silence ===");
        System.out.println("max + 1 (opérateur) = " + (Integer.MAX_VALUE + 1) + "  (silencieux)");
        try {
            Math.addExact(Integer.MAX_VALUE, 1);
        } catch (ArithmeticException e) {
            System.out.println("Math.addExact(MAX, 1) → ArithmeticException : " + e.getMessage());
        }

        System.out.println("\n=== Math.toIntExact : conversion long → int sécurisée ===");
        try {
            Math.toIntExact(5_000_000_000L);
        } catch (ArithmeticException e) {
            System.out.println("Math.toIntExact(5 milliards) → ArithmeticException (hors plage int)");
        }

        System.out.println("\n=== BigInteger : entiers de taille arbitraire ===");
        BigInteger fact20 = BigInteger.ONE;
        for (int k = 1; k <= 20; k++) {
            fact20 = fact20.multiply(BigInteger.valueOf(k));
        }
        System.out.println("20! = " + fact20 + "  (dépasse long, mais exact)");
    }
}
