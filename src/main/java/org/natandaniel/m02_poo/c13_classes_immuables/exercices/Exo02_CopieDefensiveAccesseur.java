package org.natandaniel.m02_poo.c13_classes_immuables.exercices;

/**
 * Exercice — copie défensive en sortie (Effective Java, Item 50) : {@code Releve} protège déjà
 * son état à la construction (copie faite dans le constructeur), mais expose un accesseur naïf
 * ({@code soldesJournaliersBruts}) qui renvoie la référence interne telle quelle.
 */
class Exo02_CopieDefensiveAccesseur {

    static class Releve {
        private final long[] soldesJournaliers;

        Releve(long[] soldesJournaliers) {
            this.soldesJournaliers = soldesJournaliers.clone(); // déjà protégé en entrée
        }

        long[] soldesJournaliersBruts() {
            return soldesJournaliers; // naïf : aucune copie en sortie
        }
    }

    /**
     * Renvoie les soldes journaliers de {@code releve} sous une forme que l'appelant peut
     * librement modifier sans jamais atteindre l'état interne de {@code releve}.
     */
    static long[] soldesJournaliersProteges(Releve releve) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
