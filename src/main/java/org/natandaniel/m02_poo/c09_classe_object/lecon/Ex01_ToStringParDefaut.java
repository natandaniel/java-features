package org.natandaniel.m02_poo.c09_classe_object.lecon;

/**
 * Leçon 1/4 — toString() hérité d'Object, sans redéfinition (JLS §4.3.2).
 */
class Ex01_ToStringParDefaut {

    static class Produit {
        String nom;
        double prix;

        Produit(String nom, double prix) {
            this.nom = nom;
            this.prix = prix;
        }
        // Pas de toString() ici : Produit hérite tel quel de Object.toString().
    }

    public static void main(String[] args) {
        Produit aspirateur = new Produit("Aspirateur", 199.0);

        System.out.println("=== toString() par défaut (hérité d'Object) ===");
        System.out.println(aspirateur.toString());
        System.out.println("println(aspirateur) appelle implicitement toString() : " + aspirateur);

        // Le contrat d'Object.toString() (Javadoc java.lang.Object) fixe ce format exact :
        //   getClass().getName() + '@' + Integer.toHexString(hashCode())
        String formatEquivalent = aspirateur.getClass().getName() + "@" + Integer.toHexString(aspirateur.hashCode());
        System.out.println("\n=== Reconstruction manuelle du même format ===");
        System.out.println(formatEquivalent);
        System.out.println("Identique à aspirateur.toString() : " + formatEquivalent.equals(aspirateur.toString()));

        System.out.println("\n=== Pourquoi ce n'est pas exploitable en logs/debug ===");
        System.out.println("Ce format ne révèle ni \"nom\" ni \"prix\" : seule l'identité de l'objet");
        System.out.println("(classe + code de hachage en hexadécimal) est visible, pas son contenu.");
    }
}
