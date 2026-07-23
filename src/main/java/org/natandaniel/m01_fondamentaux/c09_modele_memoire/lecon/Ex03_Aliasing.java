package org.natandaniel.m01_fondamentaux.c09_modele_memoire.lecon;

import java.util.Arrays;

/**
 * Leçon 3/6 — Copier une référence n'est pas copier l'objet (aliasing).
 */
class Ex03_Aliasing {

    public static void main(String[] args) {
        System.out.println("=== Deux variables, UN seul objet ===");
        int[] original = {1, 2, 3};
        int[] alias = original;              // copie l'ADRESSE, pas les 3 entiers
        alias[0] = 99;
        System.out.println("original = " + Arrays.toString(original) + "   → modifié via alias");
        System.out.println("original == alias : " + (original == alias) + "   (même objet sur le tas)");

        System.out.println("\n=== Copie superficielle d'un tableau 2D ===");
        int[][] source = {{1, 2}, {3, 4}};
        int[][] superficielle = source.clone();   // nouveau tableau EXTERNE, mêmes lignes
        System.out.println("superficielle == source        : " + (superficielle == source)
                + "   (deux tableaux externes distincts)");
        System.out.println("superficielle[0] == source[0]  : " + (superficielle[0] == source[0])
                + "    (mais les LIGNES sont partagées)");

        superficielle[0][0] = 42;
        System.out.println("après superficielle[0][0] = 42 → source[0][0] = " + source[0][0]
                + "   la source a changé !");

        System.out.println("\n=== Copie profonde : chaque ligne est clonée ===");
        int[][] source2 = {{1, 2}, {3, 4}};
        int[][] profonde = new int[source2.length][];
        for (int i = 0; i < source2.length; i++) {
            profonde[i] = source2[i].clone();     // un nouvel objet par ligne
        }
        System.out.println("profonde[0] == source2[0]      : " + (profonde[0] == source2[0])
                + "   (lignes indépendantes)");

        profonde[0][0] = 42;
        System.out.println("après profonde[0][0] = 42      → source2[0][0] = " + source2[0][0]
                + "    la source est intacte");

        System.out.println("\n=== Le piège du contenu vs de l'identité ===");
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        System.out.println("a == b              : " + (a == b) + "   (deux objets distincts)");
        System.out.println("Arrays.equals(a, b) : " + Arrays.equals(a, b) + "    (même contenu)");
    }
}
