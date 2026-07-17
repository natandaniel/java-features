package org.natandaniel.m01_fondamentaux.c03_operateurs.c01_arithmetiques.solutions;

/** Solution de référence — Division/modulo « plancher ». */
class Exo01_DivisionPlancher {

    static int floorMod(int a, int b) {
        int r = a % b;
        // Si le reste est non nul et de signe opposé à b, on le ramène côté b.
        if (r != 0 && (r ^ b) < 0) {
            r += b;
        }
        return r;
    }

    static int floorDiv(int a, int b) {
        int q = a / b;
        // Si la division n'est pas exacte et que les signes diffèrent, le vrai plancher est q-1.
        if ((a % b != 0) && ((a ^ b) < 0)) {
            q--;
        }
        return q;
    }
}
