package org.natandaniel.m02_poo.c05_encapsulation.lecon;

/**
 * Leçon 2/3 — invariant de classe : l'encapsulation permet de le GARANTIR.
 */
class Ex02_InvariantsEtValidation {

    static class CompteBancaire {
        private double solde;

        CompteBancaire(double soldeInitial) {
            if (soldeInitial < 0) {
                throw new IllegalArgumentException("Solde initial négatif refusé");
            }
            this.solde = soldeInitial;
        }

        // En passant par cette méthode (au lieu de manipuler solde directement), l'invariant
        // "solde jamais négatif" est garanti PAR la classe elle-même, à chaque appel.
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

    public static void main(String[] args) {
        System.out.println("=== L'invariant 'solde jamais négatif' est protégé ===");
        CompteBancaire compte = new CompteBancaire(100.0);
        compte.retirer(30.0);
        System.out.println("Après retrait de 30 : solde = " + compte.getSolde());

        System.out.println("\n=== retirer() refuse tout ce qui casserait l'invariant ===");
        try {
            compte.retirer(1000.0);
        } catch (IllegalStateException e) {
            System.out.println("retirer(1000) refusé : " + e.getMessage());
        }

        System.out.println("\n=== La validation se fait au même endroit qu'à la construction ===");
        try {
            new CompteBancaire(-50.0);
        } catch (IllegalArgumentException e) {
            System.out.println("new CompteBancaire(-50) refusé : " + e.getMessage());
        }
    }
}
