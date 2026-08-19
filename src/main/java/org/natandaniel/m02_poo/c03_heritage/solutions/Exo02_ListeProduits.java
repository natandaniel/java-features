package org.natandaniel.m02_poo.c03_heritage.solutions;

import java.util.List;

/** Solution de référence — substituabilité de référence sur une liste mixte de Produit/ProduitPhysique. */
class Exo02_ListeProduits {

    static class Produit {
        String nom;
        double prix;

        Produit(String nom, double prix) {
            this.nom = nom;
            this.prix = prix;
        }
    }

    static class ProduitPhysique extends Produit {
        int poidsGrammes;

        ProduitPhysique(String nom, double prix, int poidsGrammes) {
            super(nom, prix);
            this.poidsGrammes = poidsGrammes;
        }
    }

    static double prixLePlusEleve(List<Produit> produits) {
        double max = produits.get(0).prix;
        for (Produit p : produits) {
            if (p.prix > max) {
                max = p.prix;
            }
        }
        return max;
    }
}
