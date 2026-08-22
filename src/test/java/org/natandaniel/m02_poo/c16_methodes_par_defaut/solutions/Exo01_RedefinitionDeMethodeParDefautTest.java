package org.natandaniel.m02_poo.c16_methodes_par_defaut.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_RedefinitionDeMethodeParDefautTest {

    @Test
    void fraisTraitement_redefinitLeCorpsParDefaut_retourneDeuxPourcentDuMontant() {
        Exo01_RedefinitionDeMethodeParDefaut.MoyenDePaiement moyen =
                new Exo01_RedefinitionDeMethodeParDefaut.PaiementInternational();

        assertEquals(2.0, moyen.fraisTraitement(100.0), 1e-9);
    }

    @Test
    void fraisTraitement_montantNul_retourneZero() {
        Exo01_RedefinitionDeMethodeParDefaut.MoyenDePaiement moyen =
                new Exo01_RedefinitionDeMethodeParDefaut.PaiementInternational();

        assertEquals(0.0, moyen.fraisTraitement(0.0), 1e-9);
    }
}
