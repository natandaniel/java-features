package org.natandaniel.m02_poo.c01_classes_objets.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo02_RectangleTest {

    @Test
    void aireEtPerimetre_casNominal() {
        Exo02_Rectangle.Rectangle r = new Exo02_Rectangle.Rectangle(3, 4);
        assertEquals(12, r.aire());
        assertEquals(14, r.perimetre());
    }

    @Test
    void estCarre_rectangleQuelconque_estFaux() {
        assertFalse(new Exo02_Rectangle.Rectangle(3, 4).estCarre());
    }

    @Test
    void constructeurUnArgument_delegueVersLeCarre() {
        Exo02_Rectangle.Rectangle carre = new Exo02_Rectangle.Rectangle(5);
        assertTrue(carre.estCarre());
        assertEquals(25, carre.aire());
    }
}
