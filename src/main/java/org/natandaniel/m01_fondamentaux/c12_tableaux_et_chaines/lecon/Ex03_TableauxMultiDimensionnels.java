package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.lecon;

/**
 * Leçon 3/7 — tableaux multidimensionnels (JLS §10.1, §10.2).
 *
 * En Java, il n'existe pas de vrai tableau à N dimensions : `int[][]` est un
 * tableau DONT LE TYPE D'ÉLÉMENT est lui-même `int[]` — un tableau de tableaux.
 * Conséquence directe : chaque sous-tableau a sa propre longueur, indépendante
 * des autres (tableau "en dents de scie" / jagged array).
 */
class Ex03_TableauxMultiDimensionnels {

    public static void main(String[] args) {
        System.out.println("=== création rectangulaire : toutes les lignes ont la même longueur ===");
        int nbEleves = 3;
        int nbTrimestres = 2;
        int[][] notesParTrimestre = new int[nbEleves][nbTrimestres];
        notesParTrimestre[0][0] = 14;
        notesParTrimestre[0][1] = 16;
        notesParTrimestre[1][0] = 9;
        notesParTrimestre[1][1] = 11;
        notesParTrimestre[2][0] = 18;
        notesParTrimestre[2][1] = 17;
        for (int eleve = 0; eleve < notesParTrimestre.length; eleve++) {
            System.out.print("élève " + eleve + " : ");
            for (int trimestre = 0; trimestre < notesParTrimestre[eleve].length; trimestre++) {
                System.out.print(notesParTrimestre[eleve][trimestre] + " ");
            }
            System.out.println();
        }

        System.out.println("\n=== chaque ligne est un tableau int[] à part entière : accessible seul ===");
        int[] notesEleve1 = notesParTrimestre[1];
        System.out.println("notesEleve1.length = " + notesEleve1.length);

        System.out.println("\n=== tableau en dents de scie : les lignes n'ont pas besoin de la même longueur ===");
        // Contrairement à un vrai tableau rectangulaire, chaque sous-tableau est créé
        // indépendamment : rien n'oblige à leur donner la même longueur.
        int[][] devoirsParEleve = new int[3][];
        devoirsParEleve[0] = new int[]{15, 12};
        devoirsParEleve[1] = new int[]{10, 14, 16, 9};
        devoirsParEleve[2] = new int[]{20};
        for (int eleve = 0; eleve < devoirsParEleve.length; eleve++) {
            System.out.println("élève " + eleve + " a rendu " + devoirsParEleve[eleve].length + " devoir(s)");
        }

        System.out.println("\n=== initialiseur imbriqué : une paire d'accolades par niveau ===");
        int[][] grille = {
                {1, 2, 3},
                {4, 5},
                {6}
        };
        System.out.println("grille[1][1] = " + grille[1][1] + " (dernière valeur de la 2e ligne)");
    }
}
