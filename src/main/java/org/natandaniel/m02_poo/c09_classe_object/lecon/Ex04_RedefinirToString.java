package org.natandaniel.m02_poo.c09_classe_object.lecon;

/**
 * Leçon 4/4 — redéfinir toString() pour une représentation lisible (Javadoc java.lang.Object :
 * "It is recommended that all subclasses override this method.").
 */
class Ex04_RedefinirToString {

    static class Produit {
        String reference;
        String nom;
        double prix;

        Produit(String reference, String nom, double prix) {
            this.reference = reference;
            this.nom = nom;
            this.prix = prix;
        }

        @Override
        public String toString() {
            return "Produit[reference=" + reference + ", nom=" + nom + ", prix=" + prix + "]";
        }
    }

    public static void main(String[] args) {
        Produit aspirateur = new Produit("P-001", "Aspirateur", 199.0);

        System.out.println("=== toString() redéfini : lisible en logs/debug ===");
        System.out.println(aspirateur); // appelle implicitement toString()

        System.out.println("\n=== Comparaison avec le format par défaut d'Object ===");
        String formatParDefaut = aspirateur.getClass().getName() + "@" + Integer.toHexString(aspirateur.hashCode());
        System.out.println("Object.toString()  aurait donné : " + formatParDefaut);
        System.out.println("Produit.toString() donne         : " + aspirateur);
        System.out.println("Même sans connaître la classe à l'avance (ex. dans un log d'erreur générique),");
        System.out.println("cette redéfinition expose le contenu utile — pas seulement l'identité de l'objet.");
    }
}
