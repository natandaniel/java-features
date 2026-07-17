package org.natandaniel.m01_fondamentaux.c03_litteraux.lecon;

/**
 * Leçon 2/3 — Deux améliorations de lisibilité introduites en Java 7.
 *
 * @since Java 7 (JLS §3.10.1) — littéraux binaires 0b et underscores.
 */
class Ex02_BinaireEtUnderscores {

    public static void main(String[] args) {
        // @since Java 7 — littéraux binaires (préfixe 0b ou 0B)
        System.out.println("=== Littéraux binaires 0b (Java 7) ===");
        int binaire = 0b11111111;
        System.out.println("0b11111111 = " + binaire + "  (identique à 255 et à 0xFF)");
        System.out.println("Utile pour les masques : la structure bit à bit est visible directement.");
        System.out.println("  0b00001000 = " + 0b00001000 + "  (le bit 3, sans calcul mental)");

        // @since Java 7 — underscores entre les chiffres
        System.out.println("\n=== Underscores pour la lisibilité (Java 7) ===");
        int  million  = 1_000_000;
        long milliard = 1_000_000_000L;
        int  masque   = 0b1111_0000_1111_0000;   // groupé en quartets
        int  couleur  = 0xFF_EC_D1;              // groupé en octets RVB
        System.out.println("1_000_000           = " + million);
        System.out.println("1_000_000_000L      = " + milliard);
        System.out.println("0b1111_0000_1111_0000 = " + Integer.toBinaryString(masque));
        System.out.println("0xFF_EC_D1          = #" + Integer.toHexString(couleur).toUpperCase());

        System.out.println("\nRègles : pas d'underscore en tête/fin du littéral, ni collé au point décimal.");
    }
}
