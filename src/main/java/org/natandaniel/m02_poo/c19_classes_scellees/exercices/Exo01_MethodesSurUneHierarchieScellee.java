package org.natandaniel.m02_poo.c19_classes_scellees.exercices;

/**
 * Exercice — {@code masquer()} est à écrire pour chacune des trois sous-classes de
 * {@code ModePaiement} : {@code CarteBancaire} renvoie {@code "**** **** **** "} suivi des 4
 * derniers caractères de {@code numero} ; {@code VirementBancaire} renvoie {@code "•"} répété
 * autant de fois que {@code numeroCompte} a de caractères avant ses 4 derniers, suivi de ces 4
 * derniers caractères ; {@code Especes} renvoie toujours {@code "Espèces"} (rien à masquer).
 */
class Exo01_MethodesSurUneHierarchieScellee {

    abstract sealed static class ModePaiement permits CarteBancaire, VirementBancaire, Especes {
        private final String reference;

        ModePaiement(String reference) {
            this.reference = reference;
        }

        String reference() {
            return reference;
        }

        abstract String masquer();
    }

    static final class CarteBancaire extends ModePaiement {
        private final String numero;

        CarteBancaire(String reference, String numero) {
            super(reference);
            this.numero = numero;
        }

        @Override
        String masquer() {
            throw new UnsupportedOperationException("À implémenter");
        }
    }

    static final class VirementBancaire extends ModePaiement {
        private final String numeroCompte;

        VirementBancaire(String reference, String numeroCompte) {
            super(reference);
            this.numeroCompte = numeroCompte;
        }

        @Override
        String masquer() {
            throw new UnsupportedOperationException("À implémenter");
        }
    }

    static final class Especes extends ModePaiement {
        Especes(String reference) {
            super(reference);
        }

        @Override
        String masquer() {
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
