package org.natandaniel.m02_poo.c14_clonage.solutions;

/** Solution de référence — clone le tableau {@code joueurs} après {@code super.clone()}. */
class Exo01_ClonageProfondUnNiveau {

    static class Equipe implements Cloneable {
        private String[] joueurs;

        Equipe(String[] joueurs) {
            this.joueurs = joueurs;
        }

        String[] joueurs() {
            return joueurs;
        }

        void remplacerJoueur(String joueur, int index) {
            joueurs[index] = joueur;
        }

        @Override
        public Equipe clone() {
            try {
                Equipe clone = (Equipe) super.clone();
                clone.joueurs = joueurs.clone();
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }
    }
}
