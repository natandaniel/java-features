package org.natandaniel.m01_fondamentaux.c03_litteraux.lecon;

/**
 * Leçon 3/3 — Suffixes de type et char vu comme un entier.
 */
class Ex03_SuffixesEtChar {

    public static void main(String[] args) {
        System.out.println("=== Suffixes de type ===");
        // long : suffixe L obligatoire si la valeur dépasse Integer.MAX_VALUE
        long grand = 10000000000L;       // sans L → erreur : « integer number too large »
        // float : suffixe f obligatoire, sinon le littéral est un double
        float f = 3.14f;                 // float sansF = 3.14; → erreur (double vers float)
        double d = 3.14;                 // double par défaut, pas de suffixe requis
        System.out.println("long   : " + grand + "  (suffixe L)");
        System.out.println("float  : " + f + "  (suffixe f)");
        System.out.println("double : " + d + "  (pas de suffixe)");

        System.out.println("\n=== char est un entier non signé de 16 bits ===");
        char lettre = 'A';
        System.out.println("Le littéral 'A' désigne le code Unicode " + (int) lettre + ".");
        System.out.println("Un char stocke donc un entier de 0 à 65535.");
        System.out.println("→ Son arithmétique (promotion, cast char↔int) est traitée en c07_conversions.");
    }
}
