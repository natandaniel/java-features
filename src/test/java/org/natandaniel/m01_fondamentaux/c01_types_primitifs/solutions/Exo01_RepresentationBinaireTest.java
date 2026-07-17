package org.natandaniel.m01_fondamentaux.c01_types_primitifs.solutions;

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

    @Test
    void compterBitsAUn_casConnus() {
        assertEquals(0, Exo01_RepresentationBinaire.compterBitsAUn(0));
        assertEquals(4, Exo01_RepresentationBinaire.compterBitsAUn(15));
        assertEquals(8, Exo01_RepresentationBinaire.compterBitsAUn(255));
        assertEquals(32, Exo01_RepresentationBinaire.compterBitsAUn(-1));
    }

    @Test
    void compterBitsAUn_coherentAvecIntegerBitCount() {
        for (int n : new int[]{1, 42, 1024, Integer.MAX_VALUE, Integer.MIN_VALUE, -42}) {
            assertEquals(Integer.bitCount(n), Exo01_RepresentationBinaire.compterBitsAUn(n));
        }
    }
}
