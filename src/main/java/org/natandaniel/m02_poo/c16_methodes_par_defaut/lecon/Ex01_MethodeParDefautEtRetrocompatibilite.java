package org.natandaniel.m02_poo.c16_methodes_par_defaut.lecon;

/**
 * Leçon 1/4 — méthode {@code default} d'interface (JLS §9.4, Java 8) : une méthode d'instance
 * déclarée dans une interface avec un corps, fournissant une implémentation de repli pour toute
 * classe qui implémente l'interface sans la redéfinir. Motivation historique : faire évoluer une
 * interface déjà publiée (ex. {@code MoyenDePaiement}) sans casser ses implémentations
 * existantes — sans {@code default}, ajouter une méthode à une interface obligerait à modifier
 * chaque classe qui l'implémente.
 */
class Ex01_MethodeParDefautEtRetrocompatibilite {

    interface MoyenDePaiement {
        boolean payer(double montant);

        // @since Java 8 — méthode "default" (JLS §9.4) : "Its body is always represented by a
        // block, which provides a default implementation for any class that implements the
        // interface without overriding the method." Ajoutée après coup à MoyenDePaiement, elle
        // ne casse aucune classe existante qui l'implémente déjà (ex. PaiementCarte, c15).
        default double fraisTraitement(double montant) {
            return 0.0;
        }
    }

    static class PaiementCarte implements MoyenDePaiement {
        private final String numeroMasque;

        PaiementCarte(String numeroMasque) {
            this.numeroMasque = numeroMasque;
        }

        @Override
        public boolean payer(double montant) {
            System.out.println("Carte " + numeroMasque + " débitée de " + montant + " €");
            return true;
        }

        // Ne redéfinit pas fraisTraitement : hérite du corps par défaut (retourne 0.0).
    }

    public static void main(String[] args) {
        MoyenDePaiement moyen = new PaiementCarte("**** 4242");

        System.out.println("=== Une classe qui n'implémente pas fraisTraitement hérite du "
                + "corps par défaut ===");
        System.out.println("Frais de traitement : " + moyen.fraisTraitement(100.0) + " € "
                + "(valeur par défaut, PaiementCarte ne l'a jamais écrite elle-même)");
    }
}
