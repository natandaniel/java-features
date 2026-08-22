package org.natandaniel.m02_poo.c19_classes_scellees.lecon;

/**
 * Leçon 1/4 — une classe scellée (JLS §8.1.1.2, @since Java 17) énumère ses sous-classes directes
 * avec la clause {@code permits} (§8.1.6) : {@code ModePaiement} ne peut être étendue que par les
 * trois classes citées, aucune autre. Chaque sous-classe directe doit à son tour se déclarer
 * {@code final}, {@code sealed} ou {@code non-sealed} — Java force ce choix explicite plutôt que
 * de le laisser implicite, pour éviter d'exposer accidentellement la hiérarchie à l'extension.
 */
class Ex01_ClasseScelleeAvecPermitsExplicite {

    sealed static class ModePaiement permits CarteBancaire, VirementBancaire, Especes {
        private final String reference;

        ModePaiement(String reference) {
            this.reference = reference;
        }

        String reference() {
            return reference;
        }
    }

    // final : le cas le plus courant en pratique — un moyen de paiement de plus n'a aucune
    // raison d'exister sans toucher à ModePaiement elle-même.
    static final class CarteBancaire extends ModePaiement {
        private final String quatreDerniersChiffres;

        CarteBancaire(String reference, String quatreDerniersChiffres) {
            super(reference);
            this.quatreDerniersChiffres = quatreDerniersChiffres;
        }

        String quatreDerniersChiffres() {
            return quatreDerniersChiffres;
        }
    }

    static final class VirementBancaire extends ModePaiement {
        private final String iban;

        VirementBancaire(String reference, String iban) {
            super(reference);
            this.iban = iban;
        }

        String iban() {
            return iban;
        }
    }

    static final class Especes extends ModePaiement {
        Especes(String reference) {
            super(reference);
        }
    }

    // Ne compile pas (JLS §8.1.1.2) : une sous-classe directe d'une classe sealed doit être
    // final, sealed ou non-sealed — l'omission des trois est une erreur de compilation.
    //
    // static class Cheque extends ModePaiement {
    //     // error: class Cheque must be declared final or sealed or non-sealed since it
    //     // extends the sealed class ModePaiement
    //     Cheque(String reference) { super(reference); }
    // }

    public static void main(String[] args) {
        ModePaiement[] paiements = {
                new CarteBancaire("PAY-001", "4242"),
                new VirementBancaire("PAY-002", "FR7630006000011234567890189"),
                new Especes("PAY-003")
        };

        System.out.println("=== ModePaiement est sealed : exactement trois sous-classes, "
                + "aucune autre n'est possible ===");
        for (ModePaiement paiement : paiements) {
            System.out.println(
                    paiement.getClass().getSimpleName() + " (" + paiement.reference() + ")");
        }
    }
}
