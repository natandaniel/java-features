package org.natandaniel.m02_poo.c15_interfaces_classes_abstraites.lecon;

/**
 * Leçon 4/4 — même besoin, deux modèles. Une interface ne porte aucun état, et une classe peut en
 * implémenter plusieurs (JLS §8.1.5) ; une classe abstraite peut porter un état partagé, mais une
 * classe ne peut en étendre qu'une seule (JLS §8.1.4). Le choix dépend de ce qui doit être
 * partagé entre les implémentations.
 */
class Ex04_InterfaceVsClasseAbstraite {

    interface MoyenDePaiement {
        boolean payer(double montant);
    }

    abstract static class TraitementPaiement {
        private final double fraisFixe;

        TraitementPaiement(double fraisFixe) {
            this.fraisFixe = fraisFixe;
        }

        double fraisFixe() {
            return fraisFixe;
        }

        abstract boolean payer(double montant);
    }

    static class PaiementCarte implements MoyenDePaiement {
        @Override
        public boolean payer(double montant) {
            System.out.println("[interface] carte débitée de " + montant + " €");
            return true;
        }
    }

    static class TraitementCarte extends TraitementPaiement {
        TraitementCarte(double fraisFixe) {
            super(fraisFixe);
        }

        @Override
        boolean payer(double montant) {
            System.out.println("[classe abstraite] carte débitée de "
                    + (montant + fraisFixe()) + " € (frais fixe inclus)");
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Interface : aucun état, contrat pur ===");
        MoyenDePaiement viaInterface = new PaiementCarte();
        viaInterface.payer(100.0);

        System.out.println();
        System.out.println("=== Classe abstraite : état partagé (fraisFixe) ===");
        TraitementPaiement viaClasseAbstraite = new TraitementCarte(0.30);
        viaClasseAbstraite.payer(100.0);

        System.out.println();
        System.out.println("Une classe peut implémenter PLUSIEURS interfaces (JLS §8.1.5),");
        System.out.println("mais n'étendre qu'UNE SEULE classe, abstraite ou non (JLS §8.1.4)");
        System.out.println("— détaillé dans un concept suivant (héritage multiple de types).");
    }
}
