package org.natandaniel.m02_poo.c10_methodes_fabriques_statiques.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

class Exo02_FormesTest {

    @Test
    void cercle_calculeLAireAttendue() {
        Exo02_Formes.Forme disque = Exo02_Formes.cercle(2.0);

        assertEquals(Math.PI * 4.0, disque.aire(), 1e-9);
    }

    @Test
    void cercle_rayonNul_produitAireNulle() {
        assertEquals(0.0, Exo02_Formes.cercle(0.0).aire());
    }

    @Test
    void cercle_chaqueAppelProduitUneNouvelleInstance() {
        Exo02_Formes.Forme a = Exo02_Formes.cercle(1.0);
        Exo02_Formes.Forme b = Exo02_Formes.cercle(1.0);

        assertNotSame(a, b);
    }
}
