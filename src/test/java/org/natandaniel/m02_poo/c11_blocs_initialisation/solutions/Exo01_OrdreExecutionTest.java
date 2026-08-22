package org.natandaniel.m02_poo.c11_blocs_initialisation.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class Exo01_OrdreExecutionTest {

    @Test
    void journalPourUneCreation_superclasseAvantSousClasse() {
        List<String> journal = Exo01_OrdreExecution.journalPourUneCreation();

        assertEquals(
                List.of(
                        "instance Vehicule",
                        "constructeur Vehicule",
                        "instance VehiculeElectrique",
                        "constructeur VehiculeElectrique"),
                journal);
    }

    @Test
    void journalPourUneCreation_estReinitialiseAChaqueAppel() {
        Exo01_OrdreExecution.journalPourUneCreation();
        List<String> deuxiemeJournal = Exo01_OrdreExecution.journalPourUneCreation();

        assertEquals(4, deuxiemeJournal.size());
    }
}
