package org.natandaniel.m01_fondamentaux.c11_structures_controle.lecon;

/**
 * Leçon 4/7 — la boucle do-while (JLS §14.13).
 *
 * Symétrique du while : la condition est testée APRÈS le corps. Le corps
 * s'exécute donc TOUJOURS au moins une fois, même si la condition est fausse
 * dès le départ.
 */
class Ex04_BoucleDoWhile {

    public static void main(String[] args) {
        System.out.println("=== do-while : le corps s'exécute avant le premier test ===");
        int compteur = 0;
        do {
            System.out.println("tour " + compteur);
            compteur++;
        } while (compteur < 5);

        System.out.println("\n=== différence clé avec while : condition fausse dès le départ ===");
        int i = 10;
        do {
            System.out.println("affiché quand même une fois, i = " + i);
        } while (i < 5);
        System.out.println("un while équivalent n'aurait rien affiché du tout");

        System.out.println("\n=== cas d'usage typique : traiter AU MOINS une valeur ===");
        // do-while convient quand une valeur doit être produite ou consommée au
        // moins une fois avant de savoir si on doit continuer — ex. : compter les
        // chiffres d'un nombre. Même 0 (aucun chiffre "restant" après division)
        // a un chiffre : il faut au moins un passage dans le corps.
        int n = 0;
        int chiffres = 0;
        int reste = n;
        do {
            reste = reste / 10;
            chiffres++;
        } while (reste != 0);
        System.out.println(n + " a " + chiffres + " chiffre(s) — un while aurait donné 0 à tort");
    }
}
