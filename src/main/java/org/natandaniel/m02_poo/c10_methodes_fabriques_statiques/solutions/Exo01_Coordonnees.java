package org.natandaniel.m02_poo.c10_methodes_fabriques_statiques.solutions;

/** Solution de référence — fabriques nommées depuisDegres/depuisRadians. */
class Exo01_Coordonnees {

    static class Coordonnees {
        final double latitudeDegres;
        final double longitudeDegres;

        private Coordonnees(double latitudeDegres, double longitudeDegres) {
            this.latitudeDegres = latitudeDegres;
            this.longitudeDegres = longitudeDegres;
        }

        static Coordonnees depuisDegres(double latitudeDegres, double longitudeDegres) {
            return new Coordonnees(latitudeDegres, longitudeDegres);
        }

        static Coordonnees depuisRadians(double latitudeRadians, double longitudeRadians) {
            return new Coordonnees(
                    Math.toDegrees(latitudeRadians),
                    Math.toDegrees(longitudeRadians));
        }
    }
}
