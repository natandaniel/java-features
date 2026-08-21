package org.natandaniel.m02_poo.c10_methodes_fabriques_statiques.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_CoordonneesTest {

    @Test
    void depuisDegres_conserveLesValeurs() {
        Exo01_Coordonnees.Coordonnees paris =
                Exo01_Coordonnees.Coordonnees.depuisDegres(48.8566, 2.3522);

        assertEquals(48.8566, paris.latitudeDegres);
        assertEquals(2.3522, paris.longitudeDegres);
    }

    @Test
    void depuisRadians_zero_donneZeroDegre() {
        Exo01_Coordonnees.Coordonnees origine =
                Exo01_Coordonnees.Coordonnees.depuisRadians(0.0, 0.0);

        assertEquals(0.0, origine.latitudeDegres);
        assertEquals(0.0, origine.longitudeDegres);
    }

    @Test
    void depuisRadians_convertitCorrectementEnDegres() {
        Exo01_Coordonnees.Coordonnees pole =
                Exo01_Coordonnees.Coordonnees.depuisRadians(Math.PI / 2, Math.PI);

        assertEquals(90.0, pole.latitudeDegres, 1e-9);
        assertEquals(180.0, pole.longitudeDegres, 1e-9);
    }
}
