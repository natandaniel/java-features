package org.natandaniel.m01_fondamentaux.c03_operateurs.c01_arithmetiques.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_DivisionPlancherTest {

    @Test
    void floorMod_coherentAvecMathFloorMod() {
        int[][] cas = {{7, 3}, {-7, 3}, {7, -3}, {-7, -3}, {10, 5}, {-1, 4}, {0, 7}};
        for (int[] c : cas) {
            assertEquals(Math.floorMod(c[0], c[1]), Exo01_DivisionPlancher.floorMod(c[0], c[1]),
                "floorMod(" + c[0] + ", " + c[1] + ")");
        }
    }

    @Test
    void floorDiv_coherentAvecMathFloorDiv() {
        int[][] cas = {{7, 2}, {-7, 2}, {7, -2}, {-7, -2}, {10, 5}, {-1, 4}, {0, 7}};
        for (int[] c : cas) {
            assertEquals(Math.floorDiv(c[0], c[1]), Exo01_DivisionPlancher.floorDiv(c[0], c[1]),
                "floorDiv(" + c[0] + ", " + c[1] + ")");
        }
    }

    @Test
    void exemplesEmblematiques() {
        assertEquals(2, Exo01_DivisionPlancher.floorMod(-7, 3));
        assertEquals(-4, Exo01_DivisionPlancher.floorDiv(-7, 2));
    }
}
