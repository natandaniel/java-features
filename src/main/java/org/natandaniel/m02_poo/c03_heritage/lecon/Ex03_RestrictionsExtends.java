package org.natandaniel.m02_poo.c03_heritage.lecon;

/**
 * Leçon 3/3 — ce qui ne peut PAS être étendu (JLS §8.1.4).
 */
class Ex03_RestrictionsExtends {

    static final class ProduitPhysique {
        int poidsGrammes;
    }

    // class ProduitFragile extends ProduitPhysique { } // ERREUR : ProduitPhysique est final, elle ne peut pas être étendue

    public static void main(String[] args) {
        System.out.println("=== final : une classe déclarée final ne peut pas être étendue ===");
        System.out.println("'final class ProduitPhysique' : 'class X extends ProduitPhysique' serait une erreur de compilation.");

        System.out.println("\n=== D'autres classes ne s'étendent pas via un extends ordinaire ===");
        System.out.println("Enum   : seule une déclaration 'enum' étend java.lang.Enum (implicite, pas via extends).");
        System.out.println("Record : seule une déclaration 'record' étend java.lang.Record (implicite).");
        System.out.println("sealed : une classe sealed ne peut être étendue que par les sous-classes qu'elle");
        System.out.println("         autorise explicitement (permits).");

        System.out.println("\n=== Héritage circulaire : impossible ===");
        System.out.println("Une classe ne peut pas dépendre d'elle-même dans sa chaîne de superclasses");
        System.out.println("(ex. A extends B, B extends A) : ClassCircularityError au chargement.");
    }
}
