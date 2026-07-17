package org.natandaniel.m01_fondamentaux.c06_flottants.lecon;

/**
 * Leçon 2/3 — Les valeurs spéciales : Infinity et NaN (JLS §4.2.3).
 */
class Ex02_InfinityEtNaN {

    public static void main(String[] args) {
        System.out.println("=== Division flottante par zéro → Infinity, PAS d'exception ===");
        double posInf = 1.0 / 0.0;
        double negInf = -1.0 / 0.0;
        System.out.println(" 1.0 / 0.0 = " + posInf);
        System.out.println("-1.0 / 0.0 = " + negInf);
        System.out.println("Infinity + 1        = " + (posInf + 1));
        System.out.println("Infinity - Infinity = " + (posInf + negInf) + "  (indéfini → NaN)");

        System.out.println("\n=== NaN : Not-a-Number ===");
        double nan = 0.0 / 0.0;
        System.out.println("0.0 / 0.0 = " + nan);
        System.out.println("nan == nan → " + (nan == nan) + "  (!!) NaN n'est égal à RIEN, pas même à lui-même");
        System.out.println("nan != nan → " + (nan != nan) + "  (seul cas où x != x est vrai)");
        System.out.println("nan + 1   = " + (nan + 1) + "  (NaN est contagieux)");
        System.out.println("nan * 0   = " + (nan * 0) + "  (pas 0 !)");
    }
}
