package org.natandaniel.m02_poo.c15_interfaces_classes_abstraites.solutions;

/** Solution de référence — les fonds sont suffisants si le montant ne dépasse pas le solde. */
class Exo02_SousClasseDeClasseAbstraite {

    abstract static class TraitementPaiement {
        private final double montant;

        TraitementPaiement(double montant) {
            this.montant = montant;
        }

        double montant() {
            return montant;
        }

        abstract boolean verifierFonds();

        boolean executer() {
            return verifierFonds();
        }
    }

    static class TraitementVirement extends TraitementPaiement {
        private final double soldeDisponible;

        TraitementVirement(double montant, double soldeDisponible) {
            super(montant);
            this.soldeDisponible = soldeDisponible;
        }

        @Override
        boolean verifierFonds() {
            return montant() <= soldeDisponible;
        }
    }
}
