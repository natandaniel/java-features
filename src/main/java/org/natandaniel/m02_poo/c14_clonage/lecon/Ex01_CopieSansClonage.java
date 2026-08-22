package org.natandaniel.m02_poo.c14_clonage.lecon;

/**
 * Leçon 1/4 — {@code Object.clone()} est {@code protected} : aucun code extérieur à la classe
 * ne peut l'invoquer directement (même une sous-classe doit le republier pour l'exposer). Et
 * même republié, l'appeler sur une classe qui n'implémente pas {@code Cloneable} échoue à
 * l'exécution avec {@code CloneNotSupportedException} — Javadoc {@code Object#clone()} : "if
 * the class of this object does not implement the interface Cloneable, then a
 * CloneNotSupportedException is thrown".
 */
class Ex01_CopieSansClonage {

    static class Commande {
        private final String reference;

        Commande(String reference) {
            this.reference = reference;
        }

        String reference() {
            return reference;
        }

        // Republie clone() en public, mais Commande n'implémente PAS Cloneable : la condition
        // posée par Object.clone() s'applique malgré la republication.
        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    // Sans même republier clone(), ce code ne compile pas : la méthode héritée reste protected.
    //
    // Commande commande = new Commande("CMD-001");
    // commande.clone(); // error: clone() has protected access in Object

    public static void main(String[] args) {
        Commande commande = new Commande("CMD-001");

        System.out.println("=== Commande ne déclare pas 'implements Cloneable' ===");
        try {
            commande.clone();
            System.out.println("clone() a réussi (ne devrait pas arriver ici)");
        } catch (CloneNotSupportedException e) {
            System.out.println("clone() échoue : " + e.getClass().getSimpleName()
                    + " — Commande n'implémente pas Cloneable");
        }
    }
}
