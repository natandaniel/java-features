package org.natandaniel.m02_poo.c03_heritage.lecon;

/**
 * Leçon 1/3 — la chaîne de superclasses/sous-classes (JLS §8.1.4).
 */
class Ex01_ChaineDHeritage {

    // Sans extends, la superclasse directe est implicitement Object.
    static class Produit {
        String nom;
        double prix;

        Produit(String nom, double prix) {
            this.nom = nom;
            this.prix = prix;
        }
    }

    // extends déclare UNE seule superclasse directe : héritage simple.
    // ProduitPhysique AJOUTE poidsGrammes/volumeCm3 ; rien de ce que Produit garantit
    // (nom, prix) n'est restreint.
    static class ProduitPhysique extends Produit {
        int poidsGrammes;
        int volumeCm3;

        ProduitPhysique(String nom, double prix, int poidsGrammes, int volumeCm3) {
            // super(nom, prix) appelle le constructeur de Produit — nécessaire ici car
            // Produit n'a pas de constructeur sans argument. Détail du mot-clé super :
            // concept séparé, à venir.
            super(nom, prix);
            this.poidsGrammes = poidsGrammes;
            this.volumeCm3 = volumeCm3;
        }
    }

    // La relation de superclasse est transitive : ProduitPhysiquePerissable hérite
    // aussi de Produit, pas seulement de ProduitPhysique. Encore un pur ajout
    // (datePeremption, estPerime()) — aucune restriction sur les niveaux précédents.
    static class ProduitPhysiquePerissable extends ProduitPhysique {
        String datePeremption; // format simplifié AAAA-MM-JJ pour la démo

        ProduitPhysiquePerissable(String nom, double prix, int poidsGrammes,
                                   int volumeCm3, String datePeremption) {
            // Même principe : appelle ici le constructeur de ProduitPhysique.
            super(nom, prix, poidsGrammes, volumeCm3);
            this.datePeremption = datePeremption;
        }

        boolean estPerime(String dateDuJour) {
            return dateDuJour.compareTo(datePeremption) > 0;
        }
    }

    public static void main(String[] args) {
        ProduitPhysiquePerissable yaourt =
                new ProduitPhysiquePerissable("Yaourt nature x4", 2.50, 500, 750, "2026-08-20");

        System.out.println("=== Réutilisation d'implémentation ===");
        // nom/prix viennent de Produit, poidsGrammes/volumeCm3 de ProduitPhysique :
        // ProduitPhysiquePerissable n'a rien eu à redéclarer pour en hériter.
        System.out.println("nom (hérité de Produit) = " + yaourt.nom);
        System.out.println("prix (hérité de Produit) = " + yaourt.prix);
        System.out.println("poidsGrammes (hérité de ProduitPhysique) = " + yaourt.poidsGrammes);
        System.out.println("datePeremption (propre à ProduitPhysiquePerissable) = " + yaourt.datePeremption);

        System.out.println("\n=== Relation transitive ===");
        System.out.println("ProduitPhysiquePerissable est une sous-classe directe de ProduitPhysique,");
        System.out.println("et une sous-classe (transitive) de Produit.");
        Produit p = yaourt; // tout ProduitPhysiquePerissable EST-UN Produit
        System.out.println("Produit p = yaourt ; p.nom = " + p.nom);

        System.out.println("\n=== Rien n'est restreint en chemin ===");
        System.out.println("estPerime(...) ne fait qu'AJOUTER un comportement ; prix/nom de Produit");
        System.out.println("restent utilisables sans surprise à travers toute la chaîne.");
        System.out.println("yaourt.estPerime(\"2026-08-25\") = " + yaourt.estPerime("2026-08-25"));

        System.out.println("\n=== Restriction : une classe final ne peut pas être étendue ===");
        System.out.println("Si ProduitPhysique était déclarée 'final class ProduitPhysique extends Produit',");
        System.out.println("'class ProduitPhysiquePerissable extends ProduitPhysique' serait une erreur de compilation.");
        // final class ProduitPhysique extends Produit { ... }
        // class ProduitPhysiquePerissable extends ProduitPhysique { ... }  // <-- ne compilerait pas
    }
}
