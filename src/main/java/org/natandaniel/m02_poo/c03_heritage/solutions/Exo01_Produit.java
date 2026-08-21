package org.natandaniel.m02_poo.c03_heritage.solutions;

/** Solution de référence — hiérarchie Produit / ProduitPhysique. */
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
            // super(nom, prix) appelle le constructeur de Produit — nécessaire ici car
            // Produit n'a pas de constructeur sans argument. Détail du mot-clé super :
            // concept séparé, à venir.
            super(nom, prix);
            this.poidsGrammes = poidsGrammes;
            this.volumeCm3 = volumeCm3;
        }
    }

    static boolean depassePoidsLimite(ProduitPhysique produit, int poidsLimite) {
        return produit.poidsGrammes > poidsLimite;
    }

    static boolean estColisStandard(ProduitPhysique produit) {
        return produit.poidsGrammes <= 2000 && produit.volumeCm3 <= 5000;
    }
}
