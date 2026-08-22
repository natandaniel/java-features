package org.natandaniel.m02_poo.c13_classes_immuables.solutions;

/** Solution de référence — clone le tableau interne avant de le renvoyer à l'appelant. */
class Exo02_CopieDefensiveAccesseur {

    static class Releve {
        private final long[] soldesJournaliers;

        Releve(long[] soldesJournaliers) {
            this.soldesJournaliers = soldesJournaliers.clone();
        }

        long[] soldesJournaliersBruts() {
            return soldesJournaliers;
        }
    }

    static long[] soldesJournaliersProteges(Releve releve) {
        return releve.soldesJournaliersBruts().clone();
    }
}
