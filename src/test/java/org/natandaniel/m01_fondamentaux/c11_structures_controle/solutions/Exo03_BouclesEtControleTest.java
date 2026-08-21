package org.natandaniel.m01_fondamentaux.c11_structures_controle.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo03_BouclesEtControleTest {

    @Test
    void compterChiffres_zeroAUnChiffre() {
        assertEquals(1, Exo03_BouclesEtControle.compterChiffres(0));
    }

    @Test
    void compterChiffres() {
        assertEquals(1, Exo03_BouclesEtControle.compterChiffres(7));
        assertEquals(2, Exo03_BouclesEtControle.compterChiffres(42));
        assertEquals(5, Exo03_BouclesEtControle.compterChiffres(12345));
    }

    @Test
    void estPremier_nombresPremiers() {
        assertTrue(Exo03_BouclesEtControle.estPremier(2));
        assertTrue(Exo03_BouclesEtControle.estPremier(3));
        assertTrue(Exo03_BouclesEtControle.estPremier(13));
        assertTrue(Exo03_BouclesEtControle.estPremier(97));
    }

    @Test
    void estPremier_nombresComposes() {
        assertFalse(Exo03_BouclesEtControle.estPremier(4));
        assertFalse(Exo03_BouclesEtControle.estPremier(9));
        assertFalse(Exo03_BouclesEtControle.estPremier(100));
    }
}
