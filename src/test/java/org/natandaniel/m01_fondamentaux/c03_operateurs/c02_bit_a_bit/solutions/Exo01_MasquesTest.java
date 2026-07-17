package org.natandaniel.m01_fondamentaux.c03_operateurs.c02_bit_a_bit.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo01_MasquesTest {

    @Test
    void activer() {
        assertEquals(0b0001, Exo01_Masques.activer(0b0000, 0));
        assertEquals(0b0101, Exo01_Masques.activer(0b0001, 2));
        assertEquals(0b0001, Exo01_Masques.activer(0b0001, 0)); // déjà actif : inchangé
    }

    @Test
    void desactiver() {
        assertEquals(0b0000, Exo01_Masques.desactiver(0b0001, 0));
        assertEquals(0b0001, Exo01_Masques.desactiver(0b0101, 2));
        assertEquals(0b0000, Exo01_Masques.desactiver(0b0000, 3)); // déjà à 0 : inchangé
    }

    @Test
    void basculer() {
        assertEquals(0b0001, Exo01_Masques.basculer(0b0000, 0));
        assertEquals(0b0000, Exo01_Masques.basculer(0b0001, 0));
    }

    @Test
    void estActif() {
        assertTrue(Exo01_Masques.estActif(0b0100, 2));
        assertFalse(Exo01_Masques.estActif(0b0100, 1));
    }
}
