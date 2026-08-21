package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.solutions;

/** Solution de référence — StringBuilder. */
class Exo03_FormaterListe {

    static String formaterAvecSeparateur(String[] mots, String separateur) {
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < mots.length; i++) {
            if (i > 0) {
                resultat.append(separateur);
            }
            resultat.append(mots[i]);
        }
        return resultat.toString();
    }
}
