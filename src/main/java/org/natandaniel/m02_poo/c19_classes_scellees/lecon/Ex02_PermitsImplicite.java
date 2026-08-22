package org.natandaniel.m02_poo.c19_classes_scellees.lecon;

/**
 * Leçon 2/4 — la clause {@code permits} est optionnelle (JLS §8.1.6) : si elle est omise, les
 * sous-classes permises sont <b>inférées</b> — toutes les classes de la même unité de compilation
 * (le même fichier, §7.3) qui déclarent {@code ModePaiement} comme superclasse directe. C'est
 * l'usage le plus courant : nul besoin de répéter des noms déjà visibles juste en dessous.
 */
class Ex02_PermitsImplicite {

    sealed static class ModePaiement {
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

    static final class VirementBancaire extends ModePaiement {
        VirementBancaire(String reference) {
            super(reference);
        }
    }

    // Une classe du même fichier qui n'étend PAS ModePaiement ne fait pas partie de la liste
    // inférée — seules celles qui la déclarent explicitement comme superclasse directe comptent
    // (JLS §8.1.6 : "the permitted direct subclasses are inferred as the classes in the same
    // compilation unit that specify C as their direct superclass").
    static class Recu {
        private final String contenu;

        Recu(String contenu) {
            this.contenu = contenu;
        }

        String contenu() {
            return contenu;
        }
    }

    public static void main(String[] args) {
        ModePaiement[] paiements = {
                new CarteBancaire("PAY-004"),
                new VirementBancaire("PAY-005")
        };

        System.out.println("=== Sans permits explicite, les sous-classes sont inférées du "
                + "même fichier ===");
        for (ModePaiement paiement : paiements) {
            System.out.println(
                    paiement.getClass().getSimpleName() + " (" + paiement.reference() + ")");
        }
        System.out.println("Recu n'étend pas ModePaiement : absente de la liste inférée, même "
                + "présente dans le même fichier.");
    }
}
