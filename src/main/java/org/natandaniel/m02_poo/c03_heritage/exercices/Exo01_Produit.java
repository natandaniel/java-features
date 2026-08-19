package org.natandaniel.m02_poo.c03_heritage.exercices;

/**
 * Exercices — hiérarchie Produit / ProduitPhysique : les champs hérités s'utilisent comme les champs propres.
 */
class Exo01_Produit {

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
        int volumeCm3;

        ProduitPhysique(String nom, double prix, int poidsGrammes, int volumeCm3) {
            super(nom, prix);
            this.poidsGrammes = poidsGrammes;
            this.volumeCm3 = volumeCm3;
        }
    }

    /** true si le produit dépasse poidsLimite (champ poidsGrammes, hérité... non, propre à ProduitPhysique). */
    static boolean depassePoidsLimite(ProduitPhysique produit, int poidsLimite) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /** true si le produit est un colis standard : poidsGrammes <= 2000 ET volumeCm3 <= 5000. */
    static boolean estColisStandard(ProduitPhysique produit) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
