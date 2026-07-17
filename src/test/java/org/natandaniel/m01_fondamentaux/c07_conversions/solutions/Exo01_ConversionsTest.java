package org.natandaniel.m01_fondamentaux.c07_conversions.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo01_ConversionsTest {

    @Test
    void tronquerEnByte() {
        assertEquals((byte) 44, Exo01_Conversions.tronquerEnByte(300));
        assertEquals((byte) 0, Exo01_Conversions.tronquerEnByte(256));
        assertEquals((byte) 127, Exo01_Conversions.tronquerEnByte(127));
        assertEquals((byte) -1, Exo01_Conversions.tronquerEnByte(255));
    }

    @Test
    void partieEntiere_tronqueVersZero() {
        assertEquals(3, Exo01_Conversions.partieEntiere(3.99999));
        assertEquals(-3, Exo01_Conversions.partieEntiere(-3.7));
        assertEquals(0, Exo01_Conversions.partieEntiere(0.5));
    }

    @Test
    void tientDansByte() {
        assertTrue(Exo01_Conversions.tientDansByte(127));
        assertTrue(Exo01_Conversions.tientDansByte(-128));
        assertFalse(Exo01_Conversions.tientDansByte(128));
        assertFalse(Exo01_Conversions.tientDansByte(300));
    }
}
