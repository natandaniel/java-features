package org.natandaniel.m02_poo.c09_classe_object.lecon;

import java.util.HashSet;
import java.util.Set;

/**
 * Leçon 2/4 — equals() hérité d'Object : comparaison de référence, pas de contenu (JLS §4.3.2 ;
 * contrat détaillé dans le Javadoc java.lang.Object). Prolonge c08_references_variables (m01),
 * qui établit déjà que "==" compare des références — ici, Object.equals() fait exactement "==".
 */
class Ex02_EqualsParDefaut {

    static class Produit {
        String nom;
        double prix;

        Produit(String nom, double prix) {
            this.nom = nom;
            this.prix = prix;
        }
        // Pas d'equals() ici : Produit hérite tel quel de Object.equals().
    }

    public static void main(String[] args) {
        Produit p1 = new Produit("Aspirateur", 199.0);
        Produit p2 = new Produit("Aspirateur", 199.0); // mêmes valeurs de champs, autre instance

        System.out.println("=== equals() par défaut == comparaison de référence ===");
        System.out.println("p1.equals(p2) = " + p1.equals(p2) + " (mêmes nom/prix, mais deux objets distincts)");
        System.out.println("p1 == p2      = " + (p1 == p2));
        System.out.println("p1.equals(p1) = " + p1.equals(p1) + " (une référence est toujours égale à elle-même)");

        System.out.println("\n=== Conséquence concrète : une collection basée sur equals()/hashCode() ===");
        Set<Produit> produits = new HashSet<>();
        produits.add(p1);
        produits.add(p2);
        System.out.println("produits.size() = " + produits.size()
                + " (2, alors que p1 et p2 se ressemblent \"en contenu\")");
        System.out.println("Sans equals()/hashCode() redéfinis, un HashSet ne peut détecter aucun doublon");
        System.out.println("de contenu — il ne connaît que l'identité de référence.");
    }
}
