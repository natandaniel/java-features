package org.natandaniel.m02_poo.c01_classes_objets.solutions;

/** Solution de référence — méthodes sur une classe Point. */
class Exo01_Point {

    static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static double distance(Point a, Point b) {
        int dx = a.x - b.x;
        int dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    static Point milieu(Point a, Point b) {
        return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
    }
}
