package org.natandaniel.m02_poo.c17_heritage_multiple_types.lecon;

/**
 * Leçon 4/4 — une méthode concrète héritée de la superclasse l'emporte toujours sur un
 * {@code default} d'interface de même signature, sans ambiguïté ni erreur de compilation. JLS
 * §8.4.8 : "it is possible for an inherited concrete method to prevent the inheritance of an
 * abstract or default method. (The concrete method will override the abstract or default method
 * 'from C', per §8.4.8.1 and §9.4.1.1.)" — point explicitement laissé hors scope par {@code c16},
 * comblé ici.
 */
class Ex04_PrioriteDeLaMethodeConcreteDeClasse {

    static class OperationFinanciere {
        public String resume() {
            return "opération financière (classe)";
        }
    }

    interface Remboursable {
        default String resume() {
            return "remboursement (interface)";
        }
    }

    // Avoir hérite de OperationFinanciere.resume() (méthode concrète de classe) ET implémente
    // Remboursable, dont le default resume() a la même signature. Pas de conflit : la méthode
    // concrète de la superclasse prévaut toujours, sans qu'Avoir ait besoin de redéfinir quoi
    // que ce soit elle-même.
    static class Avoir extends OperationFinanciere implements Remboursable {
    }

    public static void main(String[] args) {
        Avoir avoir = new Avoir();

        System.out.println("=== Une méthode concrète de superclasse l'emporte toujours sur un "
                + "default d'interface, sans conflit ===");
        System.out.println("Résumé : " + avoir.resume() + " (hérité de la classe, pas de "
                + "l'interface)");
    }
}
