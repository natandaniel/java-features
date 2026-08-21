package org.natandaniel.m02_poo.c05_encapsulation.solutions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo02_PlageEntiersTest {

    @Test
    void contient_valeurDansLaPlage() {
        Exo02_PlageEntiers.PlageEntiers plage = new Exo02_PlageEntiers.PlageEntiers(1, 10);
        assertTrue(plage.contient(1));
        assertTrue(plage.contient(10));
        assertTrue(plage.contient(5));
    }

    @Test
    void contient_valeurHorsPlage() {
        Exo02_PlageEntiers.PlageEntiers plage = new Exo02_PlageEntiers.PlageEntiers(1, 10);
        assertFalse(plage.contient(0));
        assertFalse(plage.contient(11));
    }

    @Test
    void constructeur_minSuperieurAMax_leve() {
        assertThrows(IllegalArgumentException.class, () -> new Exo02_PlageEntiers.PlageEntiers(10, 1));
    }
}
