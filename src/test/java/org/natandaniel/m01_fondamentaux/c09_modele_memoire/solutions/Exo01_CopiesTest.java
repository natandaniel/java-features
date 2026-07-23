package org.natandaniel.m01_fondamentaux.c09_modele_memoire.solutions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo01_CopiesTest {

    @Test
    void copieSuperficielle_memeContenu() {
        int[][] source = {{1, 2}, {3, 4}};
        assertArrayEquals(source, Exo01_Copies.copieSuperficielle(source));
    }

    @Test
    void copieSuperficielle_nouveauTableauExterne_maisLignesPartagees() {
        int[][] source = {{1, 2}, {3, 4}};
        int[][] copie = Exo01_Copies.copieSuperficielle(source);

        assertNotSame(source, copie);        // le tableau externe est neuf
        assertSame(source[0], copie[0]);     // les lignes, elles, sont les mêmes objets
        assertSame(source[1], copie[1]);
    }

    @Test
    void copieSuperficielle_muterLaCopieMuteLaSource() {
        int[][] source = {{1, 2}, {3, 4}};
        int[][] copie = Exo01_Copies.copieSuperficielle(source);

        copie[0][0] = 42;
        assertEquals(42, source[0][0]);      // le piège : la source a changé
    }

    @Test
    void copieProfonde_memeContenu() {
        int[][] source = {{1, 2}, {3, 4}};
        assertArrayEquals(source, Exo01_Copies.copieProfonde(source));
    }

    @Test
    void copieProfonde_lignesIndependantes() {
        int[][] source = {{1, 2}, {3, 4}};
        int[][] copie = Exo01_Copies.copieProfonde(source);

        assertNotSame(source, copie);
        assertNotSame(source[0], copie[0]);
        assertNotSame(source[1], copie[1]);
    }

    @Test
    void copieProfonde_muterLaCopieLaisseLaSourceIntacte() {
        int[][] source = {{1, 2}, {3, 4}};
        int[][] copie = Exo01_Copies.copieProfonde(source);

        copie[0][0] = 42;
        assertEquals(1, source[0][0]);
        assertArrayEquals(new int[][]{{1, 2}, {3, 4}}, source);
    }

    @Test
    void copies_tableauVide() {
        int[][] vide = {};
        assertEquals(0, Exo01_Copies.copieSuperficielle(vide).length);
        assertEquals(0, Exo01_Copies.copieProfonde(vide).length);
    }

    @Test
    void copies_ligneVide() {
        int[][] source = {{}, {7}};
        int[][] profonde = Exo01_Copies.copieProfonde(source);

        assertEquals(0, profonde[0].length);
        assertNotSame(source[0], profonde[0]);
        assertArrayEquals(new int[]{7}, profonde[1]);
    }

    @Test
    void partagentLaLigne_vraiEnSuperficiel_fauxEnProfond() {
        int[][] source = {{1, 2}, {3, 4}};

        assertTrue(Exo01_Copies.partagentLaLigne(source, Exo01_Copies.copieSuperficielle(source), 0));
        assertFalse(Exo01_Copies.partagentLaLigne(source, Exo01_Copies.copieProfonde(source), 0));
    }

    @Test
    void partagentLaLigne_contenuIdentiqueMaisObjetsDistincts() {
        int[][] a = {{1, 2}};
        int[][] b = {{1, 2}};   // même contenu, allocation séparée

        assertFalse(Exo01_Copies.partagentLaLigne(a, b, 0));   // identité, pas contenu
    }

    @Test
    void partagentLaLigne_memeTableauCompareALuiMeme() {
        int[][] a = {{1, 2}, {3, 4}};
        assertTrue(Exo01_Copies.partagentLaLigne(a, a, 1));
    }
}
