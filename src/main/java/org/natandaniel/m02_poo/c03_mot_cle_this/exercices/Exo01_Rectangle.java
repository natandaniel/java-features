package org.natandaniel.m02_poo.c03_mot_cle_this.exercices;

/**
 * Exercices — méthodes d'instance et constructeur chaîné sur un Rectangle.
 */
class Exo01_Rectangle {

    static class Rectangle {
        final int largeur;
        final int hauteur;

        Rectangle(int largeur, int hauteur) {
            this.largeur = largeur;
            this.hauteur = hauteur;
        }

        /** Constructeur de commodité : carré (délègue à l'autre constructeur via this(...)). */
        Rectangle(int cote) {
            this(cote, cote);
        }

        int aire() {
            throw new UnsupportedOperationException("À implémenter");
        }

        int perimetre() {
            throw new UnsupportedOperationException("À implémenter");
        }

        boolean estCarre() {
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
