package org.natandaniel.m01_fondamentaux.c09_modele_memoire.exercices;

import java.util.List;
import java.util.Map;

/**
 * Exercices — Accessibilité : ce qu'on retient, on l'empêche d'être collecté.
 *
 * <p>Le GC ne récupère que l'INACCESSIBLE. Une fuite mémoire en Java n'est jamais
 * un défaut du GC : c'est du code qui garde un chemin vers un objet devenu inutile.
 * Ces exercices modélisent les deux fuites les plus fréquentes — le cache sans purge
 * et l'écouteur jamais désinscrit — sous forme de méthodes pures.
 */
class Exo03_Accessibilite {

    /**
     * Construit un cache borné : insère les clés de {@code cles} dans l'ordre, en associant
     * à chacune la valeur {@code "valeur:" + cle}, mais ne retient jamais plus de
     * {@code capacite} entrées.
     *
     * <p>Dès que l'insertion ferait dépasser la capacité, l'entrée la PLUS ANCIENNEMENT
     * insérée est retirée : plus aucun chemin ne mène à elle, elle devient collectable.
     *
     * <p>L'ordre d'itération de la map renvoyée doit être l'ordre d'insertion.
     * Si {@code capacite <= 0}, renvoie une map vide.
     *
     * <p>Exemple : {@code cacheBorne(List.of("a","b","c"), 2)} → {@code {b=valeur:b, c=valeur:c}}.
     */
    static Map<String, String> cacheBorne(List<String> cles, int capacite) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Rejoue un journal d'inscriptions et de désinscriptions, et renvoie les écouteurs
     * encore inscrits à la fin, dans l'ordre de leur première inscription.
     *
     * <p>Chaque opération est une chaîne : {@code "+nom"} inscrit, {@code "-nom"} désinscrit.
     * Une inscription en double ne crée pas de doublon ; une désinscription d'un nom absent
     * est sans effet.
     *
     * <p>Exemple : {@code registreFinal(List.of("+a","+b","-a","+b"))} → {@code [b]}.
     */
    static List<String> registreFinal(List<String> operations) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Indique si {@code nom} est encore inscrit après avoir rejoué {@code operations}.
     *
     * <p>C'est la question de l'accessibilité posée directement : tant que la réponse est
     * {@code true}, l'objet correspondant ne pourrait pas être collecté.
     */
    static boolean toujoursAccessible(List<String> operations, String nom) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
