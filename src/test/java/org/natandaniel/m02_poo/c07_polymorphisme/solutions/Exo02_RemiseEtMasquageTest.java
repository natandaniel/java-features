package org.natandaniel.m02_poo.c07_polymorphisme.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class Exo02_RemiseEtMasquageTest {

    @Test
    void prixTotalApresRemise_listeMixteAvecRetourCovariant() {
        Exo02_RemiseEtMasquage.Produit generique = new Exo02_RemiseEtMasquage.Produit("Carte cadeau", 20.00);
        Exo02_RemiseEtMasquage.ProduitPhysique physique =
                new Exo02_RemiseEtMasquage.ProduitPhysique("Roman broché", 100.00, 400);

        double total = Exo02_RemiseEtMasquage.prixTotalApresRemise(List.of(generique, physique), 10);

        assertEquals(18.00 + 90.00, total, 1e-9);
    }

    @Test
    void prixTotalApresRemise_remiseNulle_prixInchange() {
        Exo02_RemiseEtMasquage.Produit p = new Exo02_RemiseEtMasquage.Produit("Stylo", 1.50);
        assertEquals(1.50, Exo02_RemiseEtMasquage.prixTotalApresRemise(List.of(p), 0), 1e-9);
    }

    @Test
    void prixTotalApresRemise_remiseTotale_prixNul() {
        Exo02_RemiseEtMasquage.Produit p = new Exo02_RemiseEtMasquage.Produit("Stylo", 1.50);
        assertEquals(0.0, Exo02_RemiseEtMasquage.prixTotalApresRemise(List.of(p), 100), 1e-9);
    }

    @Test
    void categorieVueDepuisProduit_masqueeParTypeDeclare_pasParTypeReel() {
        // produit est déclaré Produit ; l'objet réel est un ProduitPhysique dont categorie
        // vaut "Import" — mais un champ static est masqué, jamais redéfini (JLS §8.4.8.2) :
        // c'est la version de Produit qui est vue ici, quel que soit le type réel de l'objet.
        Exo02_RemiseEtMasquage.Produit produit =
                new Exo02_RemiseEtMasquage.ProduitPhysique("Roman broché", 14.90, 400);

        assertEquals("Générique", Exo02_RemiseEtMasquage.categorieVueDepuisProduit(produit));
    }

    @Test
    void categorieVueDepuisProduit_produitDeBase() {
        Exo02_RemiseEtMasquage.Produit produit = new Exo02_RemiseEtMasquage.Produit("Carte cadeau", 20.00);
        assertEquals("Générique", Exo02_RemiseEtMasquage.categorieVueDepuisProduit(produit));
    }
}
