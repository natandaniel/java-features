package org.natandaniel.m02_poo.c16_methodes_par_defaut.exercices;

/**
 * Exercice — {@code Avoir.resume} est à écrire : {@code Remboursable} et {@code Facturable}
 * redéfinissent {@code resume()} différemment, {@code Avoir} hérite des deux et doit résoudre le
 * conflit en combinant les deux implémentations, séparées par {@code " + "}
 * ({@code "remboursement + facturation"}).
 */
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
            throw new UnsupportedOperationException("À implémenter");
        }
    }

    static class AvoirStandard implements Avoir {
    }
}
