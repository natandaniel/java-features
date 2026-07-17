package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.exercices;

/**
 * Exercices 2/2 — Empaqueter/décomposer une couleur RVB.
 *
 * Une couleur tient dans un int au format 0x00RRGGBB : rouge sur les bits 16-23,
 * vert sur 8-15, bleu sur 0-7. Compose avec {@code <<} et {@code |}, décompose avec
 * {@code >>>} et {@code & 0xFF}.
 */
class Exo03_CouleursRGB {

    /** Empaquette trois composantes 0..255 en un entier 0x00RRGGBB. */
    static int empaqueter(int rouge, int vert, int bleu) {
        throw new UnsupportedOperationException("À implémenter");
    }

    static int rouge(int rgb) {
        throw new UnsupportedOperationException("À implémenter");
    }

    static int vert(int rgb) {
        throw new UnsupportedOperationException("À implémenter");
    }

    static int bleu(int rgb) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
