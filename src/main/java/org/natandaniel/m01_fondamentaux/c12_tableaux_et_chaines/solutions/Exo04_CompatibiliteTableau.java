package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.solutions;

/** Solution de référence — covariance des tableaux, ArrayStoreException. */
class Exo04_CompatibiliteTableau {

    static boolean peutStocker(Object[] tableau, int index, Object valeur) {
        try {
            tableau[index] = valeur;
            return true;
        } catch (ArrayStoreException e) {
            return false;
        }
    }
}
