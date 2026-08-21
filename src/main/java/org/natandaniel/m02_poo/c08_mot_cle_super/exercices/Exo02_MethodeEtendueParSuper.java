package org.natandaniel.m02_poo.c08_mot_cle_super.exercices;

/**
 * Exercice — super.methode() : étendre le comportement d'une méthode redéfinie plutôt que le
 * dupliquer (JLS §15.12.1).
 */
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

        /**
         * Doit reprendre l'étiquette de Produit (via super.etiquette(), sans la dupliquer) et lui
         * ajouter " — solde <pourcentageSolde>%" à la fin.
         */
        @Override
        String etiquette() {
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
