package org.natandaniel.m02_poo.c01_classes_objets.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo01_EtudiantTest {

    @Test
    void moyenne_casNominal() {
        Exo01_Etudiant.Etudiant e = new Exo01_Etudiant.Etudiant();
        e.note1 = 12;
        e.note2 = 8;
        e.note3 = 16;
        assertEquals(12.0, Exo01_Etudiant.moyenne(e));
    }

    @Test
    void estAdmis_moyenneExactementDix_estAdmis() {
        Exo01_Etudiant.Etudiant e = new Exo01_Etudiant.Etudiant();
        e.note1 = 10;
        e.note2 = 10;
        e.note3 = 10;
        assertTrue(Exo01_Etudiant.estAdmis(e));
    }

    @Test
    void estAdmis_moyenneSousDix_estRefuse() {
        Exo01_Etudiant.Etudiant e = new Exo01_Etudiant.Etudiant();
        e.note1 = 5;
        e.note2 = 6;
        e.note3 = 7;
        assertFalse(Exo01_Etudiant.estAdmis(e));
    }

    @Test
    void deuxInstances_champsModifiesSeparement_resteIndependant() {
        Exo01_Etudiant.Etudiant a = new Exo01_Etudiant.Etudiant();
        Exo01_Etudiant.Etudiant b = new Exo01_Etudiant.Etudiant();

        a.note1 = 20;
        a.note2 = 20;
        a.note3 = 20;
        b.note1 = 0;
        b.note2 = 0;
        b.note3 = 0;

        assertEquals(20.0, Exo01_Etudiant.moyenne(a));
        assertEquals(0.0, Exo01_Etudiant.moyenne(b));
    }
}
