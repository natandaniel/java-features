package org.natandaniel.m02_poo.c16_methodes_par_defaut.solutions;

/** Solution de référence — 2 % du montant en frais de traitement. */
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
            return montant * TAUX_FRAIS;
        }
    }
}
