package org.natandaniel.m01_fondamentaux.c02_litteraux.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_CaracteresTest {

    @Test
    void distanceAlphabetique() {
        assertEquals(2, Exo01_Caracteres.distanceAlphabetique('A', 'C'));
        assertEquals(25, Exo01_Caracteres.distanceAlphabetique('A', 'Z'));
        assertEquals(-2, Exo01_Caracteres.distanceAlphabetique('C', 'A'));
        assertEquals(0, Exo01_Caracteres.distanceAlphabetique('m', 'm'));
    }

    @Test
    void enMajuscule_transformeLesMinuscules() {
        assertEquals('A', Exo01_Caracteres.enMajuscule('a'));
        assertEquals('Z', Exo01_Caracteres.enMajuscule('z'));
        assertEquals('M', Exo01_Caracteres.enMajuscule('m'));
    }

    @Test
    void enMajuscule_laisseLeResteIntact() {
        assertEquals('A', Exo01_Caracteres.enMajuscule('A'));
        assertEquals('5', Exo01_Caracteres.enMajuscule('5'));
        assertEquals('!', Exo01_Caracteres.enMajuscule('!'));
    }
}
