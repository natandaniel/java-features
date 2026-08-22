package org.natandaniel.m02_poo.c15_interfaces_classes_abstraites.exercices;

/**
 * Exercice — {@code PaiementEspeces.payer} est à écrire : refuse (retourne {@code false}) si le
 * montant dépasse le plafond de l'instance, accepte (retourne {@code true}) sinon.
 */
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
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
