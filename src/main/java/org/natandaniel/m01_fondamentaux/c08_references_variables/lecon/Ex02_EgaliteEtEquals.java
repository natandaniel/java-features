package org.natandaniel.m01_fondamentaux.c08_references_variables.lecon;

/**
 * Leçon 2/3 — == compare les adresses, .equals() compare le contenu.
 */
class Ex02_EgaliteEtEquals {

    public static void main(String[] args) {
        String a = new String("hello");
        String b = new String("hello");

        System.out.println("=== Deux objets distincts, même contenu ===");
        System.out.println("a = new String(\"hello\"), b = new String(\"hello\")");
        System.out.println("a == b      → " + (a == b) + "  (adresses différentes)");
        System.out.println("a.equals(b) → " + a.equals(b) + "  (même contenu)");

        System.out.println("\n=== Règle ===");
        System.out.println("== sur des références = même objet en mémoire.");
        System.out.println(".equals() = égalité de contenu (à condition que la classe la définisse).");
        System.out.println("Erreur classique : comparer des String avec == au lieu de .equals().");
    }
}
