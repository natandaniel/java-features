package org.natandaniel.m02_poo.c11_blocs_initialisation.solutions;

import java.util.ArrayList;
import java.util.List;

/** Solution de référence — le bloc d'instance de Vehicule s'exécute avant celui de VehiculeElectrique. */
class Exo01_OrdreExecution {

    static final List<String> journal = new ArrayList<>();

    static class Vehicule {
        {
            journal.add("instance Vehicule");
        }

        Vehicule() {
            journal.add("constructeur Vehicule");
        }
    }

    static class VehiculeElectrique extends Vehicule {
        {
            journal.add("instance VehiculeElectrique");
        }

        VehiculeElectrique() {
            journal.add("constructeur VehiculeElectrique");
        }
    }

    static List<String> journalPourUneCreation() {
        journal.clear();
        new VehiculeElectrique();
        return List.copyOf(journal);
    }
}
