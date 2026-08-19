package org.natandaniel.m02_poo.c03_heritage.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class Exo02_ListeProduitsTest {

    @Test
    void prixLePlusEleve_listeMixteProduitEtProduitPhysique() {
        Exo02_ListeProduits.Produit ebook = new Exo02_ListeProduits.Produit("Ebook", 9.90);
        Exo02_ListeProduits.ProduitPhysique livre = new Exo02_ListeProduits.ProduitPhysique("Roman broché", 14.90, 400);
        Exo02_ListeProduits.ProduitPhysique coffret = new Exo02_ListeProduits.ProduitPhysique("Coffret collector", 59.90, 1200);

        // livre et coffret (ProduitPhysique) s'ajoutent directement à une List<Produit> :
        // tout ProduitPhysique EST-UN Produit.
        List<Exo02_ListeProduits.Produit> produits = List.of(ebook, livre, coffret);

        assertEquals(59.90, Exo02_ListeProduits.prixLePlusEleve(produits));
    }

    @Test
    void prixLePlusEleve_unSeulElement() {
        Exo02_ListeProduits.Produit p = new Exo02_ListeProduits.Produit("Stylo", 1.50);
        assertEquals(1.50, Exo02_ListeProduits.prixLePlusEleve(List.of(p)));
    }
}
