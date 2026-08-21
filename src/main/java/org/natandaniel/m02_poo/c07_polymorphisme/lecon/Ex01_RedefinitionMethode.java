package org.natandaniel.m02_poo.c07_polymorphisme.lecon;

/**
 * Leçon 1/3 — les règles de la redéfinition de méthode (JLS §8.4.8.1, §8.4.8.3).
 */
class Ex01_RedefinitionMethode {

    static class Produit {
        String nom;
        double prix;

        Produit(String nom, double prix) {
            this.nom = nom;
            this.prix = prix;
        }

        // Le point de personnalisation : chaque sous-classe pourra en donner sa propre version.
        String description() {
            return nom + " (" + prix + " €)";
        }

        // Retour du type de base ; une sous-classe pourra restreindre ce type de retour
        // (retour covariant, §8.4.8.3, légal depuis Java SE 5.0).
        Produit appliquerRemise(double pourcentage) {
            return new Produit(nom, prix * (1 - pourcentage / 100));
        }
    }

    static class ProduitPhysique extends Produit {
        int poidsGrammes;

        ProduitPhysique(String nom, double prix, int poidsGrammes) {
            super(nom, prix);
            this.poidsGrammes = poidsGrammes;
        }

        // @Override est facultative (elle existe depuis Java 5, §9.6.4.4) mais fait détecter par
        // le compilateur toute signature qui ne correspondrait pas vraiment à un override.
        @Override
        String description() {
            return nom + " (" + prix + " €) — " + poidsGrammes + " g";
        }

        // Retour covariant : ProduitPhysique restreint le type de retour à ProduitPhysique,
        // sous-type de Produit — légal tant que le type de retour reste substituable (§8.4.8.3).
        @Override
        ProduitPhysique appliquerRemise(double pourcentage) {
            return new ProduitPhysique(nom, prix * (1 - pourcentage / 100), poidsGrammes);
        }
    }

    public static void main(String[] args) {
        Produit generique = new Produit("Carte cadeau", 20.00);
        ProduitPhysique physique = new ProduitPhysique("Roman broché", 14.90, 400);

        System.out.println("=== Redéfinition : signature identique, comportement propre ===");
        System.out.println("generique.description() = " + generique.description());
        System.out.println("physique.description()  = " + physique.description());

        System.out.println("\n=== Retour covariant (depuis Java 5.0) ===");
        System.out.println("appliquerRemise déclare 'Produit' dans Produit, mais 'ProduitPhysique' dans");
        System.out.println("ProduitPhysique : le type de retour peut être restreint, jamais élargi.");
        ProduitPhysique remise = physique.appliquerRemise(10); // pas besoin de cast : le type de retour est déjà ProduitPhysique
        System.out.println("physique.appliquerRemise(10).poidsGrammes = " + remise.poidsGrammes);

        System.out.println("\n=== Ce que le compilateur interdit (JLS §8.4.8.3) ===");
        System.out.println("Visibilité : une redéfinition ne peut pas RÉDUIRE la visibilité de la méthode redéfinie.");
        // class ProduitPhysique extends Produit {
        //     private String description() { ... } // ERREUR si description() est package/public dans Produit
        // }
        System.out.println("Exceptions : une redéfinition ne peut pas déclarer PLUS d'exceptions vérifiées.");
        // class ProduitPhysique extends Produit {
        //     String description() throws java.io.IOException { ... } // ERREUR : Produit.description() n'en déclare aucune
        // }
        System.out.println("private : une méthode private n'est JAMAIS redéfinie, même par une signature identique");
        System.out.println("(elle n'est pas héritée — la sous-classe déclare une méthode sans rapport).");
    }
}
