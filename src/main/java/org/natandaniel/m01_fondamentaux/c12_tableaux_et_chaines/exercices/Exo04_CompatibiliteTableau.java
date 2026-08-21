package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.exercices;

/**
 * Exercices — covariance des tableaux, ArrayStoreException.
 */
class Exo04_CompatibiliteTableau {

    /**
     * Tente de stocker valeur à l'indice index de tableau. Renvoie true si
     * l'affectation a réussi, false si elle a levé ArrayStoreException (le
     * VRAI type d'exécution de tableau n'accepte pas valeur, même si tableau
     * est vu comme un Object[] plus large à la compilation).
     */
    static boolean peutStocker(Object[] tableau, int index, Object valeur) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
