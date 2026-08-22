package org.natandaniel.m02_poo.c13_classes_immuables.solutions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class Exo02_CopieDefensiveAccesseurTest {

    @Test
    void soldesJournaliersProteges_mutationDuResultat_sansEffetSurLeReleve() {
        Exo02_CopieDefensiveAccesseur.Releve releve =
                new Exo02_CopieDefensiveAccesseur.Releve(new long[] {1_000, 1_200, 900});

        long[] soldes = Exo02_CopieDefensiveAccesseur.soldesJournaliersProteges(releve);
        soldes[0] = 0;

        assertArrayEquals(new long[] {1_000, 1_200, 900},
                Exo02_CopieDefensiveAccesseur.soldesJournaliersProteges(releve));
    }

    @Test
    void soldesJournaliersProteges_etatInitial_correspondAuReleve() {
        Exo02_CopieDefensiveAccesseur.Releve releve =
                new Exo02_CopieDefensiveAccesseur.Releve(new long[] {5, 10, 15});

        assertArrayEquals(new long[] {5, 10, 15},
                Exo02_CopieDefensiveAccesseur.soldesJournaliersProteges(releve));
    }
}
