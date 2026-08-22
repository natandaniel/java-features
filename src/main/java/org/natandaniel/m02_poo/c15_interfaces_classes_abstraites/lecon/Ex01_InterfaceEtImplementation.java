package org.natandaniel.m02_poo.c15_interfaces_classes_abstraites.lecon;

/**
 * Leçon 1/4 — déclaration d'interface (JLS §9.1) : un contrat de comportement, sans état ni
 * implémentation. Une méthode d'interface sans modificateur est implicitement {@code public
 * abstract} (JLS §9.4) — une classe qui l'implémente doit fournir un corps.
 */
class Ex01_InterfaceEtImplementation {

    interface MoyenDePaiement {
        // Implicitement "public abstract" (JLS §9.4) : "An interface method lacking a private,
        // default, or static modifier is implicitly abstract."
        boolean payer(double montant);
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
    }

    // Ne compile pas (JLS §9.1) : une interface ne peut pas être instantiée directement.
    //
    // MoyenDePaiement moyen = new MoyenDePaiement();
    // error: MoyenDePaiement is abstract; cannot be instantiated

    public static void main(String[] args) {
        MoyenDePaiement moyen = new PaiementCarte("**** 4242");

        System.out.println("=== Une interface définit un contrat, pas une implémentation ===");
        boolean succes = moyen.payer(49.90);
        System.out.println("Paiement réussi : " + succes);

        System.out.println("Type déclaré de la variable : MoyenDePaiement (l'interface).");
        System.out.println("Type réel de l'objet : " + moyen.getClass().getSimpleName() + ".");
    }
}
