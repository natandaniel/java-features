package org.natandaniel.m02_poo.c08_mot_cle_super.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_ChampMasqueParSuperTest {

    @Test
    void origineHeritee_renvoieLeChampDeLaSuperclasse_pasLeChampMasquant() {
        Exo01_ChampMasqueParSuper.ProduitImporte importe = new Exo01_ChampMasqueParSuper.ProduitImporte();

        assertEquals("Origine inconnue", importe.origineHeritee());
        assertEquals("Import direct", importe.origine); // le champ masquant reste inchangé
    }
}
