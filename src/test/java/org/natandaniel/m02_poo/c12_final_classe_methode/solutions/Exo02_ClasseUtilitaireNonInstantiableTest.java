package org.natandaniel.m02_poo.c12_final_classe_methode.solutions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo02_ClasseUtilitaireNonInstantiableTest {

    @Test
    void convertirPlusieurs_valeursConnues() {
        double[] resultat = Exo02_ClasseUtilitaireNonInstantiable.convertirPlusieurs(0, 100, -40);

        assertArrayEquals(new double[] {32.0, 212.0, -40.0}, resultat, 0.001);
    }

    @Test
    void convertirPlusieurs_tableauVide_renvoieTableauVide() {
        double[] resultat = Exo02_ClasseUtilitaireNonInstantiable.convertirPlusieurs();

        assertEquals(0, resultat.length);
    }
}
