package org.natandaniel.m02_poo.c20_enumerations.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo02_ConstructeurEtChampParConstanteTest {

    private static final Exo02_ConstructeurEtChampParConstante.NiveauDifficulte DEBUTANT =
            Exo02_ConstructeurEtChampParConstante.NiveauDifficulte.DEBUTANT;
    private static final Exo02_ConstructeurEtChampParConstante.NiveauDifficulte INTERMEDIAIRE =
            Exo02_ConstructeurEtChampParConstante.NiveauDifficulte.INTERMEDIAIRE;
    private static final Exo02_ConstructeurEtChampParConstante.NiveauDifficulte AVANCE =
            Exo02_ConstructeurEtChampParConstante.NiveauDifficulte.AVANCE;

    @Test
    void debutant_pointsObtenus_neChangePasLesPointsDeBase() {
        assertEquals(100, DEBUTANT.pointsObtenus(100));
    }

    @Test
    void intermediaire_pointsObtenus_appliqueUnCoefficientUnEtDemi() {
        assertEquals(150, INTERMEDIAIRE.pointsObtenus(100));
    }

    @Test
    void avance_pointsObtenus_appliqueUnCoefficientDeux() {
        assertEquals(200, AVANCE.pointsObtenus(100));
    }

    @Test
    void pointsObtenus_arrondiAuPlusProche() {
        // 1.5 * 3 = 4.5 -> arrondi à 5 (Math.round arrondit à l'entier le plus proche)
        assertEquals(5, INTERMEDIAIRE.pointsObtenus(3));
    }

    @Test
    void coefficient_estAccessibleDepuisChaqueConstante() {
        assertEquals(1.5, INTERMEDIAIRE.coefficient(), 1e-9);
    }
}
