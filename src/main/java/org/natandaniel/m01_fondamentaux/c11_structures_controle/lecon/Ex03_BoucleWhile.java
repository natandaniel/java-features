package org.natandaniel.m01_fondamentaux.c11_structures_controle.lecon;

/**
 * Leçon 3/7 — la boucle while (JLS §14.12).
 *
 * `while` teste sa condition AVANT chaque itération, y compris la première :
 * si la condition est fausse dès le départ, le corps ne s'exécute jamais.
 */
class Ex03_BoucleWhile {

    public static void main(String[] args) {
        System.out.println("=== while : condition testée avant chaque tour ===");
        int compteur = 0;
        while (compteur < 5) {
            System.out.println("tour " + compteur);
            compteur++;
        }

        System.out.println("\n=== condition fausse dès le départ : 0 itération ===");
        int i = 10;
        while (i < 5) {
            System.out.println("jamais affiché");
        }
        System.out.println("boucle sautée entièrement, i vaut toujours " + i);

        System.out.println("\n=== cas d'usage typique : condition non bornée par un compteur ===");
        // while convient quand le nombre d'itérations n'est pas connu à l'avance :
        // ici, on divise par deux jusqu'à atteindre 1.
        int valeur = 100;
        int etapes = 0;
        while (valeur > 1) {
            valeur = valeur / 2;
            etapes++;
        }
        System.out.println("100 divisé par 2 jusqu'à 1 : " + etapes + " étapes");

        System.out.println("\n=== piège : oublier de faire progresser la condition ===");
        // Une condition qui ne devient jamais fausse boucle indéfiniment.
        // Ici on montre juste le motif correct : la variable de contrôle DOIT
        // être modifiée dans le corps.
        int garde = 3;
        while (garde > 0) {
            System.out.println("garde = " + garde);
            garde--;   // sans cette ligne : boucle infinie
        }
    }
}
