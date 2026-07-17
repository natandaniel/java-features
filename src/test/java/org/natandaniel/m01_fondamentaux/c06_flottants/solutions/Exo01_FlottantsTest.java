package org.natandaniel.m01_fondamentaux.c06_flottants.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo01_FlottantsTest {

    @Test
    void presqueEgaux() {
        assertTrue(Exo01_Flottants.presqueEgaux(0.1 + 0.2, 0.3, 1e-9));
        assertFalse(Exo01_Flottants.presqueEgaux(0.1 + 0.2, 0.3, 0.0));
        assertTrue(Exo01_Flottants.presqueEgaux(5.0, 5.0, 0.0));
    }

    @Test
    void estNaN() {
        assertTrue(Exo01_Flottants.estNaN(0.0 / 0.0));
        assertTrue(Exo01_Flottants.estNaN(Double.NaN));
        assertFalse(Exo01_Flottants.estNaN(3.14));
        assertFalse(Exo01_Flottants.estNaN(1.0 / 0.0));
    }

    @Test
    void classifier() {
        assertEquals("NaN", Exo01_Flottants.classifier(0.0 / 0.0));
        assertEquals("INFINI", Exo01_Flottants.classifier(1.0 / 0.0));
        assertEquals("INFINI", Exo01_Flottants.classifier(-1.0 / 0.0));
        assertEquals("FINI", Exo01_Flottants.classifier(42.0));
    }
}
