package org.natandaniel.m01_fondamentaux.c01_types_primitifs.lecon;

/**
 * Leçon 2/3 — Les valeurs par défaut des champs (JLS §4.12.5).
 *
 * Un CHAMP (de classe ou d'instance) non initialisé reçoit automatiquement une
 * valeur par défaut. Une VARIABLE LOCALE, elle, n'en reçoit pas : le compilateur
 * refuse de la lire avant affectation.
 */
class Ex02_ValeursParDefaut {

    // Champs de classe — initialisés automatiquement à zéro (JLS §4.12.5)
    static byte    champByte;
    static short   champShort;
    static int     champInt;
    static long    champLong;
    static char    champChar;
    static float   champFloat;
    static double  champDouble;
    static boolean champBoolean;

    public static void main(String[] args) {
        System.out.println("=== Valeurs par défaut des champs (JLS §4.12.5) ===");
        System.out.println("byte    → " + champByte);
        System.out.println("short   → " + champShort);
        System.out.println("int     → " + champInt);
        System.out.println("long    → " + champLong);
        System.out.println("char    → (int) " + (int) champChar + "  (caractère nul '\\u0000')");
        System.out.println("float   → " + champFloat);
        System.out.println("double  → " + champDouble);
        System.out.println("boolean → " + champBoolean);

        System.out.println("\n=== Variables locales : PAS de valeur par défaut ===");
        // int x;
        // System.out.println(x); // ERREUR de compilation : variable x might not have been initialized
        int x = 0;  // on est OBLIGÉ d'initialiser avant de lire
        System.out.println("Une locale doit être initialisée explicitement : x = " + x);
    }
}
