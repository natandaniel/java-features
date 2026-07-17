package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo04_CompterBitsTest {

    @Test
    void casConnus() {
        assertEquals(0, Exo04_CompterBits.compterBitsAUn(0));
        assertEquals(4, Exo04_CompterBits.compterBitsAUn(15));
        assertEquals(8, Exo04_CompterBits.compterBitsAUn(255));
        assertEquals(32, Exo04_CompterBits.compterBitsAUn(-1));
    }

    @Test
    void coherentAvecIntegerBitCount() {
        for (int n : new int[]{1, 42, 1024, Integer.MAX_VALUE, Integer.MIN_VALUE, -42}) {
            assertEquals(Integer.bitCount(n), Exo04_CompterBits.compterBitsAUn(n));
        }
    }
}
