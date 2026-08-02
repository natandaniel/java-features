package org.natandaniel.m01_fondamentaux.c10_execution.exercices;

import java.util.List;

/**
 * Exercices — Mémoire virtuelle : pagination et copie sur écriture.
 *
 * <p>Le processus manipule des adresses <b>virtuelles</b>. Le noyau, via la MMU, les traduit
 * en adresses <b>physiques</b> par blocs de taille fixe : les <b>pages</b>. Une adresse
 * virtuelle se décompose en (numéro de page, position dans la page).
 *
 * <p>Après un {@code fork}, parent et enfant partagent les mêmes pages physiques. Tant que
 * personne n'écrit, une seule copie existe en RAM. À la première <b>écriture</b> sur une page,
 * le noyau en fabrique une copie privée pour l'auteur : c'est le <b>copy-on-write</b>. Le partage
 * ne brise jamais l'isolation, et l'économie de RAM est réelle tant qu'on ne fait que lire.
 */
class Exo03_PagesEtCopyOnWrite {

    /**
     * Renvoie le numéro de la page contenant {@code adresseVirtuelle}.
     *
     * <p>Exemple : avec des pages de 4096 octets, l'adresse 5000 est dans la page 1.
     *
     * <p>Lève {@link IllegalArgumentException} si {@code adresseVirtuelle} est négative
     * ou si {@code tailleDePage} n'est pas une puissance de deux strictement positive.
     */
    static long numeroDePage(long adresseVirtuelle, int tailleDePage) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Renvoie la position de {@code adresseVirtuelle} à l'intérieur de sa page.
     *
     * <p>Exemple : avec des pages de 4096 octets, l'adresse 5000 est à l'offset 904.
     * Mêmes conditions d'erreur que {@link #numeroDePage(long, int)}.
     */
    static long offsetDansPage(long adresseVirtuelle, int tailleDePage) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Reconstruit l'adresse virtuelle à partir de ses deux composantes.
     *
     * <p>C'est l'opération inverse des deux précédentes : pour toute adresse valide,
     * {@code adresseVirtuelle(numeroDePage(a, t), offsetDansPage(a, t), t) == a}.
     *
     * <p>Lève {@link IllegalArgumentException} si {@code numeroDePage} est négatif,
     * si {@code offset} n'est pas dans {@code [0, tailleDePage[}, ou si la taille de page
     * n'est pas une puissance de deux strictement positive.
     */
    static long adresseVirtuelle(long numeroDePage, long offset, int tailleDePage) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Renvoie le nombre de pages nécessaires pour loger {@code tailleEnOctets} octets.
     *
     * <p>La dernière page est occupée même partiellement : 1 octet coûte une page entière.
     * {@code tailleEnOctets == 0} → 0 page. Négatif → {@link IllegalArgumentException}.
     */
    static int pagesNecessaires(long tailleEnOctets, int tailleDePage) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Simule un {@code fork} puis une suite d'accès mémoire, et renvoie le nombre de pages
     * <b>physiques</b> réellement occupées à la fin.
     *
     * <p>Situation de départ : parent et enfant partagent {@code nbPages} pages, soit
     * {@code nbPages} pages physiques. Chaque opération est une chaîne
     * {@code "<qui>:<action>:<page>"} où {@code qui} vaut {@code "P"} (parent) ou {@code "E"}
     * (enfant), et {@code action} vaut {@code "lire"} ou {@code "ecrire"}.
     *
     * <p>Règles :
     * <ul>
     *   <li>une <b>lecture</b> ne coûte rien : la page reste partagée ;</li>
     *   <li>la <b>première écriture</b> sur une page encore partagée en crée une copie privée
     *       pour l'auteur : +1 page physique, et la page cesse d'être partagée ;</li>
     *   <li>toute écriture <b>suivante</b> sur cette même page se fait en place : +0.</li>
     * </ul>
     *
     * <p>Exemple : {@code pagesPhysiquesApres(4, List.of("P:lire:0", "E:ecrire:0", "P:ecrire:0"))}
     * → {@code 5} — une seule copie, provoquée par la première écriture.
     *
     * <p>Lève {@link IllegalArgumentException} si {@code nbPages} est négatif, si une opération
     * est mal formée, ou si un numéro de page est hors de {@code [0, nbPages[}.
     */
    static int pagesPhysiquesApres(int nbPages, List<String> operations) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Indique si la page {@code page} est encore partagée entre les deux processus après
     * la séquence d'opérations, c'est-à-dire si personne n'y a jamais écrit.
     *
     * <p>Mêmes conditions d'erreur que {@link #pagesPhysiquesApres(int, List)}.
     */
    static boolean estPartagee(int nbPages, List<String> operations, int page) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
