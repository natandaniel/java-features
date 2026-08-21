package org.natandaniel.m01_fondamentaux.c13_methodes.lecon;

/**
 * Leçon 3/5 — le résultat d'une méthode (JLS §8.4.5, §8.4.7).
 *
 * `void` signale l'absence de valeur de retour. Un type de retour non-void
 * impose une règle stricte : le corps ne doit jamais pouvoir "tomber en
 * bas" sans avoir rencontré un `return` — sinon erreur de compilation
 * (§8.4.7 : "a compile-time error occurs if the body of the method can
 * complete normally"). Avoir un type de retour n'oblige pas forcément à un
 * `return` explicite dans chaque branche visible : une méthode qui lève
 * systématiquement une exception satisfait aussi la règle, puisqu'elle ne
 * "complète" jamais normalement.
 */
class Ex03_ValeurDeRetour {

    static void afficherAccueil(String prenom) {
        System.out.println("Bonjour " + prenom); // void : aucun `return valeur` possible
    }

    static String classifierAge(int age) {
        if (age < 18) {
            return "mineur";
        } else {
            return "majeur";
        }
        // pas de `return` après ce bloc if/else : le compilateur voit que TOUS les
        // chemins renvoient une valeur, donc le corps ne peut jamais "tomber en bas".
    }

    // Ne compilerait pas si on retirait le `else` : sans lui, le compilateur ne peut
    // pas prouver que le corps renvoie systématiquement une valeur (même si, dans les
    // faits, `age >= 18` couvre déjà tout ce qui n'est pas `age < 18`) :
    //
    // static String classifierAgeSansElse(int age) {
    //     if (age < 18) {
    //         return "mineur";
    //     }
    //     // erreur de compilation ici : "missing return statement"
    // }

    static String echouerToujours() {
        throw new IllegalStateException("jamais implémenté");
        // pas de `return` du tout, et ça compile quand même : lever une exception
        // empêche le corps de "compléter normalement", la règle §8.4.7 est respectée.
    }

    public static void main(String[] args) {
        afficherAccueil("Camille");
        System.out.println("classifierAge(15) = " + classifierAge(15));
        System.out.println("classifierAge(30) = " + classifierAge(30));

        System.out.println("\n=== une méthode à type de retour peut ne jamais contenir de `return` ===");
        try {
            echouerToujours();
        } catch (IllegalStateException e) {
            System.out.println("echouerToujours() a levé : " + e.getMessage());
        }
    }
}
