package org.natandaniel.m02_poo.c16_methodes_par_defaut.lecon;

/**
 * Leçon 2/4 — une classe implémentante peut redéfinir une méthode {@code default} exactement
 * comme une méthode abstraite : la redéfinition prime sur le corps par défaut (liaison
 * dynamique habituelle, cf. {@code c07_polymorphisme}). Depuis cette redéfinition, l'implémentation
 * par défaut reste accessible explicitement via {@code Interface.super.methode()} (JLS §9.4.1.1) —
 * utile pour l'étendre plutôt que la remplacer intégralement.
 */
class Ex02_RedefinitionDeMethodeParDefaut {

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
            System.out.println("Paiement international de " + montant + " € accepté");
            return true;
        }

        @Override
        public double fraisTraitement(double montant) {
            // JLS §9.4.1.1 : "An overridden default method can be accessed by using a method
            // invocation expression that contains the keyword super qualified by a
            // superinterface name." Ici, la redéfinition ne se contente pas de remplacer le
            // corps par défaut : elle part de lui (0.0, inutile en pratique, mais le montre) et
            // ajoute un taux de change.
            double fraisDeBase = MoyenDePaiement.super.fraisTraitement(montant);
            return fraisDeBase + montant * TAUX_FRAIS;
        }
    }

    public static void main(String[] args) {
        MoyenDePaiement moyen = new PaiementInternational();

        System.out.println("=== La redéfinition prime sur le corps par défaut ===");
        System.out.println("Frais de traitement : " + moyen.fraisTraitement(100.0) + " € "
                + "(2 % du montant, pas 0.0 : PaiementInternational a redéfini la méthode)");
    }
}
