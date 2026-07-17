package org.natandaniel.m01_fondamentaux.c04_debordement.lecon;

/**
 * Leçon 1/3 — L'overflow entier est SILENCIEUX (JLS §4.2.1).
 */
class Ex01_ComportementSilencieux {

    public static void main(String[] args) {
        System.out.println("=== Java ne lève pas d'exception sur l'overflow entier ===");
        System.out.println("Le résultat « boucle » : dépasser MAX_VALUE retombe à MIN_VALUE.");
        System.out.println("Integer.MAX_VALUE     = " + Integer.MAX_VALUE);
        System.out.println("Integer.MAX_VALUE + 1 = " + (Integer.MAX_VALUE + 1) + "  (= MIN_VALUE !)");
        System.out.println("Integer.MIN_VALUE - 1 = " + (Integer.MIN_VALUE - 1) + "  (= MAX_VALUE !)");

        System.out.println("\n=== Pourquoi ? Le bit de signe bascule ===");
        System.out.println("MAX en binaire : " + Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println("  +1 met le bit de signe à 1 → on bascule côté négatif.");

        System.out.println("\n=== int vs long pour les grands calculs ===");
        int i = 1000000;
        System.out.println("int  : 1000000 * 1000000 = " + (i * i) + "  (overflow !)");
        long l = 1000000L;
        System.out.println("long : 1000000 * 1000000 = " + (l * l) + "  (correct)");
    }
}
