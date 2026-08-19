package org.natandaniel.m02_poo.c01_classes_objets.lecon;

/**
 * Leçon 3/4 — this : désigner l'objet courant, chaîner les constructeurs.
 */
class Ex03_This {

    static class Rectangle {
        int largeur;
        int hauteur;

        // Les paramètres masquent les champs de même nom : this.largeur désigne le champ,
        // largeur (sans this) désigne le paramètre.
        Rectangle(int largeur, int hauteur) {
            this.largeur = largeur;
            this.hauteur = hauteur;
        }

        // this(...) appelle un AUTRE constructeur de la même classe : évite de dupliquer
        // l'initialisation. Doit être la première instruction du constructeur.
        Rectangle(int cote) {
            this(cote, cote);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== this.champ vs paramètre de même nom ===");
        Rectangle r = new Rectangle(3, 4);
        System.out.println("r : largeur=" + r.largeur + ", hauteur=" + r.hauteur);

        System.out.println("\n=== this(...) délègue à un autre constructeur ===");
        Rectangle carre = new Rectangle(5);
        System.out.println("carre : largeur=" + carre.largeur + ", hauteur=" + carre.hauteur);
    }
}
