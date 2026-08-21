package org.natandaniel.m02_poo.c03_mot_cle_this.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo01_RectangleTest {

    @Test
    void aireEtPerimetre_casNominal() {
        Exo01_Rectangle.Rectangle r = new Exo01_Rectangle.Rectangle(3, 4);
        assertEquals(12, r.aire());
        assertEquals(14, r.perimetre());
    }

    @Test
    void estCarre_rectangleQuelconque_estFaux() {
        assertFalse(new Exo01_Rectangle.Rectangle(3, 4).estCarre());
    }

    @Test
    void constructeurUnArgument_delegueVersLeCarre() {
        Exo01_Rectangle.Rectangle carre = new Exo01_Rectangle.Rectangle(5);
        assertTrue(carre.estCarre());
        assertEquals(25, carre.aire());
    }
}
