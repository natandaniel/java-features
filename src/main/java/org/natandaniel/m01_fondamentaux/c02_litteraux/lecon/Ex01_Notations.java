package org.natandaniel.m01_fondamentaux.c02_litteraux.lecon;

/**
 * Leçon 1/3 — Écrire un même entier dans plusieurs bases.
 *
 * Un littéral est la forme écrite d'une valeur dans le code source. Une même valeur
 * entière peut s'écrire en décimal, hexadécimal ou octal — c'est exactement le même
 * nombre, seule la notation change.
 */
class Ex01_Notations {

    public static void main(String[] args) {
        System.out.println("=== Même valeur, trois notations historiques ===");
        int decimal     = 255;
        int hexadecimal = 0xFF;   // préfixe 0x : base 16 — pratique pour masques et couleurs
        int octal       = 0377;   // préfixe 0  : base 8  — rarement utilisé aujourd'hui
        System.out.println("décimal     : " + decimal);
        System.out.println("hexadécimal : " + hexadecimal + "  (0xFF)");
        System.out.println("octal       : " + octal       + "  (0377)");
        System.out.println("Tous égaux  : " + (decimal == hexadecimal && hexadecimal == octal));

        System.out.println("\nPiège : 0377 (octal) ≠ 377 (décimal). Un 0 en tête change la base !");
        System.out.println("0377 vaut en réalité : " + 0377);

        System.out.println("\nLa notation binaire 0b est plus récente (Java 7) → voir Ex02.");
    }
}
