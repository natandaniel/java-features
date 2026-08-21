package org.natandaniel.m02_poo.c08_mot_cle_super.lecon;

/**
 * Leçon 2/3 — super.champ (accès à un champ masqué) et super.methode() (extension d'une
 * méthode redéfinie), JLS §15.11.2 et §15.12.1.
 */
class Ex02_AccesSuperMembre {

    static class Produit {
        String nom = "Produit générique";

        String etiquette() {
            return "Produit : " + nom;
        }
    }

    static class ProduitImporte extends Produit {
        String nom = "Produit importé"; // masque Produit.nom, ne le redéfinit pas (ce n'est pas une méthode)

        @Override
        String etiquette() {
            // super.etiquette() : appelle la version de Produit — dont le "nom" interne à ce
            // corps désigne Produit.nom (résolution lexicale, JLS §6.5.6.1), pas celui-ci.
            String etiquetteHeritee = super.etiquette();
            // Étend le comportement hérité plutôt que de le dupliquer : impossible de reproduire
            // ça pour un champ, qui n'a pas de dispatch dynamique à contourner.
            return etiquetteHeritee + " (réétiqueté : " + nom + ")";
        }

        String champMasqueDeLaSuperclasse() {
            return super.nom; // accès explicite au champ masqué, sans passer par une méthode
        }
    }

    public static void main(String[] args) {
        ProduitImporte importe = new ProduitImporte();

        System.out.println("=== super.champ : accès explicite à un champ masqué ===");
        System.out.println("importe.nom (masquant) = " + importe.nom);
        System.out.println("importe.champMasqueDeLaSuperclasse() (super.nom) = "
                + importe.champMasqueDeLaSuperclasse());

        System.out.println("\n=== super.methode() : étendre plutôt que remplacer ===");
        System.out.println("importe.etiquette() = " + importe.etiquette());
        System.out.println("La version de Produit.etiquette() a été APPELÉE (via super.etiquette()),");
        System.out.println("pas dupliquée : si Produit.etiquette() change, ProduitImporte en profite.");
    }
}
