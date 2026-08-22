package org.natandaniel.m02_poo.c19_classes_scellees.lecon;

/**
 * Leçon 4/4 — dès qu'une classe a une superclasse (ou superinterface) sealed, elle doit
 * explicitement choisir {@code final}, {@code sealed} ou {@code non-sealed} (JLS §8.1.1.2) : rien
 * n'est implicite. Une sous-classe {@code non-sealed} rouvre totalement l'extensibilité en
 * dessous d'elle — c'est le seul moyen de sortir d'une hiérarchie scellée sans modifier la
 * classe {@code sealed} elle-même.
 */
class Ex04_NonSealedRouvreLExtensibilite {

    sealed static class ModePaiement permits CarteBancaire, Especes {
        private final String reference;

        ModePaiement(String reference) {
            this.reference = reference;
        }

        String reference() {
            return reference;
        }
    }

    static final class CarteBancaire extends ModePaiement {
        CarteBancaire(String reference) {
            super(reference);
        }
    }

    // non-sealed : Especes redevient librement extensible, alors que ModePaiement elle-même
    // reste fermée. Un code tiers peut donc ajouter ses propres variantes d'espèces (ex. devise
    // étrangère) sans jamais pouvoir étendre CarteBancaire ou ModePaiement directement.
    non-sealed static class Especes extends ModePaiement {
        Especes(String reference) {
            super(reference);
        }
    }

    // Autorisé précisément parce qu'Especes est non-sealed — aucune clause permits à mettre à
    // jour sur ModePaiement, aucune restriction supplémentaire à satisfaire.
    static class EspecesEnDevise extends Especes {
        private final String devise;

        EspecesEnDevise(String reference, String devise) {
            super(reference);
            this.devise = devise;
        }

        String devise() {
            return devise;
        }
    }

    // Ne compile pas (JLS §8.1.1.2) : Especes a une superclasse sealed (ModePaiement) et n'est
    // déclarée ni final, ni sealed, ni non-sealed.
    //
    // static class EspecesSansModificateur extends ModePaiement {
    //     // error: class EspecesSansModificateur must be declared final or sealed or
    //     // non-sealed since it extends the sealed class ModePaiement
    // }

    public static void main(String[] args) {
        ModePaiement paiement = new EspecesEnDevise("PAY-201", "USD");

        System.out.println("=== Especes est non-sealed : librement extensible, malgré une "
                + "superclasse ModePaiement scellée ===");
        System.out.println(paiement.getClass().getSimpleName() + " (" + paiement.reference()
                + ", " + ((EspecesEnDevise) paiement).devise() + ")");
    }
}
