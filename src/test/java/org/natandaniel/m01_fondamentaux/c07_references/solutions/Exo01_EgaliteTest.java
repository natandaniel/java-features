package org.natandaniel.m01_fondamentaux.c07_references.solutions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo01_EgaliteTest {

    @Test
    void memeContenu_compareLeContenu() {
        assertTrue(Exo01_Egalite.memeContenu(new String("hi"), new String("hi")));
        assertFalse(Exo01_Egalite.memeContenu("hi", "bye"));
    }

    @Test
    void memeContenu_gereNull() {
        assertTrue(Exo01_Egalite.memeContenu(null, null));
        assertFalse(Exo01_Egalite.memeContenu(null, "hi"));
        assertFalse(Exo01_Egalite.memeContenu("hi", null));
    }

    @Test
    void memeObjet_estLIdentite() {
        String litteral = "hello";
        assertTrue(Exo01_Egalite.memeObjet(litteral, "hello"));        // pool : même objet
        assertFalse(Exo01_Egalite.memeObjet(new String("hello"), litteral)); // hors pool
    }
}
