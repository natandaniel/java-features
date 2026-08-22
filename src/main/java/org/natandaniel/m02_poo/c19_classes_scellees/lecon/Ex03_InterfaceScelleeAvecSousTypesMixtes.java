package org.natandaniel.m02_poo.c19_classes_scellees.lecon;

/**
 * Leçon 3/4 — une interface aussi peut être scellée (JLS §9.1.1.4/§9.1.4), avec les mêmes règles
 * qu'une classe. Sa clause {@code permits} peut mélanger classes ET interfaces parmi les
 * sous-types directs autorisés : {@code Paiement} scelle à la fois une classe
 * ({@code PaiementImmediat}) et une interface ({@code PaiementDiffere}). Une interface ne peut
 * jamais être {@code final} (§9.1) — {@code PaiementDiffere} doit donc choisir entre
 * {@code sealed} et {@code non-sealed}, jamais rester implicite non plus.
 */
class Ex03_InterfaceScelleeAvecSousTypesMixtes {

    sealed interface Paiement permits PaiementImmediat, PaiementDiffere {
        String reference();
    }

    sealed interface PaiementDiffere extends Paiement permits VirementAJours {
        int delaiEnJours();
    }

    static final class PaiementImmediat implements Paiement {
        private final String reference;

        PaiementImmediat(String reference) {
            this.reference = reference;
        }

        @Override
        public String reference() {
            return reference;
        }
    }

    static final class VirementAJours implements PaiementDiffere {
        private final String reference;
        private final int delaiEnJours;

        VirementAJours(String reference, int delaiEnJours) {
            this.reference = reference;
            this.delaiEnJours = delaiEnJours;
        }

        @Override
        public String reference() {
            return reference;
        }

        @Override
        public int delaiEnJours() {
            return delaiEnJours;
        }
    }

    public static void main(String[] args) {
        Paiement immediat = new PaiementImmediat("PAY-101");
        Paiement differe = new VirementAJours("PAY-102", 30);

        System.out.println("=== Paiement (interface scellée) mélange une classe et une "
                + "interface dans sa liste permits ===");
        System.out.println(
                immediat.getClass().getSimpleName() + " (" + immediat.reference() + ")");
        System.out.println(differe.getClass().getSimpleName() + " (" + differe.reference() + ", "
                + ((PaiementDiffere) differe).delaiEnJours() + " jours)");
    }
}
