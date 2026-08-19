package org.natandaniel.m02_poo.c01_classes_objets.lecon;

/**
 * Leçon 1/4 — définir une classe : champs et méthodes d'instance.
 */
class Ex01_DefinirUneClasse {

    static class Rectangle {
        int largeur;
        int hauteur;

        int aire() {
            return largeur * hauteur;
        }

        int perimetre() {
            return 2 * (largeur + hauteur);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Une classe décrit un modèle, un objet en est une instance ===");
        Rectangle r1 = new Rectangle();
        r1.largeur = 3;
        r1.hauteur = 4;

        Rectangle r2 = new Rectangle();
        r2.largeur = 10;
        r2.hauteur = 2;

        System.out.println("r1 : largeur=" + r1.largeur + ", hauteur=" + r1.hauteur + ", aire=" + r1.aire());
        System.out.println("r2 : largeur=" + r2.largeur + ", hauteur=" + r2.hauteur + ", aire=" + r2.aire());

        System.out.println("\n=== Chaque instance a son propre état ===");
        System.out.println("Modifier r1 ne change pas r2 : r1.largeur=" + r1.largeur + " ; r2.largeur=" + r2.largeur);
    }
}
