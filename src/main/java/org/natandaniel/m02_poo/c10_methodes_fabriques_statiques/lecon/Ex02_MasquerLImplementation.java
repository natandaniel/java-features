package org.natandaniel.m02_poo.c10_methodes_fabriques_statiques.lecon;

/**
 * Leçon 2/3 — masquer l'implémentation : une fabrique statique peut renvoyer un type plus large
 * que la classe qu'elle construit réellement (type de retour déclaré, JLS §8.4.5). Un constructeur
 * ne le peut jamais : "new Cercle(...)" nomme et expose forcément Cercle à l'appelant.
 */
class Ex02_MasquerLImplementation {

    static class Forme {
        double aire() {
            return 0.0;
        }
    }

    // private : invisible hors de ce fichier, seule la fabrique Formes.cercle(...) y a accès.
    private static class Cercle extends Forme {
        double rayon;

        Cercle(double rayon) {
            this.rayon = rayon;
        }

        @Override
        double aire() {
            return Math.PI * rayon * rayon;
        }
    }

    static class Formes {
        // Le type de retour déclaré est Forme, pas Cercle : l'appelant ne manipule jamais
        // le nom Cercle, seulement la forme qu'il obtient.
        static Forme cercle(double rayon) {
            return new Cercle(rayon);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== L'appelant ne voit que Forme, jamais Cercle ===");
        Forme disque = Formes.cercle(3.0);
        System.out.println("disque.aire() = " + disque.aire());

        System.out.println("\n=== Cercle reste invisible en dehors de ce fichier ===");
        System.out.println("Formes.cercle(...) est déclarée 'static Forme cercle(...)' : le nom");
        System.out.println("Cercle ne peut apparaître dans aucun code appelant.");
    }
}
