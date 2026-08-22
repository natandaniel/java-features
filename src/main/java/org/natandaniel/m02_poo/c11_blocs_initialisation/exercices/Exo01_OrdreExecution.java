package org.natandaniel.m02_poo.c11_blocs_initialisation.exercices;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercice — ordre d'exécution entre bloc d'instance de superclasse et de sous-classe (JLS
 * §12.5 : la superclasse termine entièrement son initialisation avant que la sous-classe ne
 * commence la sienne). {@code Vehicule}/{@code VehiculeElectrique} journalisent chaque étape ;
 * il s'agit de produire, dans l'ordre, le journal d'une création de {@code VehiculeElectrique}.
 */
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

    /**
     * Réinitialise le journal, crée un {@code VehiculeElectrique}, puis renvoie le journal
     * obtenu (copie, dans l'ordre où les étapes ont été enregistrées).
     */
    static List<String> journalPourUneCreation() {
        throw new UnsupportedOperationException("À implémenter");
    }
}
