package org.natandaniel.m02_poo.c12_final_classe_methode.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_InvariantProtegeParFinalTest {

    @Test
    void montantNetApresRemise_commandeOrdinaire_remisePlafonneeA50() {
        double net = Exo01_InvariantProtegeParFinal.montantNetApresRemise(
                new Exo01_InvariantProtegeParFinal.Commande(), 1000.0);

        assertEquals(950.0, net, 0.001);
    }

    @Test
    void montantNetApresRemise_commandeVip_memeRegleQueCommandeOrdinaire() {
        double net = Exo01_InvariantProtegeParFinal.montantNetApresRemise(
                new Exo01_InvariantProtegeParFinal.CommandeVip(), 1000.0);

        assertEquals(950.0, net, 0.001);
    }

    @Test
    void montantNetApresRemise_petitMontant_remiseProportionnelle() {
        double net = Exo01_InvariantProtegeParFinal.montantNetApresRemise(
                new Exo01_InvariantProtegeParFinal.Commande(), 100.0);

        assertEquals(80.0, net, 0.001);
    }
}
