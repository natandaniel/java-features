package org.natandaniel.m02_poo.c04_polymorphisme.exercices;

import java.util.List;

/**
 * Exercices — polymorphisme : etiquette() est redéfinie dans chaque sous-classe ; l'appeler via
 * une référence Produit doit dispatcher sur la bonne version sans que l'appelant le sache.
 */
class Exo01_CatalogueMixte {

    static class Produit {
        String nom;
        double prix;

        Produit(String nom, double prix) {
            this.nom = nom;
            this.prix = prix;
        }

        String etiquette() {
            return nom + " — " + prix + " €";
        }
    }

    static class ProduitPhysique extends Produit {
        int poidsGrammes;

        ProduitPhysique(String nom, double prix, int poidsGrammes) {
            super(nom, prix);
            this.poidsGrammes = poidsGrammes;
        }

        @Override
        String etiquette() {
            return nom + " — " + prix + " € — " + poidsGrammes + " g";
        }
    }

    static class ProduitNumerique extends Produit {
        long tailleOctets;

        ProduitNumerique(String nom, double prix, long tailleOctets) {
            super(nom, prix);
            this.tailleOctets = tailleOctets;
        }

        @Override
        String etiquette() {
            return nom + " — " + prix + " € — téléchargement " + (tailleOctets / 1_000_000) + " Mo";
        }
    }

    /** Étiquette de chaque produit de la liste, dans l'ordre, via l'appel polymorphe à etiquette(). */
    static List<String> etiquettes(List<Produit> produits) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
