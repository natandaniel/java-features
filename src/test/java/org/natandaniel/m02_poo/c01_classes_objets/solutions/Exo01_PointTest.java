package org.natandaniel.m02_poo.c01_classes_objets.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_PointTest {

    @Test
    void distance_casNominal() {
        Exo01_Point.Point a = new Exo01_Point.Point(0, 0);
        Exo01_Point.Point b = new Exo01_Point.Point(3, 4);
        assertEquals(5.0, Exo01_Point.distance(a, b));
    }

    @Test
    void distance_memePoint_estNulle() {
        Exo01_Point.Point p = new Exo01_Point.Point(7, -2);
        assertEquals(0.0, Exo01_Point.distance(p, p));
    }

    @Test
    void milieu_casNominal() {
        Exo01_Point.Point a = new Exo01_Point.Point(0, 0);
        Exo01_Point.Point b = new Exo01_Point.Point(4, 10);
        Exo01_Point.Point m = Exo01_Point.milieu(a, b);
        assertEquals(2, m.x);
        assertEquals(5, m.y);
    }
}
