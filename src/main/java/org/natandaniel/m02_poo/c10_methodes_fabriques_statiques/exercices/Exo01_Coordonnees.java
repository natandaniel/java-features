package org.natandaniel.m02_poo.c10_methodes_fabriques_statiques.exercices;

/**
 * Exercice — deux fabriques statiques nommées pour un même point GPS, l'une depuis des degrés,
 * l'autre depuis des radians (impossible à distinguer avec deux constructeurs
 * Coordonnees(double, double) : signatures identiques).
 */
class Exo01_Coordonnees {

    static class Coordonnees {
        final double latitudeDegres;
        final double longitudeDegres;

        private Coordonnees(double latitudeDegres, double longitudeDegres) {
            this.latitudeDegres = latitudeDegres;
            this.longitudeDegres = longitudeDegres;
        }

        /** Construit des coordonnées directement à partir de degrés. */
        static Coordonnees depuisDegres(double latitudeDegres, double longitudeDegres) {
            throw new UnsupportedOperationException("À implémenter");
        }

        /** Construit des coordonnées à partir de radians, convertis en degrés en interne. */
        static Coordonnees depuisRadians(double latitudeRadians, double longitudeRadians) {
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
