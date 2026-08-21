package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo03_FormaterListeTest {

    @Test
    void formaterAvecSeparateur_plusieursMots() {
        assertEquals("a, b, c",
                Exo03_FormaterListe.formaterAvecSeparateur(new String[] {"a", "b", "c"}, ", "));
    }

    @Test
    void formaterAvecSeparateur_unSeulMot() {
        assertEquals("seul", Exo03_FormaterListe.formaterAvecSeparateur(new String[] {"seul"}, ", "));
    }

    @Test
    void formaterAvecSeparateur_tableauVide() {
        assertEquals("", Exo03_FormaterListe.formaterAvecSeparateur(new String[0], ", "));
    }
}
