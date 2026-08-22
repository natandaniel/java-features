package org.natandaniel.m02_poo.c15_interfaces_classes_abstraites.lecon;

/**
 * Leçon 3/4 — classe abstraite (JLS §8.1.1.1) : une implémentation partielle, avec état
 * (contrairement à l'interface). Une méthode abstraite (JLS §8.4.3.1) a un corps réduit à un
 * point-virgule (JLS §8.4.7) ; toute sous-classe concrète doit la fournir.
 */
class Ex03_ClasseAbstraiteEtSousClasse {

    abstract static class TraitementPaiement {
        private final double montant;

        TraitementPaiement(double montant) {
            this.montant = montant;
        }

        double montant() {
            return montant;
        }

        // Corps réduit à ";" (JLS §8.4.7) : pas d'implémentation ici.
        abstract boolean verifierFonds();

        // Méthode concrète, partagée par toutes les sous-classes : s'appuie sur
        // verifierFonds() sans savoir comment chaque sous-classe la réalise.
        boolean executer() {
            if (!verifierFonds()) {
                System.out.println("Fonds insuffisants pour " + montant() + " €");
                return false;
            }
            System.out.println("Paiement de " + montant() + " € exécuté");
            return true;
        }
    }

    static class TraitementCarte extends TraitementPaiement {
        TraitementCarte(double montant) {
            super(montant);
        }

        @Override
        boolean verifierFonds() {
            return montant() <= 500.0; // plafond simplifié pour l'exemple
        }
    }

    // Ne compile pas (JLS §8.1.1.1) : une classe abstraite ne peut pas être instantiée.
    //
    // TraitementPaiement t = new TraitementPaiement(100.0);
    // error: TraitementPaiement is abstract; cannot be instantiated

    // Ne compile pas non plus (JLS §8.4.3.1) : une sous-classe non abstraite doit implémenter
    // toute méthode abstraite héritée.
    //
    // static class TraitementIncomplet extends TraitementPaiement {
    //     TraitementIncomplet(double montant) { super(montant); }
    // }
    // error: TraitementIncomplet is not abstract and does not override abstract
    // method verifierFonds() in TraitementPaiement

    public static void main(String[] args) {
        TraitementPaiement traitement = new TraitementCarte(150.0);

        System.out.println("=== Classe abstraite : état partagé + contrat partiel ===");
        traitement.executer();

        TraitementPaiement refuse = new TraitementCarte(800.0);
        refuse.executer();
    }
}
