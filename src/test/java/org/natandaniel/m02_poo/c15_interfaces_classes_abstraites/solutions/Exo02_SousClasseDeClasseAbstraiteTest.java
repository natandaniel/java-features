package org.natandaniel.m02_poo.c15_interfaces_classes_abstraites.solutions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo02_SousClasseDeClasseAbstraiteTest {

    @Test
    void executer_soldeSuffisant_accepte() {
        Exo02_SousClasseDeClasseAbstraite.TraitementPaiement traitement =
                new Exo02_SousClasseDeClasseAbstraite.TraitementVirement(200.0, 300.0);

        assertTrue(traitement.executer());
    }

    @Test
    void executer_soldeExactementEgal_accepte() {
        Exo02_SousClasseDeClasseAbstraite.TraitementPaiement traitement =
                new Exo02_SousClasseDeClasseAbstraite.TraitementVirement(200.0, 200.0);

        assertTrue(traitement.executer());
    }

    @Test
    void executer_soldeInsuffisant_refuse() {
        Exo02_SousClasseDeClasseAbstraite.TraitementPaiement traitement =
                new Exo02_SousClasseDeClasseAbstraite.TraitementVirement(200.0, 100.0);

        assertFalse(traitement.executer());
    }
}
