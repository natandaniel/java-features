package org.natandaniel.m01_fondamentaux.c07_references.solutions;

/** Solution de référence — Égalité de références. */
class Exo01_Egalite {

    static boolean memeContenu(String a, String b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);   // a n'est pas null ici : pas de NPE
    }

    static boolean memeObjet(String a, String b) {
        return a == b;
    }
}
