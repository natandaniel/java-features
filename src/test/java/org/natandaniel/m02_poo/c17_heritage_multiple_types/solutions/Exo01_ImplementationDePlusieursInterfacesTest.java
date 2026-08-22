package org.natandaniel.m02_poo.c17_heritage_multiple_types.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_ImplementationDePlusieursInterfacesTest {

    @Test
    void rembourser_montantValide_diminueLeSolde() {
        Exo01_ImplementationDePlusieursInterfaces.Avoir avoir =
                new Exo01_ImplementationDePlusieursInterfaces.Avoir(100.0);

        avoir.rembourser(30.0);

        assertEquals(70.0, avoir.solde(), 1e-9);
    }

    @Test
    void rembourser_montantNul_neChangeRienAuSolde() {
        Exo01_ImplementationDePlusieursInterfaces.Avoir avoir =
                new Exo01_ImplementationDePlusieursInterfaces.Avoir(100.0);

        avoir.rembourser(0.0);

        assertEquals(100.0, avoir.solde(), 1e-9);
    }

    @Test
    void genererFacture_refleteLeSoldeCourant() {
        Exo01_ImplementationDePlusieursInterfaces.Avoir avoir =
                new Exo01_ImplementationDePlusieursInterfaces.Avoir(70.0);

        assertEquals("Avoir de 70.0 €", avoir.genererFacture());
    }
}
