package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.solutions;

/** Solution de référence 1/2 — Décalages de base. */
class Exo02_Decalages {

    static int multiplierParPuissanceDe2(int x, int k) {
        return x << k;
    }

    static int diviserParPuissanceDe2(int x, int k) {
        return x >> k;
    }

    static boolean estPuissanceDe2(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    static int extraireOctet(int valeur, int index) {
        return (valeur >>> (index * 8)) & 0xFF;
    }
}
