package org.natandaniel.m02_poo.c16_methodes_par_defaut.lecon;

/**
 * Leçon 3/4 — un diamant d'interfaces (une interface étendue par deux autres, elles-mêmes
 * réunies par une quatrième) n'entraîne pas forcément de conflit. JLS §9.4.1, exemple {@code
 * Top}/{@code Left}/{@code Right}/{@code Bottom} : si une seule branche redéfinit la méthode
 * {@code default}, la sous-interface hérite de cette redéfinition, pas de l'ambiguïté — "the
 * third clause prevents a subinterface from re-inheriting a method that has already been
 * overridden by another of its superinterfaces".
 */
class Ex03_HeritageEntreInterfacesSansConflit {

    interface OperationFinanciere {
        default String resume() {
            return "opération financière";
        }
    }

    // Redéfinit resume() : Avoir héritera de CETTE version, pas de celle d'OperationFinanciere.
    interface Remboursable extends OperationFinanciere {
        @Override
        default String resume() {
            return "remboursement";
        }
    }

    // Ne redéfinit rien : hérite tel quel de OperationFinanciere.resume().
    interface Facturable extends OperationFinanciere {
    }

    // JLS §9.4.1 : Avoir hérite de resume() depuis Remboursable, pas depuis Facturable, car
    // Remboursable.resume() overrides OperationFinanciere.resume() (troisième clause de §9.4.1 :
    // Facturable "n'hérite" en réalité que d'une méthode déjà supplantée ailleurs dans le
    // diamant — elle ne compte pas comme une seconde candidate distincte).
    interface Avoir extends Remboursable, Facturable {
    }

    static class AvoirStandard implements Avoir {
    }

    public static void main(String[] args) {
        Avoir avoir = new AvoirStandard();

        System.out.println("=== Un diamant d'interfaces sans conflit hérite de la branche qui "
                + "a redéfini la méthode ===");
        System.out.println("Résumé : " + avoir.resume() + " (hérité de Remboursable, pas de "
                + "Facturable ni d'OperationFinanciere)");
    }
}
