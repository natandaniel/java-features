package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_SommeTableauTest {

    @Test
    void somme_tableauNonVide() {
        assertEquals(30, Exo01_SommeTableau.somme(new int[] {12, 9, 4, 5}));
    }

    @Test
    void somme_tableauVide() {
        assertEquals(0, Exo01_SommeTableau.somme(new int[0]));
    }

    @Test
    void indexDuMaximum_casGeneral() {
        assertEquals(2, Exo01_SommeTableau.indexDuMaximum(new int[] {12, 9, 18, 5}));
    }

    @Test
    void indexDuMaximum_premierEnCasDegalite() {
        assertEquals(1, Exo01_SommeTableau.indexDuMaximum(new int[] {5, 18, 18, 3}));
    }
}
