package org.natandaniel.m02_poo.c20_enumerations.solutions;

/** Solution de référence — le coefficient de la constante pondère les points de base. */
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
            return (int) Math.round(pointsBase * coefficient);
        }
    }
}
