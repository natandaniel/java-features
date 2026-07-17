package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo03_CouleursRGBTest {

    @Test
    void empaqueter() {
        assertEquals(0xFFECD1, Exo03_CouleursRGB.empaqueter(0xFF, 0xEC, 0xD1));
        assertEquals(0x000000, Exo03_CouleursRGB.empaqueter(0, 0, 0));
        assertEquals(0xFFFFFF, Exo03_CouleursRGB.empaqueter(255, 255, 255));
    }

    @Test
    void decomposer() {
        int rgb = 0xFFECD1;
        assertEquals(0xFF, Exo03_CouleursRGB.rouge(rgb));
        assertEquals(0xEC, Exo03_CouleursRGB.vert(rgb));
        assertEquals(0xD1, Exo03_CouleursRGB.bleu(rgb));
    }

    @Test
    void allerRetour() {
        int rgb = Exo03_CouleursRGB.empaqueter(12, 200, 7);
        assertEquals(12, Exo03_CouleursRGB.rouge(rgb));
        assertEquals(200, Exo03_CouleursRGB.vert(rgb));
        assertEquals(7, Exo03_CouleursRGB.bleu(rgb));
    }
}
