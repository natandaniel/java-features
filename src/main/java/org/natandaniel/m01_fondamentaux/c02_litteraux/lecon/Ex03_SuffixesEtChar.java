package org.natandaniel.m01_fondamentaux.c02_litteraux.lecon;

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
        int codePoint = lettre;              // widening automatique : char → int
        char suivant = (char) ('A' + 1);     // 'A' + 1 est un int ; cast (char) requis
        System.out.println("'A'            = " + lettre + "  (code Unicode : " + codePoint + ")");
        System.out.println("'A' + 1        = " + ('A' + 1) + "  (type int après promotion)");
        System.out.println("(char)('A'+1)  = " + suivant);
        System.out.println("'Z' - 'A'      = " + ('Z' - 'A') + "  (distance alphabétique)");
    }
}
