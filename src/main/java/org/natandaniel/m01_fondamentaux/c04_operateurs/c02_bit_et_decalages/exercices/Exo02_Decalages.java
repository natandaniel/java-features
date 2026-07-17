package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.exercices;

/**
 * Exercices 1/2 — Décalages de base.
 *
 * Utilise les opérateurs de décalage (pas *, /, %, Math.pow). Vérifie-toi avec la solution.
 */
class Exo02_Decalages {

    /** Renvoie x × 2^k en utilisant un décalage. */
    static int multiplierParPuissanceDe2(int x, int k) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /** Renvoie x / 2^k (division entière, signe conservé) en utilisant un décalage. */
    static int diviserParPuissanceDe2(int x, int k) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /** Indique si n est une puissance de 2 strictement positive (1, 2, 4, 8, ...). */
    static boolean estPuissanceDe2(int n) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Extrait l'octet n° `index` (0 = octet de poids faible) d'un int, comme valeur 0..255.
     * Indice : décaler de index*8 bits puis masquer avec 0xFF. Attention au signe → utiliser >>>.
     */
    static int extraireOctet(int valeur, int index) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
