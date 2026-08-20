package org.natandaniel.m02_poo.c04_polymorphisme.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class Exo01_CatalogueMixteTest {

    @Test
    void etiquettes_dispatchePolymorphiquementSurChaqueSousType() {
        Exo01_CatalogueMixte.Produit generique = new Exo01_CatalogueMixte.Produit("Carte cadeau", 20.00);
        Exo01_CatalogueMixte.ProduitPhysique physique =
                new Exo01_CatalogueMixte.ProduitPhysique("Roman broché", 14.90, 400);
        Exo01_CatalogueMixte.ProduitNumerique numerique =
                new Exo01_CatalogueMixte.ProduitNumerique("Ebook", 9.90, 3_500_000);

        List<Exo01_CatalogueMixte.Produit> catalogue = List.of(generique, physique, numerique);

        List<String> etiquettes = Exo01_CatalogueMixte.etiquettes(catalogue);

        assertEquals("Carte cadeau — 20.0 €", etiquettes.get(0));
        assertEquals("Roman broché — 14.9 € — 400 g", etiquettes.get(1));
        assertTrue(etiquettes.get(2).endsWith("téléchargement 3 Mo"));
    }

    @Test
    void etiquettes_listeVide() {
        assertEquals(List.of(), Exo01_CatalogueMixte.etiquettes(List.of()));
    }
}
