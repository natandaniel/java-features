package org.natandaniel.m01_fondamentaux.c10_execution.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo02_EmpreinteMemoireTest {

    @Test
    void enOctets_suffixes() {
        assertEquals(1024L, Exo02_EmpreinteMemoire.enOctets("1k"));
        assertEquals(536_870_912L, Exo02_EmpreinteMemoire.enOctets("512m"));
        assertEquals(2_147_483_648L, Exo02_EmpreinteMemoire.enOctets("2g"));
    }

    @Test
    void enOctets_suffixesInsensiblesALaCasse() {
        assertEquals(Exo02_EmpreinteMemoire.enOctets("512m"), Exo02_EmpreinteMemoire.enOctets("512M"));
        assertEquals(Exo02_EmpreinteMemoire.enOctets("2g"), Exo02_EmpreinteMemoire.enOctets("2G"));
        assertEquals(Exo02_EmpreinteMemoire.enOctets("4k"), Exo02_EmpreinteMemoire.enOctets("4K"));
    }

    @Test
    void enOctets_sansSuffixe_dejaEnOctets() {
        assertEquals(1024L, Exo02_EmpreinteMemoire.enOctets("1024"));
        assertEquals(0L, Exo02_EmpreinteMemoire.enOctets("0"));
        assertEquals(0L, Exo02_EmpreinteMemoire.enOctets("0m"));
    }

    @Test
    void enOctets_espacesIgnores() {
        assertEquals(536_870_912L, Exo02_EmpreinteMemoire.enOctets("  512m  "));
    }

    @Test
    void enOctets_grandesValeurs_pasDeDebordement() {
        // 4 Go ne tient pas dans un int : le calcul doit se faire en long.
        assertEquals(4L * 1024 * 1024 * 1024, Exo02_EmpreinteMemoire.enOctets("4g"));
    }

    @Test
    void enOctets_entreesInvalides() {
        assertThrows(IllegalArgumentException.class, () -> Exo02_EmpreinteMemoire.enOctets(null));
        assertThrows(IllegalArgumentException.class, () -> Exo02_EmpreinteMemoire.enOctets(""));
        assertThrows(IllegalArgumentException.class, () -> Exo02_EmpreinteMemoire.enOctets("   "));
        assertThrows(IllegalArgumentException.class, () -> Exo02_EmpreinteMemoire.enOctets("512t"));
        assertThrows(IllegalArgumentException.class, () -> Exo02_EmpreinteMemoire.enOctets("m"));
        assertThrows(IllegalArgumentException.class, () -> Exo02_EmpreinteMemoire.enOctets("12x4m"));
        assertThrows(IllegalArgumentException.class, () -> Exo02_EmpreinteMemoire.enOctets("-512m"));
    }

    @Test
    void empreinteEstimee_sommeDesTroisPostes() {
        assertEquals(100 + 20 + 10 * 5,
                Exo02_EmpreinteMemoire.empreinteEstimee(100L, 20L, 10, 5L));
    }

    @Test
    void empreinteEstimee_aucunThread() {
        assertEquals(120L, Exo02_EmpreinteMemoire.empreinteEstimee(100L, 20L, 0, 5L));
    }

    @Test
    void empreinteEstimee_versionChaine() {
        long attendu = 512L * 1024 * 1024        // heap
                + 64L * 1024 * 1024              // metaspace
                + 10L * 1024 * 1024;             // 10 piles de 1 Mo
        assertEquals(attendu, Exo02_EmpreinteMemoire.empreinteEstimee("512m", "64m", 10, "1m"));
    }

    @Test
    void empreinteEstimee_valeursNegatives() {
        assertThrows(IllegalArgumentException.class,
                () -> Exo02_EmpreinteMemoire.empreinteEstimee(-1L, 20L, 10, 5L));
        assertThrows(IllegalArgumentException.class,
                () -> Exo02_EmpreinteMemoire.empreinteEstimee(100L, -20L, 10, 5L));
        assertThrows(IllegalArgumentException.class,
                () -> Exo02_EmpreinteMemoire.empreinteEstimee(100L, 20L, -1, 5L));
        assertThrows(IllegalArgumentException.class,
                () -> Exo02_EmpreinteMemoire.empreinteEstimee(100L, 20L, 10, -5L));
    }

    @Test
    void horsHeap_estToutCeQuiNestPasLeHeap() {
        assertEquals(20 + 10 * 5, Exo02_EmpreinteMemoire.horsHeap(100L, 20L, 10, 5L));
    }

    @Test
    void horsHeap_nulSiRienEnDehors() {
        assertEquals(0L, Exo02_EmpreinteMemoire.horsHeap(100L, 0L, 0, 0L));
    }

    @Test
    void depasseHeapMax_desQuIlYaDuHorsHeap() {
        assertTrue(Exo02_EmpreinteMemoire.depasseHeapMax(100L, 20L, 0, 0L));   // metaspace seul
        assertTrue(Exo02_EmpreinteMemoire.depasseHeapMax(100L, 0L, 1, 5L));    // une pile suffit
    }

    @Test
    void depasseHeapMax_casTheoriqueSansHorsHeap() {
        assertFalse(Exo02_EmpreinteMemoire.depasseHeapMax(100L, 0L, 0, 0L));
        assertFalse(Exo02_EmpreinteMemoire.depasseHeapMax(100L, 0L, 10, 0L));  // threads sans pile
    }

    @Test
    void cheminReel_leProcessusPeseBienPlusQueXmx() {
        long xmx = Exo02_EmpreinteMemoire.enOctets("512m");
        long empreinte = Exo02_EmpreinteMemoire.empreinteEstimee("512m", "80m", 24, "1m");

        assertTrue(empreinte > xmx);
        assertEquals(104L * 1024 * 1024, empreinte - xmx);   // 80 Mo + 24 Mo hors heap
    }
}
