package org.natandaniel.m01_fondamentaux.c04_operateurs.c01_arithmetiques.exercices;

/**
 * Exercices — Division/modulo « plancher ».
 *
 * En Java, {@code /} tronque vers zéro et {@code %} suit le signe du dividende.
 * Beaucoup d'algorithmes ont plutôt besoin d'un modulo « mathématique » dont le
 * résultat suit le signe du DIVISEUR (toujours dans [0, b[ quand b > 0).
 * Réimplémente-les SANS utiliser Math.floorMod / Math.floorDiv.
 *
 * Vérifie-toi avec {@code solutions/Exo01_DivisionPlancher} et son test.
 */
class Exo01_DivisionPlancher {

    /** Reste « plancher » : même signe que b. Ex. floorMod(-7, 3) = 2 (et non -1). */
    static int floorMod(int a, int b) {
        // TODO : partir de a % b puis corriger le signe
        throw new UnsupportedOperationException("À implémenter");
    }

    /** Quotient « plancher » : arrondi vers -∞. Ex. floorDiv(-7, 2) = -4 (et non -3). */
    static int floorDiv(int a, int b) {
        // TODO : partir de a / b puis corriger quand la division n'est pas exacte et les signes diffèrent
        throw new UnsupportedOperationException("À implémenter");
    }
}
