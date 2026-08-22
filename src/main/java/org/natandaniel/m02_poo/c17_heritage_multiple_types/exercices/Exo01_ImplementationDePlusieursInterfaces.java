package org.natandaniel.m02_poo.c17_heritage_multiple_types.exercices;

/**
 * Exercice — {@code Avoir.rembourser} et {@code Avoir.genererFacture} sont à écrire : {@code
 * Avoir} implémente {@code Remboursable} et {@code Facturable} à la fois, chaque méthode
 * abstraite héritée de l'une ou l'autre doit être fournie. {@code rembourser} diminue le solde du
 * montant versé ; {@code genererFacture} renvoie {@code "Avoir de " + solde + " €"}, reflétant le
 * solde courant.
 */
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
            throw new UnsupportedOperationException("À implémenter");
        }

        @Override
        public String genererFacture() {
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
