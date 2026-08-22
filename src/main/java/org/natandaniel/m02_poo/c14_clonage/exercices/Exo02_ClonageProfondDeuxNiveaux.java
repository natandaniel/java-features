package org.natandaniel.m02_poo.c14_clonage.exercices;

/**
 * Exercice — clonage profond à deux niveaux d'imbrication : {@code Classeur} contient un
 * tableau de {@code Dossier}, et chaque {@code Dossier} contient lui-même un tableau de
 * fichiers. {@code Dossier.clone()} est déjà correct (fourni) ; écrire
 * {@code Classeur.clone()} pour qu'il clone chaque {@code Dossier} individuellement — cloner
 * seulement le tableau qui les contient (via {@code dossiers.clone()}) ne suffit pas : les
 * éléments du tableau resteraient les mêmes instances de {@code Dossier}, partagées entre
 * original et clone.
 */
class Exo02_ClonageProfondDeuxNiveaux {

    static class Dossier implements Cloneable {
        private String[] fichiers;

        Dossier(String[] fichiers) {
            this.fichiers = fichiers;
        }

        String[] fichiers() {
            return fichiers;
        }

        void renommerFichier(String fichier, int index) {
            fichiers[index] = fichier;
        }

        @Override
        public Dossier clone() {
            try {
                Dossier clone = (Dossier) super.clone();
                clone.fichiers = fichiers.clone();
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }
    }

    static class Classeur implements Cloneable {
        private Dossier[] dossiers;

        Classeur(Dossier[] dossiers) {
            this.dossiers = dossiers;
        }

        Dossier[] dossiers() {
            return dossiers;
        }

        @Override
        public Classeur clone() {
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
