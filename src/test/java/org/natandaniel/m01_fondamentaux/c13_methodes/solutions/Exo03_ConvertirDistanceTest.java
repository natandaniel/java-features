package org.natandaniel.m01_fondamentaux.c13_methodes.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo03_ConvertirDistanceTest {

    @Test
    void convertirEnMetres_entier_choisitLaVarianteInt() {
        assertEquals(2000, Exo03_ConvertirDistance.convertirEnMetres(2));
    }

    @Test
    void convertirEnMetres_fractionnaire_choisitLaVarianteDouble() {
        assertEquals(2500.0, Exo03_ConvertirDistance.convertirEnMetres(2.5));
    }
}
