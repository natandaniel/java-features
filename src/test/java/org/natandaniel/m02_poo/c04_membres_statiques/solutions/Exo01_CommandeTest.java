package org.natandaniel.m02_poo.c04_membres_statiques.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_CommandeTest {

    @Test
    void nombreCommandes_augmenteAChaqueCreation() {
        int avant = Exo01_Commande.nombreCommandes();
        new Exo01_Commande.Commande(10);
        new Exo01_Commande.Commande(20);
        int apres = Exo01_Commande.nombreCommandes();
        assertEquals(avant + 2, apres);
    }

    @Test
    void champStatic_memeValeurViaClasseEtViaInstance() {
        Exo01_Commande.Commande commande = new Exo01_Commande.Commande(5);
        assertEquals(Exo01_Commande.Commande.nombreCreees, commande.nombreCreees);
    }

    @Test
    void montantMoyen_casNominal() {
        Exo01_Commande.Commande[] commandes = {
                new Exo01_Commande.Commande(10),
                new Exo01_Commande.Commande(20),
                new Exo01_Commande.Commande(30)
        };
        assertEquals(20.0, Exo01_Commande.montantMoyen(commandes));
    }

    @Test
    void montantMoyen_tableauVide_estZero() {
        assertEquals(0.0, Exo01_Commande.montantMoyen(new Exo01_Commande.Commande[0]));
    }
}
