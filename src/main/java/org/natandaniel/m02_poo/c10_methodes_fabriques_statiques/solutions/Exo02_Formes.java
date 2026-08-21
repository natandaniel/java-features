package org.natandaniel.m02_poo.c10_methodes_fabriques_statiques.solutions;

/** Solution de référence — Cercle reste private, seule Forme est exposée par la fabrique. */
class Exo02_Formes {

    static class Forme {
        double aire() {
            return 0.0;
        }
    }

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

    static Forme cercle(double rayon) {
        return new Cercle(rayon);
    }
}
