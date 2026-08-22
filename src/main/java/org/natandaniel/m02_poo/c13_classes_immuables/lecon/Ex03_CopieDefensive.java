package org.natandaniel.m02_poo.c13_classes_immuables.lecon;

import java.util.Arrays;

/**
 * Leçon 3/4 — la correction du piège de {@code Ex02} : copie défensive en entrée (constructeur)
 * ET en sortie (accesseur). Aucune section JLS ne décrit ce patron — c'est une pratique de
 * conception (Effective Java, Item 50 « Make defensive copies when needed »), pas une règle du
 * langage.
 */
class Ex03_CopieDefensive {

    static class ReleveImmuable {
        private final long[] soldesJournaliers;

        ReleveImmuable(long[] soldesJournaliers) {
            // Copie en entrée : l'appelant garde sa référence à 'soldesJournaliers', mais elle
            // n'a plus aucun lien avec l'état interne de cet objet une fois le constructeur passé.
            this.soldesJournaliers = soldesJournaliers.clone();
        }

        long[] soldesJournaliers() {
            // Copie en sortie : l'appelant reçoit un tableau qu'il peut librement modifier, sans
            // jamais atteindre l'état interne.
            return soldesJournaliers.clone();
        }
    }

    public static void main(String[] args) {
        long[] soldesOrigine = {1_000, 1_200, 900};
        ReleveImmuable releve = new ReleveImmuable(soldesOrigine);

        soldesOrigine[0] = 0;
        System.out.println("=== copie défensive en entrée : mutation externe sans effet ===");
        System.out.println("soldesOrigine[0] = 0, mais releve reste : "
                + Arrays.toString(releve.soldesJournaliers()));

        releve.soldesJournaliers()[1] = 0;
        System.out.println("=== copie défensive en sortie : mutation du résultat sans effet ===");
        System.out.println("après releve.soldesJournaliers()[1] = 0, releve reste : "
                + Arrays.toString(releve.soldesJournaliers()));
    }
}
