package org.natandaniel.m02_poo.c01_classes_objets.exercices;

/**
 * Exercices — méthodes sur une classe Etudiant (champs publics, pas de constructeur explicite).
 */
class Exo01_Etudiant {

    static class Etudiant {
        String nom;
        double note1;
        double note2;
        double note3;
    }

    /** Moyenne des trois notes. */
    static double moyenne(Etudiant e) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /** Admis si la moyenne est au moins égale à 10. */
    static boolean estAdmis(Etudiant e) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
