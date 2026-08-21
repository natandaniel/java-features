package org.natandaniel.m01_fondamentaux.c11_structures_controle.lecon;

/**
 * Leçon 1/7 — if, if-else, chaînage else-if (JLS §14.9).
 *
 * `if` évalue une condition booléenne : si elle est vraie, le bloc s'exécute ;
 * sinon, il est sauté. `else` couvre le cas contraire. Un enchaînement `else if`
 * n'est pas une syntaxe à part : c'est un `if` imbriqué dans le `else` du précédent.
 */
class Ex01_IfElse {

    public static void main(String[] args) {
        System.out.println("=== if simple ===");
        int temperature = 3;
        if (temperature < 0) {
            System.out.println(temperature + "°C : gel");
        }
        System.out.println("(rien ne s'affiche au-dessus si la condition est fausse)");

        System.out.println("\n=== if-else ===");
        int age = 15;
        if (age >= 18) {
            System.out.println(age + " ans : majeur");
        } else {
            System.out.println(age + " ans : mineur");
        }

        System.out.println("\n=== chaînage else-if : un if imbriqué dans chaque else ===");
        // Chaque `else if` est évalué seulement si tous les précédents ont échoué.
        // L'ordre compte : la première condition vraie « gagne », les suivantes
        // ne sont même pas évaluées.
        for (double celsius : new double[] {-5.0, 5.0, 18.0, 28.0, 38.0}) {
            String classe;
            if (celsius < 0) {
                classe = "gel";
            } else if (celsius < 15) {
                classe = "frais";
            } else if (celsius < 25) {
                classe = "tempere";
            } else if (celsius < 35) {
                classe = "chaud";
            } else {
                classe = "canicule";
            }
            System.out.println(celsius + "°C -> " + classe);
        }

        System.out.println("\n=== piège : else se rattache au if le plus proche non apparié ===");
        // Sans accolades, l'indentation est trompeuse : le `else` ci-dessous se
        // rattache au `if (b)`, PAS au `if (a)`, quelle que soit l'indentation visuelle.
        boolean a = true;
        boolean b = false;
        if (a)
            if (b)
                System.out.println("a et b");
            else
                System.out.println("a vrai, b faux (ce else appartient au if(b), pas au if(a))");
        // Toujours mettre des accolades dès qu'un if imbriqué est en jeu.
    }
}
