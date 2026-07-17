package org.natandaniel.m01_fondamentaux.c05_debordement.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo01_ArithmetiqueSureTest {

    @Test
    void additionDeborde_detecteLesDepassements() {
        assertTrue(Exo01_ArithmetiqueSure.additionDeborde(Integer.MAX_VALUE, 1));
        assertTrue(Exo01_ArithmetiqueSure.additionDeborde(Integer.MIN_VALUE, -1));
        assertFalse(Exo01_ArithmetiqueSure.additionDeborde(2_000_000_000, 100));
        assertFalse(Exo01_ArithmetiqueSure.additionDeborde(-5, 5));
    }

    @Test
    void sommeSure_renvoieLaSommeQuandValide() {
        assertEquals(10, Exo01_ArithmetiqueSure.sommeSure(4, 6));
        assertEquals(Integer.MAX_VALUE, Exo01_ArithmetiqueSure.sommeSure(Integer.MAX_VALUE, 0));
    }

    @Test
    void sommeSure_leveQuandDeborde() {
        assertThrows(ArithmeticException.class,
            () -> Exo01_ArithmetiqueSure.sommeSure(Integer.MAX_VALUE, 1));
    }

    @Test
    void coherentAvecMathAddExact() {
        int[][] cas = {{1, 2}, {Integer.MAX_VALUE, 0}, {-3, 7}, {1_000_000, 2_000_000}};
        for (int[] c : cas) {
            assertEquals(Math.addExact(c[0], c[1]), Exo01_ArithmetiqueSure.sommeSure(c[0], c[1]));
        }
    }
}
