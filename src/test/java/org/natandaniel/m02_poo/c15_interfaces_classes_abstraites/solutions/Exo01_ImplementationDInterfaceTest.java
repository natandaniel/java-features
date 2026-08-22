package org.natandaniel.m02_poo.c15_interfaces_classes_abstraites.solutions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo01_ImplementationDInterfaceTest {

    @Test
    void payer_montantSousLePlafond_accepte() {
        Exo01_ImplementationDInterface.MoyenDePaiement moyen =
                new Exo01_ImplementationDInterface.PaiementEspeces(100.0);

        assertTrue(moyen.payer(50.0));
    }

    @Test
    void payer_montantEgalAuPlafond_accepte() {
        Exo01_ImplementationDInterface.MoyenDePaiement moyen =
                new Exo01_ImplementationDInterface.PaiementEspeces(100.0);

        assertTrue(moyen.payer(100.0));
    }

    @Test
    void payer_montantAuDessusDuPlafond_refuse() {
        Exo01_ImplementationDInterface.MoyenDePaiement moyen =
                new Exo01_ImplementationDInterface.PaiementEspeces(100.0);

        assertFalse(moyen.payer(150.0));
    }
}
