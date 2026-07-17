package org.natandaniel.m01_fondamentaux.c06_flottants.lecon;

/**
 * Leçon 3/3 — Tester NaN, Infinity et l'égalité correctement.
 */
class Ex03_TesterCorrectement {

    public static void main(String[] args) {
        double nan = 0.0 / 0.0;
        double posInf = 1.0 / 0.0;

        System.out.println("=== Les bons outils ===");
        System.out.println("Double.isNaN(nan)         → " + Double.isNaN(nan));
        System.out.println("Double.isInfinite(posInf) → " + Double.isInfinite(posInf));
        System.out.println("Double.isFinite(3.14)     → " + Double.isFinite(3.14) + "  (@since Java 8)");

        System.out.println("\n=== Le piège à éviter ===");
        System.out.println("nan == Double.NaN → " + (nan == Double.NaN) + "  (TOUJOURS false : utiliser Double.isNaN)");

        System.out.println("\n=== Comparer deux flottants : tolérance epsilon ===");
        double a = 0.1 + 0.2, b = 0.3;
        System.out.println("(0.1 + 0.2) == 0.3        → " + (a == b) + "  (faux à cause de l'arrondi)");
        System.out.println("|a - b| < 1e-9            → " + (Math.abs(a - b) < 1e-9) + "  (la bonne approche)");
    }
}
