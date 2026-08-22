package org.natandaniel.m02_poo.c14_clonage.solutions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

class Exo01_ClonageProfondUnNiveauTest {

    @Test
    void clone_mutationDuClone_originaleInchangee() {
        Exo01_ClonageProfondUnNiveau.Equipe originale =
                new Exo01_ClonageProfondUnNiveau.Equipe(new String[] {"Aline", "Bruno"});
        Exo01_ClonageProfondUnNiveau.Equipe clone = originale.clone();

        clone.remplacerJoueur("Chloé", 0);

        assertArrayEquals(new String[] {"Aline", "Bruno"}, originale.joueurs());
        assertArrayEquals(new String[] {"Chloé", "Bruno"}, clone.joueurs());
    }

    @Test
    void clone_mutationDeLoriginale_cloneInchange() {
        Exo01_ClonageProfondUnNiveau.Equipe originale =
                new Exo01_ClonageProfondUnNiveau.Equipe(new String[] {"Aline", "Bruno"});
        Exo01_ClonageProfondUnNiveau.Equipe clone = originale.clone();

        originale.remplacerJoueur("Diane", 1);

        assertArrayEquals(new String[] {"Aline", "Diane"}, originale.joueurs());
        assertArrayEquals(new String[] {"Aline", "Bruno"}, clone.joueurs());
    }

    @Test
    void clone_estUneInstanceEtUnTableauDistincts() {
        Exo01_ClonageProfondUnNiveau.Equipe originale =
                new Exo01_ClonageProfondUnNiveau.Equipe(new String[] {"Aline"});
        Exo01_ClonageProfondUnNiveau.Equipe clone = originale.clone();

        assertNotSame(originale, clone);
        assertNotSame(originale.joueurs(), clone.joueurs());
    }
}
