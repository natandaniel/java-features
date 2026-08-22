package org.natandaniel.m02_poo.c12_final_classe_methode.lecon;

/**
 * Leçon 2/4 — méthode {@code final} (JLS §8.4.3.3) : une méthode déclarée {@code final} ne peut
 * être ni redéfinie ni masquée par aucune sous-classe, même si la classe elle-même reste
 * extensible. {@code CompteBancaire} peut être étendue, mais sa règle de sécurité ne peut pas
 * être affaiblie par une sous-classe.
 */
class Ex02_MethodeFinal {

    static class CompteBancaire {
        private long soldeCentimes;

        CompteBancaire(long soldeInitialCentimes) {
            this.soldeCentimes = soldeInitialCentimes;
        }

        long solde() {
            return soldeCentimes;
        }

        // Invariant de sécurité : jamais de retrait qui mettrait le solde en négatif. Déclarée
        // final pour qu'aucune sous-classe ne puisse relâcher cette règle (JLS §8.4.3.3).
        final boolean estRetraitValide(long montantCentimes) {
            return montantCentimes > 0 && montantCentimes <= soldeCentimes;
        }

        void retirer(long montantCentimes) {
            if (!estRetraitValide(montantCentimes)) {
                throw new IllegalStateException("retrait refusé : " + montantCentimes);
            }
            soldeCentimes -= montantCentimes;
        }
    }

    static class CompteBancairePremium extends CompteBancaire {
        CompteBancairePremium(long soldeInitialCentimes) {
            super(soldeInitialCentimes);
        }

        // Autorisé : cette méthode n'est pas final, la redéfinir est normal (c07_polymorphisme).
        @Override
        long solde() {
            System.out.println("[premium] consultation du solde");
            return super.solde();
        }

        // Ne compile pas (JLS §8.4.3.3) : "It is a compile-time error to attempt to override or
        // hide a final method."
        //
        // @Override
        // boolean estRetraitValide(long montantCentimes) {
        //     return true; // contournerait l'invariant de sécurité
        // }
    }

    public static void main(String[] args) {
        CompteBancairePremium compte = new CompteBancairePremium(10_000);

        System.out.println("=== estRetraitValide reste identique dans la sous-classe ===");
        compte.retirer(3_000);
        System.out.println("solde après retrait valide : " + compte.solde());

        try {
            compte.retirer(50_000);
        } catch (IllegalStateException e) {
            System.out.println("retrait refusé comme attendu : " + e.getMessage());
        }
    }
}
