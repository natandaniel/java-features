package org.natandaniel.m02_poo.c13_classes_immuables.exercices;

/**
 * Exercice — copie défensive en entrée (Effective Java, Item 50) : {@code Inventaire} stocke le
 * tableau qu'on lui passe SANS le copier (constructeur naïf, volontairement laissé tel quel).
 * Protéger son état est donc la responsabilité de l'appelant, au moment de la construction.
 */
class Exo01_CopieDefensiveConstructeur {

    static class Inventaire {
        private final int[] quantites;

        Inventaire(int[] quantites) {
            this.quantites = quantites; // naïf : aucune copie
        }

        int[] quantites() {
            return quantites;
        }
    }

    /**
     * Construit un {@code Inventaire} dont l'état reste inchangé même si {@code quantitesSource}
     * est modifié par l'appelant après coup.
     */
    static Inventaire creerInventaireProtege(int[] quantitesSource) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
