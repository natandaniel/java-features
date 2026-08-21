package org.natandaniel.m01_fondamentaux.c11_structures_controle.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo02_JoursDuMoisTest {

    @Test
    void moisDe31Jours() {
        assertEquals(31, Exo02_JoursDuMois.joursDansLeMois(1, false));
        assertEquals(31, Exo02_JoursDuMois.joursDansLeMois(12, false));
    }

    @Test
    void moisDe30Jours() {
        assertEquals(30, Exo02_JoursDuMois.joursDansLeMois(4, false));
        assertEquals(30, Exo02_JoursDuMois.joursDansLeMois(6, false));
        assertEquals(30, Exo02_JoursDuMois.joursDansLeMois(9, false));
        assertEquals(30, Exo02_JoursDuMois.joursDansLeMois(11, false));
    }

    @Test
    void fevrier() {
        assertEquals(28, Exo02_JoursDuMois.joursDansLeMois(2, false));
        assertEquals(29, Exo02_JoursDuMois.joursDansLeMois(2, true));
    }
}
