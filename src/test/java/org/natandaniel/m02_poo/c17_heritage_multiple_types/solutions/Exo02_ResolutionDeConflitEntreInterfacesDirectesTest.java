package org.natandaniel.m02_poo.c17_heritage_multiple_types.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo02_ResolutionDeConflitEntreInterfacesDirectesTest {

    @Test
    void resume_conflitEntreRemboursableEtFacturable_combineLesDeux() {
        Exo02_ResolutionDeConflitEntreInterfacesDirectes.Avoir avoir =
                new Exo02_ResolutionDeConflitEntreInterfacesDirectes.Avoir();

        assertEquals("remboursement + facturation", avoir.resume());
    }
}
