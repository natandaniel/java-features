package org.natandaniel.m02_poo.c08_mot_cle_super.lecon;

/**
 * Leçon 3/3 — ordre d'exécution : le constructeur de la superclasse s'exécute intégralement
 * avant les initialiseurs d'instance/de champ de la sous-classe, eux-mêmes avant le reste de
 * son propre corps (JLS §8.8.7.1, point 3).
 */
class Ex03_OrdreExecution {

    static class Produit {
        Produit() {
            System.out.println("1. Produit() : corps du constructeur de la superclasse");
        }
    }

    static class ProduitPhysique extends Produit {
        int poidsGrammes = initialiserPoids(); // initialiseur de champ de la sous-classe

        ProduitPhysique() {
            super(); // implicite si omis ; explicite ici pour rendre l'ordre lisible
            System.out.println("3. ProduitPhysique() : reste du corps du constructeur (épilogue)");
        }

        static int initialiserPoids() {
            System.out.println("2. initialiseur du champ poidsGrammes de ProduitPhysique");
            return 500;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Ordre : superclasse -> initialiseurs de la sous-classe -> épilogue ===");
        new ProduitPhysique();
        System.out.println("\nCet ordre vaut aussi quand super() est implicite (omis) : l'insertion");
        System.out.println("implicite ne change rien à la séquence, seulement à ce qui est écrit.");
    }
}
