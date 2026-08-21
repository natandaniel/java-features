package org.natandaniel.m02_poo.c08_mot_cle_super.solutions;

/** Solution de référence — étendre etiquette() via super.etiquette() plutôt que la dupliquer. */
class Exo02_MethodeEtendueParSuper {

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

    static class ProduitEnSolde extends Produit {
        double pourcentageSolde;

        ProduitEnSolde(String nom, double prix, double pourcentageSolde) {
            super(nom, prix);
            this.pourcentageSolde = pourcentageSolde;
        }

        @Override
        String etiquette() {
            return super.etiquette() + " — solde " + pourcentageSolde + "%";
        }
    }
}
