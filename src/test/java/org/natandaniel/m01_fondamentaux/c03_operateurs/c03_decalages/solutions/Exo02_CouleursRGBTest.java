package org.natandaniel.m01_fondamentaux.c03_operateurs.c03_decalages.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo02_CouleursRGBTest {

    @Test
    void empaqueter() {
        assertEquals(0xFFECD1, Exo02_CouleursRGB.empaqueter(0xFF, 0xEC, 0xD1));
        assertEquals(0x000000, Exo02_CouleursRGB.empaqueter(0, 0, 0));
        assertEquals(0xFFFFFF, Exo02_CouleursRGB.empaqueter(255, 255, 255));
    }

    @Test
    void decomposer() {
        int rgb = 0xFFECD1;
        assertEquals(0xFF, Exo02_CouleursRGB.rouge(rgb));
        assertEquals(0xEC, Exo02_CouleursRGB.vert(rgb));
        assertEquals(0xD1, Exo02_CouleursRGB.bleu(rgb));
    }

    @Test
    void allerRetour() {
        int rgb = Exo02_CouleursRGB.empaqueter(12, 200, 7);
        assertEquals(12, Exo02_CouleursRGB.rouge(rgb));
        assertEquals(200, Exo02_CouleursRGB.vert(rgb));
        assertEquals(7, Exo02_CouleursRGB.bleu(rgb));
    }
}
