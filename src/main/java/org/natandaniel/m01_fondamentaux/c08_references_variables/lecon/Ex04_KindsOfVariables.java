package org.natandaniel.m01_fondamentaux.c08_references_variables.lecon;

/**
 * Leçon 1/3 — Les six « kinds of variables » de la JLS §4.12.1.
 */
class Ex04_KindsOfVariables {

    int champInstance = 42;                       // 1. champ d'instance
    static final String CONSTANTE = "Java";       // 2. champ de classe (static)
    static int[] tableau = {10, 20, 30};          // 3. composant de tableau (chaque case)

    public static void main(String[] args) {      // args : 5. paramètre de méthode
        System.out.println("=== Les catégories de variables (JLS §4.12.1) ===");
        System.out.println("2. champ de classe (static)  : " + CONSTANTE);
        System.out.println("3. composant tableau[1]       : " + tableau[1]);

        Ex04_KindsOfVariables v = new Ex04_KindsOfVariables();
        System.out.println("1. champ d'instance           : " + v.champInstance);

        int variableLocale = 10;                   // 4. variable locale
        System.out.println("4. variable locale            : " + variableLocale);

        try {
            risque();
        } catch (RuntimeException e) {             // e : 6. paramètre d'exception
            System.out.println("6. paramètre d'exception      : " + e.getMessage());
        }
    }

    static void risque() {
        throw new RuntimeException("attrapée dans un catch");
    }
}
