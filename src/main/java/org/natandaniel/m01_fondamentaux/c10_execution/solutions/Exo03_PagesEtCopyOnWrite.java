package org.natandaniel.m01_fondamentaux.c10_execution.solutions;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Solutions — Mémoire virtuelle : pagination et copie sur écriture.
 */
class Exo03_PagesEtCopyOnWrite {

    /** Le numéro de page, c'est le quotient : les bits de poids fort de l'adresse. */
    static long numeroDePage(long adresseVirtuelle, int tailleDePage) {
        verifierAdresse(adresseVirtuelle);
        verifierTailleDePage(tailleDePage);
        return adresseVirtuelle / tailleDePage;
    }

    /** L'offset, c'est le reste : les bits de poids faible, que la MMU ne traduit pas. */
    static long offsetDansPage(long adresseVirtuelle, int tailleDePage) {
        verifierAdresse(adresseVirtuelle);
        verifierTailleDePage(tailleDePage);
        return adresseVirtuelle % tailleDePage;
    }

    /** L'opération inverse : recoller les deux moitiés de l'adresse. */
    static long adresseVirtuelle(long numeroDePage, long offset, int tailleDePage) {
        verifierTailleDePage(tailleDePage);
        if (numeroDePage < 0) {
            throw new IllegalArgumentException("numéro de page négatif : " + numeroDePage);
        }
        if (offset < 0 || offset >= tailleDePage) {
            throw new IllegalArgumentException("offset hors de la page : " + offset);
        }
        return numeroDePage * tailleDePage + offset;
    }

    /** Division arrondie au supérieur : la dernière page est occupée même à moitié vide. */
    static int pagesNecessaires(long tailleEnOctets, int tailleDePage) {
        verifierTailleDePage(tailleDePage);
        if (tailleEnOctets < 0) {
            throw new IllegalArgumentException("taille négative : " + tailleEnOctets);
        }
        long pages = tailleEnOctets / tailleDePage;
        if (tailleEnOctets % tailleDePage != 0) {
            pages++;   // le reliquat occupe une page entière : c'est la fragmentation interne
        }
        return Math.toIntExact(pages);
    }

    /**
     * Les pages de départ sont partagées ; chaque page écrite au moins une fois
     * a coûté exactement une copie privée.
     */
    static int pagesPhysiquesApres(int nbPages, List<String> operations) {
        return nbPages + pagesEcrites(nbPages, operations).size();
    }

    /** Une page reste partagée tant que personne n'y a écrit. */
    static boolean estPartagee(int nbPages, List<String> operations, int page) {
        verifierNumeroDePage(page, nbPages);
        return !pagesEcrites(nbPages, operations).contains(page);
    }

    /** Rejoue les accès et renvoie les pages ayant subi au moins une écriture. */
    private static Set<Integer> pagesEcrites(int nbPages, List<String> operations) {
        if (nbPages < 0) {
            throw new IllegalArgumentException("nombre de pages négatif : " + nbPages);
        }
        Set<Integer> ecrites = new LinkedHashSet<>();
        for (String operation : operations) {
            String[] morceaux = operation.split(":");
            if (morceaux.length != 3) {
                throw new IllegalArgumentException("opération mal formée : " + operation);
            }
            if (!morceaux[0].equals("P") && !morceaux[0].equals("E")) {
                throw new IllegalArgumentException("acteur inconnu : " + operation);
            }
            int page;
            try {
                page = Integer.parseInt(morceaux[2]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("numéro de page illisible : " + operation);
            }
            verifierNumeroDePage(page, nbPages);

            switch (morceaux[1]) {                 // switch fléché sur String : @since Java 14
                // La lecture ne coûte rien : les deux processus lisent la MÊME page physique.
                case "lire" -> { }
                // L'écriture ne coûte QUE la première fois : ensuite la page est déjà privée.
                case "ecrire" -> ecrites.add(page);
                default -> throw new IllegalArgumentException("action inconnue : " + operation);
            }
        }
        return ecrites;
    }

    private static void verifierAdresse(long adresseVirtuelle) {
        if (adresseVirtuelle < 0) {
            throw new IllegalArgumentException("adresse négative : " + adresseVirtuelle);
        }
    }

    /** Une taille de page est toujours une puissance de deux : la MMU découpe par bits. */
    private static void verifierTailleDePage(int tailleDePage) {
        if (tailleDePage <= 0 || (tailleDePage & (tailleDePage - 1)) != 0) {
            throw new IllegalArgumentException(
                    "taille de page invalide (puissance de deux attendue) : " + tailleDePage);
        }
    }

    private static void verifierNumeroDePage(int page, int nbPages) {
        if (page < 0 || page >= nbPages) {
            throw new IllegalArgumentException("page hors bornes : " + page + " (sur " + nbPages + ")");
        }
    }
}
