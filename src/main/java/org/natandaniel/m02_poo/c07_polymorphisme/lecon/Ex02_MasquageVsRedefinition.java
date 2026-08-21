package org.natandaniel.m02_poo.c07_polymorphisme.lecon;

/**
 * Leçon 2/3 — masquage (hiding) vs redéfinition (overriding) : ce qui se résout sur le type
 * DÉCLARÉ, à la compilation (champs, méthodes static), et ce qui se résout sur le type RÉEL, à
 * l'exécution (méthodes d'instance) (JLS §8.3, §8.4.8.1, §8.4.8.2).
 */
class Ex02_MasquageVsRedefinition {

    static class Produit {
        static String categorie = "Générique"; // champ static : masqué, jamais redéfini
        String nom = "Produit générique";       // champ d'instance : masqué aussi — les champs ne sont JAMAIS polymorphes

        static String origine() { // méthode static : masquée, comme un champ
            return "Fabricant générique";
        }

        String etiquette() { // méthode d'instance : redéfinie, liaison dynamique
            return "Produit : " + nom;
        }
    }

    static class ProduitImporte extends Produit {
        static String categorie = "Import";
        String nom = "Produit importé";

        static String origine() {
            return "Fabricant étranger";
        }

        @Override
        String etiquette() {
            return "Produit importé : " + nom;
        }
    }

    public static void main(String[] args) {
        // p est déclaré Produit, mais référence un ProduitImporte : le cas qui distingue
        // masquage et redéfinition (JLS §8.4.8.2, Example 8.4.8.2-1).
        Produit p = new ProduitImporte();

        System.out.println("=== Champ d'instance : masqué, résolu sur le type DÉCLARÉ ===");
        System.out.println("p.nom (p déclaré Produit) = " + p.nom);

        System.out.println("\n=== Méthode static : masquée, résolue sur le type DÉCLARÉ (comme un champ) ===");
        System.out.println("p.origine() (p déclaré Produit) = " + p.origine());
        System.out.println("Le compilateur choisit Produit.origine() d'après le type déclaré de p,");
        System.out.println("sans jamais regarder le type réel de l'objet référencé.");

        System.out.println("\n=== Méthode d'instance : redéfinie, résolue sur le type RÉEL (liaison dynamique) ===");
        System.out.println("p.etiquette() (p déclaré Produit, réel ProduitImporte) = " + p.etiquette());
        System.out.println("Ici, à l'inverse, c'est la version de ProduitImporte qui est appelée : c'est");
        System.out.println("précisément ce que le polymorphisme rend possible pour les méthodes d'instance.");
    }
}
