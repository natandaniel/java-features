package org.natandaniel.m02_poo.c07_polymorphisme.solutions;

import java.util.ArrayList;
import java.util.List;

/** Solution de référence — dispatch polymorphe de etiquette() sur une liste mixte. */
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

    static List<String> etiquettes(List<Produit> produits) {
        List<String> resultat = new ArrayList<>();
        for (Produit produit : produits) {
            resultat.add(produit.etiquette());
        }
        return resultat;
    }
}
