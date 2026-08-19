package org.natandaniel.m02_poo.c03_heritage.solutions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo01_ProduitTest {

    @Test
    void depassePoidsLimite_champPropreAccessible() {
        Exo01_Produit.ProduitPhysique produit = new Exo01_Produit.ProduitPhysique("Micro-ondes", 89.90, 8000, 40000);
        assertTrue(Exo01_Produit.depassePoidsLimite(produit, 5000));
        assertFalse(Exo01_Produit.depassePoidsLimite(produit, 10000));
    }

    @Test
    void estColisStandard_casNominal() {
        Exo01_Produit.ProduitPhysique livre = new Exo01_Produit.ProduitPhysique("Roman", 12.90, 400, 900);
        assertTrue(Exo01_Produit.estColisStandard(livre));
    }

    @Test
    void estColisStandard_tropLourdOuTropVolumineux() {
        Exo01_Produit.ProduitPhysique lourd = new Exo01_Produit.ProduitPhysique("Enclume", 45.00, 5000, 1200);
        assertFalse(Exo01_Produit.estColisStandard(lourd));

        Exo01_Produit.ProduitPhysique volumineux = new Exo01_Produit.ProduitPhysique("Coussin géant", 30.00, 800, 8000);
        assertFalse(Exo01_Produit.estColisStandard(volumineux));
    }
}
