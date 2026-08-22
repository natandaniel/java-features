package org.natandaniel.m02_poo.c14_clonage.solutions;

/**
 * Solution de référence — {@code Classeur.clone()} clone le tableau de {@code Dossier}, PUIS
 * remplace chaque élément par le résultat de son propre {@code clone()} (déjà correct) : c'est
 * cette étape par élément, pas le seul {@code dossiers.clone()}, qui rend chaque {@code Dossier}
 * indépendant entre original et clone.
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
            try {
                Classeur clone = (Classeur) super.clone();
                clone.dossiers = dossiers.clone();
                for (int i = 0; i < clone.dossiers.length; i++) {
                    clone.dossiers[i] = clone.dossiers[i].clone();
                }
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }
    }
}
