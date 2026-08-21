package org.natandaniel.m01_fondamentaux.c13_methodes.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_MoyenneTest {

    @Test
    void moyenne_plusieursValeurs() {
        assertEquals(20.0, Exo01_Moyenne.moyenne(10.0, 20.0, 30.0));
    }

    @Test
    void moyenne_uneSeuleValeur() {
        assertEquals(7.5, Exo01_Moyenne.moyenne(7.5));
    }

    @Test
    void moyenne_aucuneValeur() {
        assertEquals(0.0, Exo01_Moyenne.moyenne());
    }
}
