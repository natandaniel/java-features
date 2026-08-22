package org.natandaniel.m02_poo.c14_clonage.solutions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

class Exo02_ClonageProfondDeuxNiveauxTest {

    @Test
    void clone_mutationDunFichierDansLeClone_dossierOriginalInchange() {
        Exo02_ClonageProfondDeuxNiveaux.Dossier dossier =
                new Exo02_ClonageProfondDeuxNiveaux.Dossier(new String[] {"rapport.pdf"});
        Exo02_ClonageProfondDeuxNiveaux.Classeur originale =
                new Exo02_ClonageProfondDeuxNiveaux.Classeur(
                        new Exo02_ClonageProfondDeuxNiveaux.Dossier[] {dossier});

        Exo02_ClonageProfondDeuxNiveaux.Classeur clone = originale.clone();
        clone.dossiers()[0].renommerFichier("rapport-v2.pdf", 0);

        assertArrayEquals(new String[] {"rapport.pdf"}, originale.dossiers()[0].fichiers());
        assertArrayEquals(new String[] {"rapport-v2.pdf"}, clone.dossiers()[0].fichiers());
    }

    @Test
    void clone_tableauEtChaqueDossierSontDesInstancesDistinctes() {
        Exo02_ClonageProfondDeuxNiveaux.Dossier dossier =
                new Exo02_ClonageProfondDeuxNiveaux.Dossier(new String[] {"a.txt"});
        Exo02_ClonageProfondDeuxNiveaux.Classeur originale =
                new Exo02_ClonageProfondDeuxNiveaux.Classeur(
                        new Exo02_ClonageProfondDeuxNiveaux.Dossier[] {dossier});

        Exo02_ClonageProfondDeuxNiveaux.Classeur clone = originale.clone();

        assertNotSame(originale.dossiers(), clone.dossiers());
        assertNotSame(originale.dossiers()[0], clone.dossiers()[0]);
    }
}
