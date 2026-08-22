package org.natandaniel.m02_poo.c17_heritage_multiple_types.lecon;

/**
 * Leçon 1/4 — une classe peut implémenter plusieurs interfaces à la fois (JLS §8.1.5, la clause
 * {@code implements} accepte une liste) : Java interdit l'héritage multiple de classes (une
 * seule superclasse directe, §8.1.4) mais pas l'implémentation multiple d'interfaces. La classe
 * doit fournir un corps pour chaque méthode abstraite héritée de chacune.
 */
class Ex01_ImplementationDePlusieursInterfaces {

    interface Remboursable {
        void rembourser(double montant);
    }

    interface Facturable {
        String genererFacture();
    }

    // Avoir hérite d'une méthode abstraite de chaque interface : les deux doivent être fournies,
    // exactement comme pour une seule interface (c15) — implements liste simplement les deux.
    static class Avoir implements Remboursable, Facturable {
        private double solde;

        Avoir(double solde) {
            this.solde = solde;
        }

        @Override
        public void rembourser(double montant) {
            solde -= montant;
            System.out.println("Remboursement de " + montant + " €, solde restant : " + solde + " €");
        }

        @Override
        public String genererFacture() {
            return "Avoir de " + solde + " €";
        }
    }

    public static void main(String[] args) {
        Avoir avoir = new Avoir(100.0);

        System.out.println("=== Une classe implémente deux interfaces à la fois : elle fournit "
                + "les méthodes abstraites des deux ===");
        avoir.rembourser(30.0);
        System.out.println("Facture : " + avoir.genererFacture());
    }
}
