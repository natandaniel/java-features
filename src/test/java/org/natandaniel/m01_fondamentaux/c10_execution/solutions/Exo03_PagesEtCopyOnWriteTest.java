package org.natandaniel.m01_fondamentaux.c10_execution.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class Exo03_PagesEtCopyOnWriteTest {

    private static final int PAGE_4K = 4096;

    @Test
    void numeroDePage_casNominal() {
        assertEquals(0L, Exo03_PagesEtCopyOnWrite.numeroDePage(0L, PAGE_4K));
        assertEquals(1L, Exo03_PagesEtCopyOnWrite.numeroDePage(5000L, PAGE_4K));
        assertEquals(2L, Exo03_PagesEtCopyOnWrite.numeroDePage(8192L, PAGE_4K));
    }

    @Test
    void numeroDePage_bordsDePage() {
        assertEquals(0L, Exo03_PagesEtCopyOnWrite.numeroDePage(4095L, PAGE_4K));   // dernier octet
        assertEquals(1L, Exo03_PagesEtCopyOnWrite.numeroDePage(4096L, PAGE_4K));   // premier de la suivante
    }

    @Test
    void offsetDansPage_casNominal() {
        assertEquals(0L, Exo03_PagesEtCopyOnWrite.offsetDansPage(0L, PAGE_4K));
        assertEquals(904L, Exo03_PagesEtCopyOnWrite.offsetDansPage(5000L, PAGE_4K));
        assertEquals(4095L, Exo03_PagesEtCopyOnWrite.offsetDansPage(4095L, PAGE_4K));
        assertEquals(0L, Exo03_PagesEtCopyOnWrite.offsetDansPage(4096L, PAGE_4K));
    }

    @Test
    void offsetEstToujoursDansLaPage() {
        for (long adresse = 0; adresse < 20_000; adresse += 997) {
            long offset = Exo03_PagesEtCopyOnWrite.offsetDansPage(adresse, PAGE_4K);
            assertTrue(offset >= 0 && offset < PAGE_4K, "offset hors page pour " + adresse);
        }
    }

    @Test
    void adresseVirtuelle_estLInverseDeLaDecomposition() {
        for (long adresse : List.of(0L, 1L, 4095L, 4096L, 5000L, 1_000_000L)) {
            long page = Exo03_PagesEtCopyOnWrite.numeroDePage(adresse, PAGE_4K);
            long offset = Exo03_PagesEtCopyOnWrite.offsetDansPage(adresse, PAGE_4K);
            assertEquals(adresse, Exo03_PagesEtCopyOnWrite.adresseVirtuelle(page, offset, PAGE_4K));
        }
    }

    @Test
    void adresseVirtuelle_offsetHorsPage() {
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.adresseVirtuelle(1L, PAGE_4K, PAGE_4K));
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.adresseVirtuelle(1L, -1L, PAGE_4K));
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.adresseVirtuelle(-1L, 0L, PAGE_4K));
    }

    @Test
    void traduction_adresseNegativeOuTailleInvalide() {
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.numeroDePage(-1L, PAGE_4K));
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.offsetDansPage(-1L, PAGE_4K));
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.numeroDePage(0L, 0));
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.numeroDePage(0L, -4096));
        // 3000 n'est pas une puissance de deux : la MMU ne saurait pas découper l'adresse.
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.numeroDePage(0L, 3000));
    }

    @Test
    void pagesNecessaires_arrondiAuSuperieur() {
        assertEquals(0, Exo03_PagesEtCopyOnWrite.pagesNecessaires(0L, PAGE_4K));
        assertEquals(1, Exo03_PagesEtCopyOnWrite.pagesNecessaires(1L, PAGE_4K));        // 1 octet = 1 page
        assertEquals(1, Exo03_PagesEtCopyOnWrite.pagesNecessaires(4096L, PAGE_4K));     // pile poil
        assertEquals(2, Exo03_PagesEtCopyOnWrite.pagesNecessaires(4097L, PAGE_4K));     // un octet de trop
        assertEquals(3, Exo03_PagesEtCopyOnWrite.pagesNecessaires(10_000L, PAGE_4K));
    }

    @Test
    void pagesNecessaires_tailleNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.pagesNecessaires(-1L, PAGE_4K));
    }

    @Test
    void copyOnWrite_sansEcriture_rienNestCopie() {
        assertEquals(4, Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(
                4, List.of("P:lire:0", "E:lire:0", "P:lire:3", "E:lire:2")));
    }

    @Test
    void copyOnWrite_aucuneOperation() {
        assertEquals(4, Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(4, List.of()));
        assertEquals(0, Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(0, List.of()));
    }

    @Test
    void copyOnWrite_premiereEcriture_copiePrivee() {
        assertEquals(5, Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(4, List.of("E:ecrire:0")));
    }

    @Test
    void copyOnWrite_ecrituresSuivantes_neCoutentRien() {
        // La page 0 n'est copiée qu'une fois, quel que soit le nombre d'écritures et l'auteur.
        assertEquals(5, Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(
                4, List.of("P:lire:0", "E:ecrire:0", "P:ecrire:0", "E:ecrire:0")));
    }

    @Test
    void copyOnWrite_chaquePageEcriteCouteUneCopie() {
        assertEquals(7, Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(
                4, List.of("P:ecrire:0", "E:ecrire:1", "P:ecrire:2", "P:lire:3")));
    }

    @Test
    void copyOnWrite_toutEcrire_doubleLEmpreinte() {
        assertEquals(8, Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(
                4, List.of("E:ecrire:0", "E:ecrire:1", "E:ecrire:2", "E:ecrire:3")));
    }

    @Test
    void copyOnWrite_operationsInvalides() {
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(-1, List.of()));
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(4, List.of("P:ecrire:4")));
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(4, List.of("P:ecrire:-1")));
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(4, List.of("X:ecrire:0")));
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(4, List.of("P:effacer:0")));
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(4, List.of("P:ecrire")));
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(4, List.of("P:ecrire:zero")));
    }

    @Test
    void estPartagee_tantQuePersonneNEcrit() {
        List<String> lectures = List.of("P:lire:1", "E:lire:1");
        assertTrue(Exo03_PagesEtCopyOnWrite.estPartagee(4, lectures, 1));
    }

    @Test
    void estPartagee_faussePourLaPageEcrite_vraiePourLesAutres() {
        List<String> operations = List.of("P:ecrire:2", "E:lire:3");

        assertFalse(Exo03_PagesEtCopyOnWrite.estPartagee(4, operations, 2));
        assertTrue(Exo03_PagesEtCopyOnWrite.estPartagee(4, operations, 3));
        assertTrue(Exo03_PagesEtCopyOnWrite.estPartagee(4, operations, 0));
    }

    @Test
    void estPartagee_pageHorsBornes() {
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.estPartagee(4, List.of(), 4));
        assertThrows(IllegalArgumentException.class,
                () -> Exo03_PagesEtCopyOnWrite.estPartagee(4, List.of(), -1));
    }

    @Test
    void cheminReel_forkPuisLectureSeule_economiseLaRam() {
        // Un fork suivi d'un exec ne touche presque rien : l'économie est maximale.
        int pagesDuProcessus = Exo03_PagesEtCopyOnWrite.pagesNecessaires(64L * 1024, PAGE_4K);
        assertEquals(16, pagesDuProcessus);
        assertEquals(16, Exo03_PagesEtCopyOnWrite.pagesPhysiquesApres(
                pagesDuProcessus, List.of("E:lire:0", "E:lire:1", "E:lire:15")));
    }
}
