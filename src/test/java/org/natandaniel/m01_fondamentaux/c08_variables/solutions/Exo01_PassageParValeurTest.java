package org.natandaniel.m01_fondamentaux.c08_variables.solutions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_PassageParValeurTest {

    @Test
    void doubler_renvoieLeResultat() {
        assertEquals(10, Exo01_PassageParValeur.doubler(5));
        assertEquals(0, Exo01_PassageParValeur.doubler(0));
        assertEquals(-8, Exo01_PassageParValeur.doubler(-4));
    }

    @Test
    void remplirDeZeros_modifieLObjetPointe() {
        int[] t = {1, 2, 3, 4};
        Exo01_PassageParValeur.remplirDeZeros(t);
        assertArrayEquals(new int[]{0, 0, 0, 0}, t);
    }

    @Test
    void remplirDeZeros_tableauVide() {
        int[] vide = {};
        Exo01_PassageParValeur.remplirDeZeros(vide);
        assertArrayEquals(new int[]{}, vide);
    }
}
