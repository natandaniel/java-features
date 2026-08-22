package org.natandaniel.m02_poo.c12_final_classe_methode.solutions;

/** Solution de référence — remiseApplicable est final : la règle est identique pour toute sous-classe. */
class Exo01_InvariantProtegeParFinal {

    static class Commande {
        double fraisLivraison() {
            return 4.90;
        }

        final double remiseApplicable(double montantTotal) {
            return Math.min(montantTotal * 0.20, 50.0);
        }
    }

    static class CommandeVip extends Commande {
        @Override
        double fraisLivraison() {
            return 0.0;
        }
    }

    static double montantNetApresRemise(Commande commande, double montantTotal) {
        return montantTotal - commande.remiseApplicable(montantTotal);
    }
}
