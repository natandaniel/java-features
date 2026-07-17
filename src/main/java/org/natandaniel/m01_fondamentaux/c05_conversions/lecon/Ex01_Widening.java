package org.natandaniel.m01_fondamentaux.c05_conversions.lecon;

/**
 * Leçon 1/3 — Widening : conversions élargissantes automatiques (JLS §5.1.2).
 */
class Ex01_Widening {

    public static void main(String[] args) {
        System.out.println("=== La chaîne de widening : byte → short → int → long → float → double ===");
        byte b = 100;
        short s = b;   // automatique
        int i = s;     // automatique
        long l = i;    // automatique
        float f = l;   // automatique
        double d = f;  // automatique
        System.out.println("byte(" + b + ") → … → double(" + d + ")  (aucun cast nécessaire)");

        System.out.println("\n=== Widening n'est pas toujours sans perte ! ===");
        long grand = 1_234_567_890_123L;
        float approx = grand;   // float n'a que ~7 chiffres significatifs
        System.out.println("long  = " + grand);
        System.out.println("float = " + approx + "  (arrondi : la conversion est légale mais approxime)");
    }
}
