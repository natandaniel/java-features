package org.natandaniel.m01_fondamentaux.c09_modele_memoire.solutions;

/**
 * Solutions — La pile est finie : un frame par appel.
 */
class Exo02_Recursion {

    /** Un seul frame : la pile ne grandit pas avec n. */
    static long sommeIterative(int n) {
        long somme = 0;
        for (int i = 1; i <= n; i++) {
            somme += i;
        }
        return somme;   // n <= 0 : la boucle ne s'exécute pas, on renvoie 0
    }

    /** Un frame par appel : la pile grandit linéairement avec n. */
    static long sommeRecursive(int n) {
        if (n <= 0) {
            return 0;   // cas de base : sans lui, la récursion serait infinie
        }
        return n + sommeRecursive(n - 1);
        // Java n'élimine PAS la récursion terminale : chaque appel garde son frame
        // jusqu'au retour du plus profond.
    }

    /** Mesure la profondeur maximale avant saturation de la pile. */
    static int profondeurAtteinte() {
        return descendre(1);
    }

    private static int descendre(int profondeur) {
        try {
            return descendre(profondeur + 1);
        } catch (StackOverflowError e) {
            // La pile est saturée : ne rien faire de coûteux ici, juste remonter la valeur.
            return profondeur;
        }
    }
}
