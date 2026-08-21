package org.natandaniel.m02_poo.c05_encapsulation.solutions;

/** Solution de référence — CompteBancaire : garantir l'invariant solde >= 0. */
class Exo01_CompteBancaire {

    static class CompteBancaire {
        private double solde;

        CompteBancaire(double soldeInitial) {
            this.solde = soldeInitial;
        }

        void deposer(double montant) {
            if (montant < 0) {
                throw new IllegalArgumentException("Montant négatif refusé");
            }
            solde += montant;
        }

        void retirer(double montant) {
            if (montant < 0) {
                throw new IllegalArgumentException("Montant négatif refusé");
            }
            if (montant > solde) {
                throw new IllegalStateException("Solde insuffisant");
            }
            solde -= montant;
        }

        double getSolde() {
            return solde;
        }
    }
}
