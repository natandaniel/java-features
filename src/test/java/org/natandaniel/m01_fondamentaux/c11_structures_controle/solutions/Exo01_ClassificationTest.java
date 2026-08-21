package org.natandaniel.m01_fondamentaux.c11_structures_controle.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_ClassificationTest {

    @Test
    void classerTemperature() {
        assertEquals("gel", Exo01_Classification.classerTemperature(-5.0));
        assertEquals("frais", Exo01_Classification.classerTemperature(5.0));
        assertEquals("tempere", Exo01_Classification.classerTemperature(18.0));
        assertEquals("chaud", Exo01_Classification.classerTemperature(28.0));
        assertEquals("canicule", Exo01_Classification.classerTemperature(38.0));
    }

    @Test
    void classerTemperature_bornesExclusives() {
        assertEquals("frais", Exo01_Classification.classerTemperature(0.0));
        assertEquals("tempere", Exo01_Classification.classerTemperature(15.0));
        assertEquals("chaud", Exo01_Classification.classerTemperature(25.0));
        assertEquals("canicule", Exo01_Classification.classerTemperature(35.0));
    }

    @Test
    void comparer() {
        assertEquals(-1, Exo01_Classification.comparer(1, 2));
        assertEquals(1, Exo01_Classification.comparer(2, 1));
        assertEquals(0, Exo01_Classification.comparer(5, 5));
    }
}
