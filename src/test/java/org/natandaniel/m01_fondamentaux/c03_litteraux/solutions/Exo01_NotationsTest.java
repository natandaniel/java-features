package org.natandaniel.m01_fondamentaux.c03_litteraux.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_NotationsTest {

    @Test
    void chaqueNotationDonneLaBonneValeur() {
        assertEquals(250, Exo01_Notations.deuxCentCinquanteEnHexa());
        assertEquals(10, Exo01_Notations.dixEnBinaire());
        assertEquals(64, Exo01_Notations.soixanteQuatreEnOctal());
        assertEquals(1_000_000_000, Exo01_Notations.unMilliard());
    }
}
