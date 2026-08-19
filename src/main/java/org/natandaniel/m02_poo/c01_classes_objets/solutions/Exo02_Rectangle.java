package org.natandaniel.m02_poo.c01_classes_objets.solutions;

/** Solution de référence — méthodes d'instance et constructeur chaîné sur un Rectangle. */
class Exo02_Rectangle {

    static class Rectangle {
        final int largeur;
        final int hauteur;

        Rectangle(int largeur, int hauteur) {
            this.largeur = largeur;
            this.hauteur = hauteur;
        }

        Rectangle(int cote) {
            this(cote, cote);
        }

        int aire() {
            return largeur * hauteur;
        }

        int perimetre() {
            return 2 * (largeur + hauteur);
        }

        boolean estCarre() {
            return largeur == hauteur;
        }
    }
}
