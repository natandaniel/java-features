package org.natandaniel.m01_fondamentaux.c13_methodes.solutions;

/** Solution de référence — paramètres et valeur de retour. */
class Exo02_FormaterDuree {

    static String formaterDuree(int heures, int minutes) {
        return heures + "h" + (minutes < 10 ? "0" + minutes : String.valueOf(minutes));
    }
}
