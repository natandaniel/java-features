package org.natandaniel.m01_fondamentaux.c13_methodes.lecon;

/**
 * Leçon 4/5 — paramètre à arité variable / varargs (JLS §8.4.1, @since Java 5).
 *
 * Un paramètre suivi de `...` accepte un nombre quelconque d'arguments (y
 * compris zéro) : à l'intérieur de la méthode, il est traité comme un
 * tableau ordinaire. Au plus un paramètre varargs par méthode, et
 * obligatoirement en DERNIÈRE position — sinon erreur de compilation.
 */
class Ex04_ParametresVariables {

    static int somme(int... nombres) {
        int total = 0;
        for (int nombre : nombres) { // nombres est un int[] ordinaire ici
            total += nombre;
        }
        return total;
    }

    // un varargs peut être précédé d'autres paramètres, jamais suivi d'un autre
    static String prefixerTous(String prefixe, String... mots) {
        StringBuilder resultat = new StringBuilder();
        for (String mot : mots) {
            resultat.append(prefixe).append(mot).append(" ");
        }
        return resultat.toString().strip();
    }

    public static void main(String[] args) {
        System.out.println("=== varargs accepte zéro, un ou plusieurs arguments ===");
        System.out.println("somme() = " + somme());
        System.out.println("somme(5) = " + somme(5));
        System.out.println("somme(1, 2, 3, 4) = " + somme(1, 2, 3, 4));

        System.out.println("\n=== équivalent à passer directement un tableau ===");
        int[] valeurs = {10, 20, 30};
        System.out.println("somme(valeurs) = " + somme(valeurs));

        System.out.println("\n=== un varargs peut suivre d'autres paramètres, jamais les précéder ===");
        System.out.println(prefixerTous(">> ", "un", "deux", "trois"));
    }
}
