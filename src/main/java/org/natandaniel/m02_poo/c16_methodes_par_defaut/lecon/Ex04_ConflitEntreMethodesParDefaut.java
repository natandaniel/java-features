package org.natandaniel.m02_poo.c16_methodes_par_defaut.lecon;

/**
 * Leçon 4/4 — quand les deux branches d'un diamant d'interfaces redéfinissent {@code default}
 * la même méthode différemment, il y a un vrai conflit : JLS §9.4.1.3, "if an interface I
 * inherits a default method whose signature is override-equivalent with another method inherited
 * by I, then a compile-time error occurs". Résolution : la sous-interface doit redéfinir la
 * méthode elle-même — elle peut alors choisir explicitement quelle implémentation garder via
 * {@code Interface.super.methode()} (JLS §9.4.1.1), ou en combiner plusieurs.
 */
class Ex04_ConflitEntreMethodesParDefaut {

    interface OperationFinanciere {
        default String resume() {
            return "opération financière";
        }
    }

    // Redéfinit resume() différemment de Facturable ci-dessous.
    interface Remboursable extends OperationFinanciere {
        @Override
        default String resume() {
            return "remboursement";
        }
    }

    // Redéfinit resume() différemment de Remboursable ci-dessus.
    interface Facturable extends OperationFinanciere {
        @Override
        default String resume() {
            return "facturation";
        }
    }

    // Ne compile pas si cette redéfinition est retirée (JLS §9.4.1.3) : Avoir hériterait à la
    // fois de Remboursable.resume() et de Facturable.resume(), deux méthodes override-équivalentes
    // sans qu'aucune ne supplante l'autre.
    //
    // interface Avoir extends Remboursable, Facturable {
    // }
    // error: interface Avoir inherits unrelated defaults for resume() from types Remboursable
    // and Facturable

    interface Avoir extends Remboursable, Facturable {
        // Résolution du conflit : Avoir doit fournir sa propre redéfinition. Elle combine
        // explicitement les deux implémentations en désambiguïsant chaque appel par le nom de
        // la superinterface (JLS §9.4.1.1 : "super qualified by a superinterface name").
        @Override
        default String resume() {
            return Remboursable.super.resume() + " + " + Facturable.super.resume();
        }
    }

    static class AvoirStandard implements Avoir {
    }

    public static void main(String[] args) {
        Avoir avoir = new AvoirStandard();

        System.out.println("=== Un conflit entre deux méthodes default se résout en "
                + "redéfinissant explicitement, avec Interface.super.methode() ===");
        System.out.println("Résumé : " + avoir.resume());

        // Rappel (§9.4.1.3) : un conflit entre une méthode abstract et une méthode default de
        // même signature est traité de la même façon — erreur de compilation, à résoudre en
        // redéfinissant explicitement dans la sous-interface.
    }
}
