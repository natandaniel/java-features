package org.natandaniel.m02_poo.c08_mot_cle_super.exercices;

/**
 * Exercice — super.champ : accéder explicitement au champ masqué de la superclasse
 * (JLS §15.11.2), inaccessible autrement puisque ce champ-ci porte le même nom.
 */
class Exo01_ChampMasqueParSuper {

    static class Produit {
        String origine = "Origine inconnue";
    }

    static class ProduitImporte extends Produit {
        String origine = "Import direct"; // masque Produit.origine

        /** Origine héritée de Produit (masquée par ce champ-ci), pas celle de ProduitImporte. */
        String origineHeritee() {
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
