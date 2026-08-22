package org.natandaniel.m02_poo.c19_classes_scellees.solutions;

/** Solution de référence — chaque sous-classe scellée masque sa donnée sensible à sa façon. */
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
            return "**** **** **** " + numero.substring(numero.length() - 4);
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
            int nbCaracteresMasques = numeroCompte.length() - 4;
            return "•".repeat(nbCaracteresMasques) + numeroCompte.substring(nbCaracteresMasques);
        }
    }

    static final class Especes extends ModePaiement {
        Especes(String reference) {
            super(reference);
        }

        @Override
        String masquer() {
            return "Espèces";
        }
    }
}
