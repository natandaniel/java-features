package org.natandaniel.m02_poo.c13_classes_immuables.solutions;

/** Solution de référence — copie le tableau AVANT de le confier au constructeur naïf. */
class Exo01_CopieDefensiveConstructeur {

    static class Inventaire {
        private final int[] quantites;

        Inventaire(int[] quantites) {
            this.quantites = quantites;
        }

        int[] quantites() {
            return quantites;
        }
    }

    static Inventaire creerInventaireProtege(int[] quantitesSource) {
        return new Inventaire(quantitesSource.clone());
    }
}
