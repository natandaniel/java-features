package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.solutions;

/** Solution de référence — Manipulation de bits par masque. */
class Exo01_Masques {

    static int activer(int flags, int position) {
        return flags | (1 << position);
    }

    static int desactiver(int flags, int position) {
        return flags & ~(1 << position);
    }

    static int basculer(int flags, int position) {
        return flags ^ (1 << position);
    }

    static boolean estActif(int flags, int position) {
        return (flags & (1 << position)) != 0;
    }
}
