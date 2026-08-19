package org.natandaniel.m02_poo.c02_encapsulation.exercices;

/**
 * Exercices — CompteBancaire : garantir l'invariant solde >= 0 via l'encapsulation.
 */
class Exo01_CompteBancaire {

    static class CompteBancaire {
        private double solde;

        CompteBancaire(double soldeInitial) {
            this.solde = soldeInitial;
        }

        /** Ajoute montant au solde. Lève IllegalArgumentException si montant < 0. */
        void deposer(double montant) {
            throw new UnsupportedOperationException("À implémenter");
        }

        /**
         * Retire montant du solde. Lève IllegalArgumentException si montant < 0,
         * IllegalStateException si montant > solde.
         */
        void retirer(double montant) {
            throw new UnsupportedOperationException("À implémenter");
        }

        double getSolde() {
            return solde;
        }
    }
}
