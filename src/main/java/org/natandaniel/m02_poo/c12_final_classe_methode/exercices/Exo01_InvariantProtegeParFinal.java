package org.natandaniel.m02_poo.c12_final_classe_methode.exercices;

/**
 * Exercice — une méthode {@code final} applique la même règle quelle que soit la sous-classe
 * (JLS §8.4.3.3 : impossible de la redéfinir pour la contourner). {@code CommandeVip} redéfinit
 * {@code fraisLivraison} (méthode ordinaire) mais ne peut pas toucher à {@code remiseApplicable}.
 */
class Exo01_InvariantProtegeParFinal {

    static class Commande {
        double fraisLivraison() {
            return 4.90;
        }

        // Règle métier : jamais plus de 20% de remise (plafonnée à 50), quelle que soit la
        // sous-classe.
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

    /**
     * Renvoie le montant net (montantTotal - remise) pour la commande donnée, en s'appuyant sur
     * la règle de remise de {@code Commande} — la même pour {@code Commande} et
     * {@code CommandeVip}.
     */
    static double montantNetApresRemise(Commande commande, double montantTotal) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
