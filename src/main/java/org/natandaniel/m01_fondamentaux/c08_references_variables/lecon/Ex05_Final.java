package org.natandaniel.m01_fondamentaux.c08_references_variables.lecon;

import java.util.ArrayList;
import java.util.List;

/**
 * Leçon 2/3 — final : la variable est figée, pas forcément l'objet.
 */
class Ex05_Final {

    public static void main(String[] args) {
        System.out.println("=== final sur un primitif : valeur immuable ===");
        final int MAX = 100;
        // MAX = 200; // ERREUR : cannot assign a value to final variable MAX
        System.out.println("final int MAX = " + MAX);

        System.out.println("\n=== final sur une référence : le POINTEUR est figé, pas l'objet ===");
        final List<String> liste = new ArrayList<>();
        liste.add("premier");    // OK : on modifie le contenu de l'objet
        liste.add("deuxième");   // OK
        // liste = new ArrayList<>(); // ERREUR : réaffectation interdite
        System.out.println("liste (final, contenu mutable) : " + liste);

        System.out.println("\nPour un objet vraiment immuable, la classe doit l'interdire (comme String).");
    }
}
