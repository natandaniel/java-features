package org.natandaniel.m02_poo.c20_enumerations.exercices;

/**
 * Exercice — {@code pointsObtenus(int pointsBase)} est à écrire : elle renvoie
 * {@code pointsBase} multiplié par le {@code coefficient} de la constante, arrondi à l'entier le
 * plus proche ({@link Math#round(double)}).
 */
class Exo02_ConstructeurEtChampParConstante {

    enum NiveauDifficulte {
        DEBUTANT(1.0),
        INTERMEDIAIRE(1.5),
        AVANCE(2.0);

        NiveauDifficulte(double coefficient) {
            this.coefficient = coefficient;
        }

        private final double coefficient;

        double coefficient() {
            return coefficient;
        }

        int pointsObtenus(int pointsBase) {
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
