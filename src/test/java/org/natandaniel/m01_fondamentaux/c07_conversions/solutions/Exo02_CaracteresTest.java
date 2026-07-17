package org.natandaniel.m01_fondamentaux.c07_conversions.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo02_CaracteresTest {

    @Test
    void distanceAlphabetique() {
        assertEquals(2, Exo02_Caracteres.distanceAlphabetique('A', 'C'));
        assertEquals(25, Exo02_Caracteres.distanceAlphabetique('A', 'Z'));
        assertEquals(-2, Exo02_Caracteres.distanceAlphabetique('C', 'A'));
        assertEquals(0, Exo02_Caracteres.distanceAlphabetique('m', 'm'));
    }

    @Test
    void enMajuscule_transformeLesMinuscules() {
        assertEquals('A', Exo02_Caracteres.enMajuscule('a'));
        assertEquals('Z', Exo02_Caracteres.enMajuscule('z'));
        assertEquals('M', Exo02_Caracteres.enMajuscule('m'));
    }

    @Test
    void enMajuscule_laisseLeResteIntact() {
        assertEquals('A', Exo02_Caracteres.enMajuscule('A'));
        assertEquals('5', Exo02_Caracteres.enMajuscule('5'));
        assertEquals('!', Exo02_Caracteres.enMajuscule('!'));
    }
}
