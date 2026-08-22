package org.natandaniel.m02_poo.c11_blocs_initialisation.lecon;

/**
 * Leçon 3/4 — ordre complet d'exécution avec héritage (c06_heritage) : à la première création
 * d'une sous-classe, la superclasse est initialisée avant la sous-classe (JLS §12.4.1). Puis,
 * pour chaque instance, la superclasse termine entièrement son initialisation (initialiseurs
 * d'instance + constructeur, JLS §12.5 étapes 5-7) avant que la sous-classe ne commence la sienne.
 */
class Ex03_OrdreComplet {

    static class Employe {
        private static int compteurMatricule = 0;

        static {
            System.out.println("[static Employe] chargement de la classe Employe");
        }

        int matricule = ++compteurMatricule;

        {
            System.out.println("[instance Employe] bloc d'instance, matricule=" + matricule);
        }

        Employe() {
            System.out.println("[constructeur Employe] corps");
        }
    }

    static class Manager extends Employe {
        static {
            System.out.println("[static Manager] chargement de la classe Manager");
        }

        String equipe = "Non définie";

        {
            System.out.println("[instance Manager] bloc d'instance, equipe=" + equipe);
        }

        Manager() {
            System.out.println("[constructeur Manager] corps");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Première création (new Manager()) ===");
        new Manager();

        System.out.println("\n=== Deuxième création : les DEUX classes sont déjà initialisées ===");
        new Manager();

        System.out.println("\n=== Ordre observé à la première création ===");
        System.out.println("1. [static Employe]   -- la superclasse s'initialise en premier (JLS §12.4.1)");
        System.out.println("2. [static Manager]");
        System.out.println("3. [instance Employe]  -- super(...) implicite d'abord (JLS §12.5, étape 5)");
        System.out.println("4. [constructeur Employe]");
        System.out.println("5. [instance Manager]  -- puis seulement la sous-classe (étape 6-7)");
        System.out.println("6. [constructeur Manager]");
    }
}
