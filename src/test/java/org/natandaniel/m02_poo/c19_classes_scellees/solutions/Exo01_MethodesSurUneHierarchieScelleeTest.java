package org.natandaniel.m02_poo.c19_classes_scellees.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_MethodesSurUneHierarchieScelleeTest {

    @Test
    void carteBancaire_masquer_gardeLesQuatreDerniersChiffres() {
        Exo01_MethodesSurUneHierarchieScellee.CarteBancaire carte =
                new Exo01_MethodesSurUneHierarchieScellee.CarteBancaire(
                        "PAY-001", "4242424242424242");

        assertEquals("**** **** **** 4242", carte.masquer());
    }

    @Test
    void virementBancaire_masquer_remplaceToutSaufLesQuatreDerniersCaracteresParDesPuces() {
        Exo01_MethodesSurUneHierarchieScellee.VirementBancaire virement =
                new Exo01_MethodesSurUneHierarchieScellee.VirementBancaire(
                        "PAY-002", "123456789012");

        assertEquals("••••••••9012", virement.masquer());
    }

    @Test
    void especes_masquer_renvoieToujoursLeMemeLibelle() {
        Exo01_MethodesSurUneHierarchieScellee.Especes especes =
                new Exo01_MethodesSurUneHierarchieScellee.Especes("PAY-003");

        assertEquals("Espèces", especes.masquer());
    }

    @Test
    void reference_estHeriteeDeModePaiementPourLesTroisSousClasses() {
        Exo01_MethodesSurUneHierarchieScellee.CarteBancaire carte =
                new Exo01_MethodesSurUneHierarchieScellee.CarteBancaire(
                        "PAY-001", "4242424242424242");

        assertEquals("PAY-001", carte.reference());
    }
}
