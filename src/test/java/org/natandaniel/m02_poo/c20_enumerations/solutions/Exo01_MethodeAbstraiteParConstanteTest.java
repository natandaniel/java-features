package org.natandaniel.m02_poo.c20_enumerations.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_MethodeAbstraiteParConstanteTest {

    private static final Exo01_MethodeAbstraiteParConstante.ModeExpedition STANDARD =
            Exo01_MethodeAbstraiteParConstante.ModeExpedition.STANDARD;
    private static final Exo01_MethodeAbstraiteParConstante.ModeExpedition EXPRESS =
            Exo01_MethodeAbstraiteParConstante.ModeExpedition.EXPRESS;
    private static final Exo01_MethodeAbstraiteParConstante.ModeExpedition GRATUITE =
            Exo01_MethodeAbstraiteParConstante.ModeExpedition.GRATUITE;

    @Test
    void standard_fraisEnEuros_ajouteUnDemiEuroParKilo() {
        assertEquals(4.0, STANDARD.fraisEnEuros(4.0), 1e-9);
    }

    @Test
    void express_fraisEnEuros_ajouteUnEuroVingtParKilo() {
        assertEquals(6.2, EXPRESS.fraisEnEuros(1.0), 1e-9);
    }

    @Test
    void gratuite_fraisEnEuros_estToujoursZeroQuelQueSoitLePoids() {
        assertEquals(0.0, GRATUITE.fraisEnEuros(50.0), 1e-9);
    }

    @Test
    void values_contientLesTroisConstantesDansLOrdreDeDeclaration() {
        Exo01_MethodeAbstraiteParConstante.ModeExpedition[] valeurs =
                Exo01_MethodeAbstraiteParConstante.ModeExpedition.values();
        assertEquals(3, valeurs.length);
        assertEquals(STANDARD, valeurs[0]);
        assertEquals(EXPRESS, valeurs[1]);
        assertEquals(GRATUITE, valeurs[2]);
    }
}
