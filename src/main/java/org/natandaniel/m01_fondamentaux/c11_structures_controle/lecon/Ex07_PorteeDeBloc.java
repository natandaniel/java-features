package org.natandaniel.m01_fondamentaux.c11_structures_controle.lecon;

/**
 * Leçon 7/7 — portée de bloc et shadowing (JLS §14.2 Blocks, §6.3 Scope).
 *
 * Un bloc `{ ... }` délimite la durée de vie des variables locales qui y sont
 * déclarées : une variable n'existe qu'entre sa déclaration et l'accolade
 * fermante du bloc qui la contient. Une structure de contrôle (if/for/while)
 * ouvre implicitement un bloc pour son corps, même sans accolades visibles
 * autour d'une instruction unique.
 */
class Ex07_PorteeDeBloc {

    public static void main(String[] args) {
        System.out.println("=== une variable ne vit que dans son bloc ===");
        {
            int x = 42;
            System.out.println("x visible ici : " + x);
        }
        // `x` n'existe plus ici : `System.out.println(x);` ne compilerait pas.
        System.out.println("x n'existe plus après le bloc");

        System.out.println("\n=== deux blocs frères peuvent réutiliser le même nom ===");
        // Ces deux blocs sont indépendants : ils ne se voient pas l'un l'autre,
        // donc réutiliser `resultat` dans chacun n'est PAS du shadowing.
        {
            int resultat = 10;
            System.out.println("premier bloc, resultat = " + resultat);
        }
        {
            int resultat = 20;   // nouvelle variable, sans lien avec la précédente
            System.out.println("second bloc, resultat = " + resultat);
        }

        System.out.println("\n=== piège interdit par le compilateur : masquer une variable englobante ===");
        // Contrairement aux blocs frères ci-dessus, Java REFUSE de redéclarer un
        // même nom dans un bloc IMBRIQUÉ dans celui qui le déclare déjà — pas
        // d'ambiguïté silencieuse possible ici (contrairement à d'autres langages).
        int total = 0;
        for (int i = 0; i < 3; i++) {
            // `int total = i;` ici ne compilerait pas : `total` est déjà visible
            // (déclarée juste au-dessus, dans le bloc englobant).
            total += i;
        }
        System.out.println("total = " + total);

        System.out.println("\n=== la variable de boucle a la portée du for, pas de la méthode ===");
        for (int i = 0; i < 2; i++) {
            System.out.println("premier for, i = " + i);
        }
        for (int i = 0; i < 2; i++) {   // même nom `i` : légal, la première portée est déjà refermée
            System.out.println("second for, même nom i réutilisé sans conflit, i = " + i);
        }
    }
}
