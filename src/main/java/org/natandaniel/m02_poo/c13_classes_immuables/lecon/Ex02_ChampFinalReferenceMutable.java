package org.natandaniel.m02_poo.c13_classes_immuables.lecon;

import java.util.Arrays;

/**
 * Leçon 2/4 — le piège du champ final qui référence un objet mutable (JLS §4.12.4) : {@code
 * final} fixe la VARIABLE, pas l'état de l'objet pointé. Un accesseur naïf qui renvoie
 * directement le tableau interne laisse n'importe quel appelant modifier l'état de {@code Releve}
 * de l'extérieur, sans jamais passer par une méthode de la classe.
 */
class Ex02_ChampFinalReferenceMutable {

    static class Releve {
        private final long[] soldesJournaliers;

        Releve(long[] soldesJournaliers) {
            this.soldesJournaliers = soldesJournaliers; // pas de copie : le champ pointe
                                                          // directement sur le tableau reçu
        }

        // Naïf : renvoie la référence interne elle-même, pas une copie.
        long[] soldesJournaliers() {
            return soldesJournaliers;
        }
    }

    public static void main(String[] args) {
        long[] soldesOrigine = {1_000, 1_200, 900};
        Releve releve = new Releve(soldesOrigine);

        System.out.println("=== soldesJournaliers est final, mais son contenu ne l'est pas ===");
        System.out.println("avant mutation externe : " + Arrays.toString(releve.soldesJournaliers()));

        // Aucune méthode de Releve n'est appelée ici : pourtant son état interne change.
        soldesOrigine[0] = 0;
        System.out.println("après soldesOrigine[0] = 0 : " + Arrays.toString(releve.soldesJournaliers()));

        // Pire : le tableau RENVOYÉ par l'accesseur est aussi la référence interne.
        releve.soldesJournaliers()[1] = 0;
        System.out.println("après releve.soldesJournaliers()[1] = 0 : "
                + Arrays.toString(releve.soldesJournaliers()));
        System.out.println("Releve n'est pas réellement immuable : final ne protège que la");
        System.out.println("variable 'soldesJournaliers', jamais le tableau qu'elle référence.");
    }
}
