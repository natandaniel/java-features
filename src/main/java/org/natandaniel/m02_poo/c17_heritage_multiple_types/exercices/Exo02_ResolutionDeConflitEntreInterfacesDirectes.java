package org.natandaniel.m02_poo.c17_heritage_multiple_types.exercices;

/**
 * Exercice — {@code Avoir.resume} est à écrire : {@code Remboursable} et {@code Facturable},
 * implémentées directement (pas de lien d'héritage entre elles), redéfinissent {@code resume()}
 * différemment. {@code Avoir} doit résoudre le conflit en combinant les deux implémentations,
 * séparées par {@code " + "} ({@code "remboursement + facturation"}).
 */
class Exo02_ResolutionDeConflitEntreInterfacesDirectes {

    interface Remboursable {
        default String resume() {
            return "remboursement";
        }
    }

    interface Facturable {
        default String resume() {
            return "facturation";
        }
    }

    static class Avoir implements Remboursable, Facturable {
        @Override
        public String resume() {
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
