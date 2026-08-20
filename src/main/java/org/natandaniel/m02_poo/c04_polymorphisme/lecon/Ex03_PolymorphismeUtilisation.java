package org.natandaniel.m02_poo.c04_polymorphisme.lecon;

import java.util.List;

/**
 * Leçon 3/3 — le polymorphisme à l'usage : un même appel, sur une référence de type superclasse,
 * dont le comportement dépend du type réel de l'objet — sans que l'appelant ait besoin de le
 * connaître à l'avance (JLS §8.4.8.1).
 */
class Ex03_PolymorphismeUtilisation {

    static class Produit {
        String nom;
        double prix;

        Produit(String nom, double prix) {
            this.nom = nom;
            this.prix = prix;
        }

        String etiquette() {
            return nom + " — " + prix + " €";
        }
    }

    static class ProduitPhysique extends Produit {
        int poidsGrammes;

        ProduitPhysique(String nom, double prix, int poidsGrammes) {
            super(nom, prix);
            this.poidsGrammes = poidsGrammes;
        }

        @Override
        String etiquette() {
            return nom + " — " + prix + " € — " + poidsGrammes + " g";
        }
    }

    static class ProduitNumerique extends Produit {
        long tailleOctets;

        ProduitNumerique(String nom, double prix, long tailleOctets) {
            super(nom, prix);
            this.tailleOctets = tailleOctets;
        }

        @Override
        String etiquette() {
            return nom + " — " + prix + " € — téléchargement " + (tailleOctets / 1_000_000) + " Mo";
        }
    }

    public static void main(String[] args) {
        List<Produit> catalogue = List.of(
                new Produit("Carte cadeau", 20.00),
                new ProduitPhysique("Roman broché", 14.90, 400),
                new ProduitNumerique("Ebook", 9.90, 3_500_000)
        );

        System.out.println("=== Même appel, comportement différent selon le type réel ===");
        for (Produit produit : catalogue) {
            // Cette boucle ne connaît que Produit : elle ne sait pas, et n'a pas besoin de savoir,
            // si chaque élément est un Produit, un ProduitPhysique ou un ProduitNumerique.
            System.out.println(produit.etiquette());
        }
    }
}
