package org.natandaniel.m02_poo.c04_polymorphisme.solutions;

import java.util.List;

/** Solution de référence — retour covariant sur appliquerRemise, masquage du champ static categorie. */
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

    static double prixTotalApresRemise(List<Produit> produits, double pourcentage) {
        double total = 0;
        for (Produit produit : produits) {
            total += produit.appliquerRemise(pourcentage).prix;
        }
        return total;
    }

    static String categorieVueDepuisProduit(Produit produit) {
        return produit.categorie;
    }
}
