package org.natandaniel.m02_poo.c04_polymorphisme.exercices;

import java.util.List;

/**
 * Exercices — retour covariant (appliquerRemise) et masquage de champ static (categorie) :
 * seule la méthode d'instance est résolue sur le type réel, jamais le champ static.
 */
class Exo02_RemiseEtMasquage {

    static class Produit {
        static String categorie = "Générique";

        String nom;
        double prix;

        Produit(String nom, double prix) {
            this.nom = nom;
            this.prix = prix;
        }

        Produit appliquerRemise(double pourcentage) {
            return new Produit(nom, prix * (1 - pourcentage / 100));
        }
    }

    static class ProduitPhysique extends Produit {
        static String categorie = "Import";

        int poidsGrammes;

        ProduitPhysique(String nom, double prix, int poidsGrammes) {
            super(nom, prix);
            this.poidsGrammes = poidsGrammes;
        }

        @Override
        ProduitPhysique appliquerRemise(double pourcentage) {
            return new ProduitPhysique(nom, prix * (1 - pourcentage / 100), poidsGrammes);
        }
    }

    /** Somme des prix de la liste après application de la remise à chaque produit. */
    static double prixTotalApresRemise(List<Produit> produits, double pourcentage) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * La catégorie vue à travers une référence déclarée Produit : un champ static est masqué,
     * pas redéfini — résolu sur le type déclaré du paramètre, jamais sur le type réel de l'objet.
     */
    static String categorieVueDepuisProduit(Produit produit) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
