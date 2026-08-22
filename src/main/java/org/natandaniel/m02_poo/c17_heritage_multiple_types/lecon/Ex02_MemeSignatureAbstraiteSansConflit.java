package org.natandaniel.m02_poo.c17_heritage_multiple_types.lecon;

/**
 * Leçon 2/4 — deux interfaces indépendantes (aucun lien de sous-typage entre elles) peuvent
 * déclarer chacune une méthode abstraite de même signature, par coïncidence. JLS §8.4.8, Example
 * 8.4.8-1 (interfaces {@code I1}/{@code I2}, classe abstraite {@code Test}, repris ici) : une
 * classe qui implémente les deux hérite des deux méthodes sans erreur — elle doit rester
 * abstraite, ou fournir une seule implémentation concrète qui satisfait les deux contrats à la
 * fois.
 */
class Ex02_MemeSignatureAbstraiteSansConflit {

    interface Remboursable {
        String reference();
    }

    interface Tracable {
        String reference();
    }

    // JLS §8.4.8, Example 8.4.8-1 : "Test inherits both foo methods; obviously it must be
    // declared abstract, or else override both abstract foo methods with a concrete method."
    // Remboursable.reference() et Tracable.reference() ne sont liées par aucun héritage (ni
    // l'une ni l'autre n'"override" l'autre) : pas de conflit, juste deux méthodes abstraites
    // identiques à satisfaire.
    abstract static class OperationAbstraite implements Remboursable, Tracable {
    }

    static class Avoir extends OperationAbstraite {
        private final String identifiant;

        Avoir(String identifiant) {
            this.identifiant = identifiant;
        }

        // Une seule implémentation suffit à satisfaire les deux interfaces à la fois.
        @Override
        public String reference() {
            return "AVOIR-" + identifiant;
        }
    }

    public static void main(String[] args) {
        Avoir avoir = new Avoir("2026-001");

        System.out.println("=== Deux méthodes abstraites de même signature, sans lien entre "
                + "elles, ne sont pas un conflit ===");
        System.out.println("Référence (via Remboursable) : " + ((Remboursable) avoir).reference());
        System.out.println("Référence (via Tracable) : " + ((Tracable) avoir).reference());
    }
}
