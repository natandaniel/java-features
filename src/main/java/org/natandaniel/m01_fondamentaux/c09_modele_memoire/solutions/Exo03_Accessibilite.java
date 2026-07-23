package org.natandaniel.m01_fondamentaux.c09_modele_memoire.solutions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Solutions — Accessibilité : ce qu'on retient, on l'empêche d'être collecté.
 */
class Exo03_Accessibilite {

    /** Cache borné : au-delà de la capacité, la plus ancienne entrée est retirée. */
    static Map<String, String> cacheBorne(List<String> cles, int capacite) {
        // LinkedHashMap : conserve l'ordre d'insertion, donc « la plus ancienne » a un sens.
        Map<String, String> cache = new LinkedHashMap<>();
        if (capacite <= 0) {
            return cache;
        }
        for (String cle : cles) {
            cache.put(cle, "valeur:" + cle);
            if (cache.size() > capacite) {
                // On retire la première clé de l'ordre d'insertion : plus aucun chemin
                // vers son entrée → elle devient candidate au GC.
                String plusAncienne = cache.keySet().iterator().next();
                cache.remove(plusAncienne);
            }
        }
        return cache;
    }

    /** Rejoue le journal ; les inscrits restants sont les objets encore accessibles. */
    static List<String> registreFinal(List<String> operations) {
        List<String> inscrits = new ArrayList<>();
        for (String operation : operations) {
            String nom = operation.substring(1);
            if (operation.charAt(0) == '+') {
                if (!inscrits.contains(nom)) {
                    inscrits.add(nom);   // pas de doublon : une seule case le retient
                }
            } else {
                inscrits.remove(nom);    // sans effet si absent ; c'est LA ligne qu'on oublie
            }
        }
        return inscrits;
    }

    /** Encore inscrit = encore accessible = non collectable. */
    static boolean toujoursAccessible(List<String> operations, String nom) {
        return registreFinal(operations).contains(nom);
    }
}
