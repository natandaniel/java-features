package org.natandaniel.m01_fondamentaux.c08_references_variables.lecon;

/**
 * Leçon 3/3 — Le String pool : les littéraux sont internés.
 */
class Ex03_StringPool {

    public static void main(String[] args) {
        System.out.println("=== Les littéraux identiques partagent le même objet (pool) ===");
        String x = "hello";
        String y = "hello";
        System.out.println("\"hello\" == \"hello\"            → " + (x == y) + "  (même objet du pool)");

        System.out.println("\n=== new String crée un objet HORS du pool ===");
        String z = new String("hello");
        System.out.println("new String(\"hello\") == \"hello\" → " + (z == x) + "  (objet distinct)");
        System.out.println("z.intern() == \"hello\"          → " + (z.intern() == x) + "  (intern() ramène dans le pool)");

        System.out.println("\n=== getClass() : type réel de l'objet référencé ===");
        Object obj = "une chaîne";
        System.out.println("obj.getClass().getName() = " + obj.getClass().getName());
    }
}
