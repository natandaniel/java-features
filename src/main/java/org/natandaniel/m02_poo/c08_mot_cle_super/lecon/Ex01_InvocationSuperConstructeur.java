package org.natandaniel.m02_poo.c08_mot_cle_super.lecon;

/**
 * Leçon 1/3 — invocation du constructeur de la superclasse, explicite ou implicite
 * (JLS §8.8.7, §8.8.7.1).
 */
class Ex01_InvocationSuperConstructeur {

    static class Produit {
        String nom;

        Produit() {
            nom = "Produit générique";
            System.out.println("Produit() : nom initialisé à \"" + nom + "\"");
        }
    }

    // Aucune invocation explicite dans le corps : le compilateur insère implicitement
    // "super();" en première instruction (JLS §8.8.7) — silencieusement, car Produit a un
    // constructeur sans argument accessible.
    static class ProduitEnPromotion extends Produit {
        ProduitEnPromotion() {
            // implicite ici : super();
            System.out.println("ProduitEnPromotion() : superclasse déjà construite, nom = " + nom);
        }
    }

    static class ProduitPhysique {
        String nom;
        double prix;

        ProduitPhysique(String nom, double prix) {
            this.nom = nom;
            this.prix = prix;
        }
        // Pas de constructeur sans argument ici : l'insertion implicite de "super()" dans
        // une éventuelle sous-classe échouerait à la compilation.
    }

    // super(...) explicite, avec arguments : obligatoire ici, car ProduitPhysique n'a pas de
    // constructeur sans argument pour une insertion implicite.
    static class ProduitPhysiqueEnPromotion extends ProduitPhysique {
        double pourcentageRemise;

        ProduitPhysiqueEnPromotion(String nom, double prix, double pourcentageRemise) {
            super(nom, prix); // doit être la première instruction du corps (comme this(...), JLS §8.8.7.1)
            this.pourcentageRemise = pourcentageRemise;
        }

        // static class SansSuper extends ProduitPhysique { SansSuper() { } }
        // ERREUR DE COMPILATION : insertion implicite de "super()" impossible, ProduitPhysique
        // n'a pas de constructeur sans argument.
    }

    public static void main(String[] args) {
        System.out.println("=== super() implicite ===");
        new ProduitEnPromotion();

        System.out.println("\n=== super(...) explicite, avec arguments ===");
        ProduitPhysiqueEnPromotion promo = new ProduitPhysiqueEnPromotion("Aspirateur", 199.0, 15.0);
        System.out.println("nom = " + promo.nom + ", prix = " + promo.prix
                + ", remise = " + promo.pourcentageRemise + "%");

        System.out.println("\n=== this(...) et super(...) sont mutuellement exclusifs ===");
        System.out.println("Un constructeur contient au plus UNE invocation explicite (this(...) OU");
        System.out.println("super(...)), jamais les deux : la grammaire ne l'autorise pas (JLS §8.8.7).");
    }
}
