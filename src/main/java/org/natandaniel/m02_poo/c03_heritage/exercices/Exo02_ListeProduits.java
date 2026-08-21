package org.natandaniel.m02_poo.c03_heritage.exercices;

import java.util.List;

/**
 * Exercices — une sous-classe EST-UNE instance de sa superclasse : substituabilité de référence.
 */
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
            // super(nom, prix) appelle le constructeur de Produit — nécessaire ici car
            // Produit n'a pas de constructeur sans argument. Détail du mot-clé super :
            // concept séparé, à venir.
            super(nom, prix);
            this.poidsGrammes = poidsGrammes;
        }
    }

    /**
     * Prix le plus élevé d'une liste de Produit (peut mélanger des Produit et des
     * ProduitPhysique : un ProduitPhysique EST-UN Produit, donc s'ajoute à List<Produit>
     * sans conversion). La liste n'est jamais vide.
     */
    static double prixLePlusEleve(List<Produit> produits) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
