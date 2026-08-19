package org.natandaniel.m02_poo.c02_encapsulation.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class Exo01_CompteBancaireTest {

    @Test
    void deposer_augmenteLeSolde() {
        Exo01_CompteBancaire.CompteBancaire compte = new Exo01_CompteBancaire.CompteBancaire(100.0);
        compte.deposer(50.0);
        assertEquals(150.0, compte.getSolde());
    }

    @Test
    void deposer_montantNegatif_leve() {
        Exo01_CompteBancaire.CompteBancaire compte = new Exo01_CompteBancaire.CompteBancaire(100.0);
        assertThrows(IllegalArgumentException.class, () -> compte.deposer(-1.0));
    }

    @Test
    void retirer_diminueLeSolde() {
        Exo01_CompteBancaire.CompteBancaire compte = new Exo01_CompteBancaire.CompteBancaire(100.0);
        compte.retirer(40.0);
        assertEquals(60.0, compte.getSolde());
    }

    @Test
    void retirer_montantSuperieurAuSolde_leve() {
        Exo01_CompteBancaire.CompteBancaire compte = new Exo01_CompteBancaire.CompteBancaire(100.0);
        assertThrows(IllegalStateException.class, () -> compte.retirer(200.0));
    }

    @Test
    void retirer_montantNegatif_leve() {
        Exo01_CompteBancaire.CompteBancaire compte = new Exo01_CompteBancaire.CompteBancaire(100.0);
        assertThrows(IllegalArgumentException.class, () -> compte.retirer(-1.0));
    }
}
