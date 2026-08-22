package org.natandaniel.m02_poo.c16_methodes_par_defaut.solutions;

/** Solution de référence — combine les deux implémentations en conflit via un appel qualifié. */
class Exo02_ResolutionDeConflitEntreInterfaces {

    interface OperationFinanciere {
        default String resume() {
            return "opération financière";
        }
    }

    interface Remboursable extends OperationFinanciere {
        @Override
        default String resume() {
            return "remboursement";
        }
    }

    interface Facturable extends OperationFinanciere {
        @Override
        default String resume() {
            return "facturation";
        }
    }

    interface Avoir extends Remboursable, Facturable {
        @Override
        default String resume() {
            return Remboursable.super.resume() + " + " + Facturable.super.resume();
        }
    }

    static class AvoirStandard implements Avoir {
    }
}
