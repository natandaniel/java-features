package org.natandaniel.m02_poo.c01_classes_objets.lecon;

/**
 * Leçon 2/4 — constructeurs : initialiser un objet à la création.
 */
class Ex02_Constructeurs {

    static class Rectangle {
        int largeur;
        int hauteur;

        // Constructeur explicite : dès qu'on en écrit un, le constructeur sans argument
        // implicite (celui que fournirait le compilateur par défaut) disparaît.
        Rectangle(int largeur, int hauteur) {
            this.largeur = largeur;
            this.hauteur = hauteur;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Le constructeur initialise l'objet à sa création ===");
        Rectangle r = new Rectangle(5, 8);
        System.out.println("r : largeur=" + r.largeur + ", hauteur=" + r.hauteur);

        System.out.println("\n=== Dès qu'un constructeur est écrit, plus de constructeur sans argument implicite ===");
        // Rectangle vide = new Rectangle(); // ERREUR : le seul constructeur disponible prend 2 arguments
        System.out.println("'new Rectangle()' ne compilerait plus : le seul constructeur disponible prend 2 arguments.");
    }
}
