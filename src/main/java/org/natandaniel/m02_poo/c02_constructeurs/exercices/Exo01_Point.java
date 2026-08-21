package org.natandaniel.m02_poo.c02_constructeurs.exercices;

/**
 * Exercices — méthodes sur une classe Point (x, y).
 */
class Exo01_Point {

    static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /** Distance euclidienne entre deux points. */
    static double distance(Point a, Point b) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /** Point milieu de deux points (moyenne entière des coordonnées). */
    static Point milieu(Point a, Point b) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
