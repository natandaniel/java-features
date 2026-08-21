package org.natandaniel.m02_poo.c01_classes_objets.solutions;

/** Solution de référence — méthodes sur une classe Etudiant. */
class Exo01_Etudiant {

    static class Etudiant {
        String nom;
        double note1;
        double note2;
        double note3;
    }

    static double moyenne(Etudiant e) {
        return (e.note1 + e.note2 + e.note3) / 3;
    }

    static boolean estAdmis(Etudiant e) {
        return moyenne(e) >= 10;
    }
}
