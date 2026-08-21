package org.natandaniel.m02_poo.c10_methodes_fabriques_statiques.exercices;

/**
 * Exercice — fabrique statique qui renvoie un type plus large (Forme) que celui qu'elle
 * construit réellement (Cercle), sans jamais exposer Cercle à l'appelant.
 */
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

    /** Renvoie une Forme circulaire de rayon donné — le type de retour reste Forme, pas Cercle. */
    static Forme cercle(double rayon) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
