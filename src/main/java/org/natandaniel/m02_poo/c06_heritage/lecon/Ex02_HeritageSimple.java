package org.natandaniel.m02_poo.c06_heritage.lecon;

/**
 * Leçon 2/3 — héritage simple : une classe n'a qu'UNE seule superclasse directe.
 */
class Ex02_HeritageSimple {

    static class Produit {
    }

    // extends ne prend qu'un seul nom de classe : contrairement à C++, Java n'autorise pas
    // l'héritage multiple de classes (les interfaces, elles, peuvent être multiples via
    // implements — vu dans un autre concept).
    static class ProduitPhysique extends Produit {
    }

    public static void main(String[] args) {
        System.out.println("=== Une seule superclasse directe ===");
        System.out.println("ProduitPhysique extends Produit : superclasse directe = "
                + ProduitPhysique.class.getSuperclass().getSimpleName());

        System.out.println("\n=== Sans extends, la superclasse directe est implicitement Object ===");
        System.out.println("Produit (pas de extends) : superclasse directe = "
                + Produit.class.getSuperclass().getSimpleName());

        System.out.println("\n=== 'class X extends A, B' n'existe pas en Java (contrairement au C++) ===");
        // class Hybride extends ProduitPhysique, Produit { } // ERREUR de syntaxe
        System.out.println("Une classe ne peut désigner qu'une seule superclasse directe après extends.");
    }
}
