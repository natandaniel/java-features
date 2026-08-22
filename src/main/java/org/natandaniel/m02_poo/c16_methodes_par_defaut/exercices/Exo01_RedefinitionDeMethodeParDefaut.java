package org.natandaniel.m02_poo.c16_methodes_par_defaut.exercices;

/**
 * Exercice — {@code PaiementInternational.fraisTraitement} est à écrire : redéfinit le corps par
 * défaut ({@code 0.0}) pour retourner {@code montant * TAUX_FRAIS}.
 */
class Exo01_RedefinitionDeMethodeParDefaut {

    interface MoyenDePaiement {
        boolean payer(double montant);

        default double fraisTraitement(double montant) {
            return 0.0;
        }
    }

    static class PaiementInternational implements MoyenDePaiement {
        private static final double TAUX_FRAIS = 0.02;

        @Override
        public boolean payer(double montant) {
            return true;
        }

        @Override
        public double fraisTraitement(double montant) {
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
