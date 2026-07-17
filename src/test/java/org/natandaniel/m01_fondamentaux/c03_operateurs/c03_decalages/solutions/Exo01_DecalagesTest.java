package org.natandaniel.m01_fondamentaux.c03_operateurs.c03_decalages.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo01_DecalagesTest {

    @Test
    void multiplier() {
        assertEquals(28, Exo01_Decalages.multiplierParPuissanceDe2(7, 2));
        assertEquals(7, Exo01_Decalages.multiplierParPuissanceDe2(7, 0));
    }

    @Test
    void diviser() {
        assertEquals(7, Exo01_Decalages.diviserParPuissanceDe2(56, 3));
        assertEquals(-2, Exo01_Decalages.diviserParPuissanceDe2(-8, 2));
    }

    @Test
    void estPuissanceDe2() {
        assertTrue(Exo01_Decalages.estPuissanceDe2(1));
        assertTrue(Exo01_Decalages.estPuissanceDe2(16));
        assertFalse(Exo01_Decalages.estPuissanceDe2(12));
        assertFalse(Exo01_Decalages.estPuissanceDe2(0));
        assertFalse(Exo01_Decalages.estPuissanceDe2(-8));
    }

    @Test
    void extraireOctet() {
        int v = 0x12345678;
        assertEquals(0x78, Exo01_Decalages.extraireOctet(v, 0));
        assertEquals(0x56, Exo01_Decalages.extraireOctet(v, 1));
        assertEquals(0x34, Exo01_Decalages.extraireOctet(v, 2));
        assertEquals(0x12, Exo01_Decalages.extraireOctet(v, 3));
        assertEquals(0xFF, Exo01_Decalages.extraireOctet(-1, 0)); // pas d'extension de signe
    }
}
