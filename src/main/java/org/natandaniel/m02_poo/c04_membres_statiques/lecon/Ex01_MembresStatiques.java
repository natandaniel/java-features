package org.natandaniel.m02_poo.c04_membres_statiques.lecon;

/**
 * static : membres partagés par la classe, pas par instance.
 */
class Ex01_MembresStatiques {

    static class Rectangle {
        int largeur;
        int hauteur;

        // static : UN seul exemplaire, partagé par toutes les instances (pas un par objet).
        static int nombreCrees = 0;

        Rectangle(int largeur, int hauteur) {
            this.largeur = largeur;
            this.hauteur = hauteur;
            nombreCrees++;
        }

        // Méthode static : pas d'accès à this, seulement aux membres static.
        static int nombreCrees() {
            return nombreCrees;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Un champ static est partagé, pas dupliqué par instance ===");
        System.out.println("Avant toute création : " + Rectangle.nombreCrees());

        Rectangle r1 = new Rectangle(3, 4);
        Rectangle r2 = new Rectangle(10, 2);
        System.out.println("Après 2 créations : " + Rectangle.nombreCrees());
        System.out.println("r1.nombreCrees = " + r1.nombreCrees + " (même valeur vue depuis n'importe quelle instance)");

        System.out.println("\n=== On accède au static via la classe, pas besoin d'instance ===");
        System.out.println("Rectangle.nombreCrees() = " + Rectangle.nombreCrees());
    }
}
