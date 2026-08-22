package org.natandaniel.m02_poo.c17_heritage_multiple_types.solutions;

/** Solution de référence — combine les deux implémentations en conflit via un appel qualifié. */
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
            return Remboursable.super.resume() + " + " + Facturable.super.resume();
        }
    }
}
