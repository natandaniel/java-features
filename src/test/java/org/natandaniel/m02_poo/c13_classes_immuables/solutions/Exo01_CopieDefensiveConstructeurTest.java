package org.natandaniel.m02_poo.c13_classes_immuables.solutions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class Exo01_CopieDefensiveConstructeurTest {

    @Test
    void creerInventaireProtege_mutationDeLaSourceApresConstruction_sansEffet() {
        int[] source = {10, 20, 30};
        Exo01_CopieDefensiveConstructeur.Inventaire inventaire =
                Exo01_CopieDefensiveConstructeur.creerInventaireProtege(source);

        source[0] = 999;

        assertArrayEquals(new int[] {10, 20, 30}, inventaire.quantites());
    }

    @Test
    void creerInventaireProtege_etatInitial_correctementCopie() {
        int[] source = {5, 6, 7};
        Exo01_CopieDefensiveConstructeur.Inventaire inventaire =
                Exo01_CopieDefensiveConstructeur.creerInventaireProtege(source);

        assertArrayEquals(new int[] {5, 6, 7}, inventaire.quantites());
    }
}
