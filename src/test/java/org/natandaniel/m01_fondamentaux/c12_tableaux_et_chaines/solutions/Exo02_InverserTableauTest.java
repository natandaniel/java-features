package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.solutions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class Exo02_InverserTableauTest {

    @Test
    void inverser_tableauNonVide() {
        assertArrayEquals(new int[] {3, 2, 1}, Exo02_InverserTableau.inverser(new int[] {1, 2, 3}));
    }

    @Test
    void inverser_tableauVide() {
        assertArrayEquals(new int[0], Exo02_InverserTableau.inverser(new int[0]));
    }

    @Test
    void inverser_neModifiePasLoriginal() {
        int[] original = {1, 2, 3};
        Exo02_InverserTableau.inverser(original);
        assertArrayEquals(new int[] {1, 2, 3}, original);
    }
}
