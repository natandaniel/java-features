package org.natandaniel.m02_poo.c11_blocs_initialisation.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo02_UniciteDuBlocStatiqueTest {

    @Test
    void leBlocStatique_neSExecuteQuUneFois_memeApresTroisInstances() {
        int initialisations = Exo02_UniciteDuBlocStatique.nombreDInitialisationsApresTroisInstances();

        assertEquals(1, initialisations);
    }
}
