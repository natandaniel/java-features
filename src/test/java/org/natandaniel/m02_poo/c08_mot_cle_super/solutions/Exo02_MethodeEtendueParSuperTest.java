package org.natandaniel.m02_poo.c08_mot_cle_super.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo02_MethodeEtendueParSuperTest {

    @Test
    void etiquette_reprendCelleDeLaSuperclasse_etLaComplete() {
        Exo02_MethodeEtendueParSuper.ProduitEnSolde solde =
                new Exo02_MethodeEtendueParSuper.ProduitEnSolde("Vélo", 300.0, 20.0);

        assertEquals("Vélo — 300.0 € — solde 20.0%", solde.etiquette());
    }

    @Test
    void etiquette_produitNonSolde_referenceUtiliseeCommeBase() {
        Exo02_MethodeEtendueParSuper.Produit produit = new Exo02_MethodeEtendueParSuper.Produit("Vélo", 300.0);

        assertEquals("Vélo — 300.0 €", produit.etiquette());
    }
}
