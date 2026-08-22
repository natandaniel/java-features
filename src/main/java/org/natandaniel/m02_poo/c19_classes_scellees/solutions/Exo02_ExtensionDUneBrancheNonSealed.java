package org.natandaniel.m02_poo.c19_classes_scellees.solutions;

/** Solution de référence — la conversion est un simple produit montant × taux. */
class Exo02_ExtensionDUneBrancheNonSealed {

    sealed static class ModePaiement permits CarteBancaire, Especes {
        private final String reference;

        ModePaiement(String reference) {
            this.reference = reference;
        }

        String reference() {
            return reference;
        }
    }

    static final class CarteBancaire extends ModePaiement {
        CarteBancaire(String reference) {
            super(reference);
        }
    }

    non-sealed static class Especes extends ModePaiement {
        Especes(String reference) {
            super(reference);
        }
    }

    static class EspecesEnDevise extends Especes {
        private final double montant;
        private final String devise;
        private final double tauxDeChange;

        EspecesEnDevise(String reference, double montant, String devise, double tauxDeChange) {
            super(reference);
            this.montant = montant;
            this.devise = devise;
            this.tauxDeChange = tauxDeChange;
        }

        String devise() {
            return devise;
        }

        double convertirEnEuros() {
            return montant * tauxDeChange;
        }
    }
}
