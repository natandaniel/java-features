package org.natandaniel.m02_poo.c11_blocs_initialisation.lecon;

/**
 * Leçon 2/4 — bloc d'initialisation statique (JLS §8.7) : exécuté une seule fois, à
 * l'initialisation de la classe (JLS §12.4), déclenchée par la première "utilisation active"
 * (ici, la première création d'instance) — jamais à nouveau ensuite, quel que soit le nombre
 * d'instances créées par la suite.
 */
class Ex02_BlocStatique {

    static class Employe {
        private static int nombreEmployesCrees = 0;

        // Bloc d'initialisation statique (JLS §8.7). Contrairement au bloc d'instance (Ex01),
        // il ne s'exécute PAS à chaque "new Employe()", mais une seule fois pour la classe
        // (JLS §12.4.1 : au premier des événements qui déclenchent l'initialisation de Employe).
        static {
            System.out.println("[bloc statique] chargement de la classe Employe (une seule fois)");
        }

        Employe() {
            nombreEmployesCrees++;
        }

        static int nombreEmployesCrees() {
            return nombreEmployesCrees;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Première instance : déclenche l'initialisation de la classe Employe ===");
        new Employe();

        System.out.println("\n=== Instances suivantes : la classe est déjà initialisée ===");
        new Employe();
        new Employe();

        System.out.println("\nnombreEmployesCrees() = " + Employe.nombreEmployesCrees());
        System.out.println("Le message '[bloc statique] chargement...' n'apparaît qu'une seule fois");
        System.out.println("ci-dessus, alors que trois instances ont été créées.");
    }
}
