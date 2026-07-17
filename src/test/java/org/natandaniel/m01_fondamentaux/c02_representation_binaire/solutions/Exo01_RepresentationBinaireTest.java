package org.natandaniel.m01_fondamentaux.c02_representation_binaire.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_RepresentationBinaireTest {

    @Test
    void valeurNonSignee_huitBitsAUn_donne255() {
        assertEquals(255, Exo01_RepresentationBinaire.valeurNonSignee(new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    void valeurNonSignee_motifQuelconque() {
        assertEquals(10, Exo01_RepresentationBinaire.valeurNonSignee(new int[]{0, 0, 0, 0, 1, 0, 1, 0}));
        assertEquals(0, Exo01_RepresentationBinaire.valeurNonSignee(new int[]{0, 0, 0, 0, 0, 0, 0, 0}));
        assertEquals(128, Exo01_RepresentationBinaire.valeurNonSignee(new int[]{1, 0, 0, 0, 0, 0, 0, 0}));
    }
}
