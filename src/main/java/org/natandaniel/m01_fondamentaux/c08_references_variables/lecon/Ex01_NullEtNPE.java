package org.natandaniel.m01_fondamentaux.c08_references_variables.lecon;

/**
 * Leçon 1/3 — null : l'absence de référence (JLS §4.3).
 */
class Ex01_NullEtNPE {

    // Un champ de type référence vaut null par défaut (JLS §4.12.5)
    static String champString;
    static int[] champTableau;

    public static void main(String[] args) {
        System.out.println("=== Valeur par défaut d'une référence : null ===");
        System.out.println("champString  = " + champString);
        System.out.println("champTableau = " + champTableau);

        System.out.println("\n=== Primitif vs référence ===");
        System.out.println("Un primitif stocke sa VALEUR. Une référence stocke une ADRESSE vers un objet.");

        System.out.println("\n=== Appeler une méthode sur null → NullPointerException ===");
        String s = null;
        try {
            s.length();
        } catch (NullPointerException e) {
            System.out.println("null.length() → NullPointerException (l'erreur la plus fréquente en Java)");
        }
    }
}
