package org.natandaniel.m01_fondamentaux.c13_methodes.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo02_FormaterDureeTest {

    @Test
    void formaterDuree_minutesSurUnChiffre() {
        assertEquals("2h05", Exo02_FormaterDuree.formaterDuree(2, 5));
    }

    @Test
    void formaterDuree_minutesSurDeuxChiffres() {
        assertEquals("11h45", Exo02_FormaterDuree.formaterDuree(11, 45));
    }

    @Test
    void formaterDuree_zero() {
        assertEquals("0h00", Exo02_FormaterDuree.formaterDuree(0, 0));
    }

    @Test
    void formaterDuree_ordreDesParametresCompte() {
        assertEquals("5h02", Exo02_FormaterDuree.formaterDuree(5, 2));
    }
}
