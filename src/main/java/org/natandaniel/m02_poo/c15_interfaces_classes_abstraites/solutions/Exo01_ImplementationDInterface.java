package org.natandaniel.m02_poo.c15_interfaces_classes_abstraites.solutions;

/** Solution de référence — refuse tout montant strictement supérieur au plafond. */
class Exo01_ImplementationDInterface {

    interface MoyenDePaiement {
        boolean payer(double montant);
    }

    static class PaiementEspeces implements MoyenDePaiement {
        private final double plafond;

        PaiementEspeces(double plafond) {
            this.plafond = plafond;
        }

        @Override
        public boolean payer(double montant) {
            return montant <= plafond;
        }
    }
}
