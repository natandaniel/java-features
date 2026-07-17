package org.natandaniel.m01_fondamentaux.c06_flottants.lecon;

/**
 * Leçon 1/3 — float vs double : une question de précision (IEEE 754).
 */
class Ex01_Precision {

    public static void main(String[] args) {
        System.out.println("=== float ≈ 7 chiffres significatifs, double ≈ 15-16 ===");
        float f = 1.0f / 3.0f;
        double d = 1.0 / 3.0;
        System.out.println("1.0f / 3.0f (float)  = " + f);
        System.out.println("1.0  / 3.0  (double) = " + d);

        System.out.println("\n=== Les flottants ne sont pas exacts en base 10 ===");
        System.out.println("0.1 + 0.2 = " + (0.1 + 0.2) + "  (pas exactement 0.3 !)");
        System.out.println("→ Ne jamais comparer des flottants avec == après un calcul.");

        System.out.println("\nRègle pratique : utiliser double par défaut ; float seulement si la mémoire est contrainte.");
    }
}
