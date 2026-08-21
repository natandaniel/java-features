package org.natandaniel.m01_fondamentaux.c11_structures_controle.lecon;

/**
 * Leçon 5/7 — la boucle for classique (JLS §14.14.1).
 *
 * `for (initialisation; condition; incrément) corps` regroupe en une seule
 * ligne ce que `while` disperse : une variable de contrôle déclarée une fois,
 * une condition testée avant chaque tour, une mise à jour exécutée après
 * chaque tour. Le `for-each` (JLS §14.14.2, sur `Iterable`) est hors scope ici
 * — il dépend des collections, traité avec `m04_collections`.
 */
class Ex05_BoucleFor {

    public static void main(String[] args) {
        System.out.println("=== for classique ===");
        for (int i = 0; i < 5; i++) {
            System.out.println("tour " + i);
        }

        System.out.println("\n=== portée de la variable de boucle : invisible hors du for ===");
        for (int j = 0; j < 3; j++) {
            System.out.println("j = " + j);
        }
        // `j` n'existe plus ici : `System.out.println(j);` ne compilerait pas.
        System.out.println("j n'existe plus après l'accolade fermante du for");

        System.out.println("\n=== les trois clauses sont optionnelles ===");
        // Équivalent à un while : initialisation et incrément faits ailleurs.
        int k = 0;
        for (; k < 3; ) {
            System.out.println("k = " + k);
            k++;
        }

        System.out.println("\n=== plusieurs variables dans l'initialisation/incrément ===");
        for (int i = 0, j = 10; i < j; i++, j--) {
            System.out.println("i=" + i + " j=" + j);
        }

        System.out.println("\n=== compter à rebours ===");
        for (int i = 5; i >= 1; i--) {
            System.out.println(i);
        }
    }
}
