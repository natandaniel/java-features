package org.natandaniel.m02_poo.c15_interfaces_classes_abstraites.lecon;

/**
 * Leçon 2/4 — champ constant d'interface (JLS §9.3) : implicitement {@code public static final},
 * et obligatoirement initialisé à la déclaration. Utile pour une constante partagée par toutes
 * les implémentations du contrat.
 */
class Ex02_ChampsConstantsDInterface {

    interface MoyenDePaiement {
        // Implicitement "public static final" (JLS §9.3) — équivalent à écrire
        // "public static final double FRAIS_MINIMUM = 0.0;"
        double FRAIS_MINIMUM = 0.0;

        boolean payer(double montant);
    }

    static class PaiementVirement implements MoyenDePaiement {
        @Override
        public boolean payer(double montant) {
            if (montant < FRAIS_MINIMUM) {
                throw new IllegalArgumentException("montant invalide");
            }
            System.out.println("Virement de " + montant + " € initié");
            return true;
        }
    }

    // Ne compile pas (JLS §9.3.1) : "Every declarator in a field declaration of an interface
    // must have a variable initializer, or a compile-time error occurs."
    //
    // interface Invalide {
    //     double FRAIS_MINIMUM; // error: missing initializer
    // }

    public static void main(String[] args) {
        System.out.println("=== Champ d'interface : implicitement public static final ===");
        System.out.println("MoyenDePaiement.FRAIS_MINIMUM = " + MoyenDePaiement.FRAIS_MINIMUM);

        MoyenDePaiement moyen = new PaiementVirement();
        moyen.payer(120.0);

        System.out.println("Accessible sans instance, via le nom de l'interface — comme");
        System.out.println("n'importe quel champ static final : impossible à réassigner.");
    }
}
