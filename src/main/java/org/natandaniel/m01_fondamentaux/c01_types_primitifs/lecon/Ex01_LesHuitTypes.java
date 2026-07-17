package org.natandaniel.m01_fondamentaux.c01_types_primitifs.lecon;

/**
 * Leçon 1/3 — Les huit types primitifs, leurs tailles et leurs domaines.
 *
 * Java distingue deux mondes : les types primitifs (valeurs stockées directement)
 * et les types référence (adresses vers des objets). Voici les 8 primitifs.
 */
class Ex01_LesHuitTypes {

    public static void main(String[] args) {
        System.out.println("=== Types entiers ===");
        System.out.println("byte  : " + Byte.SIZE      + " bits  | min = " + Byte.MIN_VALUE      + " | max = " + Byte.MAX_VALUE);
        System.out.println("short : " + Short.SIZE     + " bits  | min = " + Short.MIN_VALUE     + " | max = " + Short.MAX_VALUE);
        System.out.println("int   : " + Integer.SIZE   + " bits  | min = " + Integer.MIN_VALUE   + " | max = " + Integer.MAX_VALUE);
        System.out.println("long  : " + Long.SIZE      + " bits  | min = " + Long.MIN_VALUE      + " | max = " + Long.MAX_VALUE);
        System.out.println("char  : " + Character.SIZE + " bits  | min = \\u0000 (0) | max = \\uffff (" + (int) Character.MAX_VALUE + ")");

        System.out.println("\n=== Types flottants ===");
        System.out.println("float  : " + Float.SIZE  + " bits  | ~" + Float.MIN_VALUE  + " à ~" + Float.MAX_VALUE);
        System.out.println("double : " + Double.SIZE + " bits  | ~" + Double.MIN_VALUE + " à ~" + Double.MAX_VALUE);

        System.out.println("\n=== Type booléen ===");
        System.out.println("boolean : true ou false  (taille en mémoire non définie par la JLS)");

        System.out.println("\nÀ retenir : les bornes de int (~±2,1 milliards) et que char est un entier 16 bits NON signé.");
    }
}
