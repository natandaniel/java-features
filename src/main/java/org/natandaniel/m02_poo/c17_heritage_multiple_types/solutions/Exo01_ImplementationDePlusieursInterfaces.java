package org.natandaniel.m02_poo.c17_heritage_multiple_types.solutions;

/** Solution de référence — le remboursement diminue le solde, la facture le reflète. */
class Exo01_ImplementationDePlusieursInterfaces {

    interface Remboursable {
        double solde();

        void rembourser(double montant);
    }

    interface Facturable {
        String genererFacture();
    }

    static class Avoir implements Remboursable, Facturable {
        private double solde;

        Avoir(double solde) {
            this.solde = solde;
        }

        @Override
        public double solde() {
            return solde;
        }

        @Override
        public void rembourser(double montant) {
            solde -= montant;
        }

        @Override
        public String genererFacture() {
            return "Avoir de " + solde + " €";
        }
    }
}
