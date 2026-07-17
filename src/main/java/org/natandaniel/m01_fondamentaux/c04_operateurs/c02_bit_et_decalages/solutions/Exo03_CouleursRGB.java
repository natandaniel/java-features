package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.solutions;

/** Solution de référence 2/2 — Couleurs RVB. */
class Exo03_CouleursRGB {

    static int empaqueter(int rouge, int vert, int bleu) {
        return (rouge << 16) | (vert << 8) | bleu;
    }

    static int rouge(int rgb) {
        return (rgb >>> 16) & 0xFF;
    }

    static int vert(int rgb) {
        return (rgb >>> 8) & 0xFF;
    }

    static int bleu(int rgb) {
        return rgb & 0xFF;
    }
}
