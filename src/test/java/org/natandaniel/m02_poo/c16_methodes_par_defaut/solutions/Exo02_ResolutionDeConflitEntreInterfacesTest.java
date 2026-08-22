package org.natandaniel.m02_poo.c16_methodes_par_defaut.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo02_ResolutionDeConflitEntreInterfacesTest {

    @Test
    void resume_conflitEntreRemboursableEtFacturable_combineLesDeux() {
        Exo02_ResolutionDeConflitEntreInterfaces.Avoir avoir =
                new Exo02_ResolutionDeConflitEntreInterfaces.AvoirStandard();

        assertEquals("remboursement + facturation", avoir.resume());
    }
}
