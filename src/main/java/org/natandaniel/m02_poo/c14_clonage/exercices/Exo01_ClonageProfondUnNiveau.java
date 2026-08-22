package org.natandaniel.m02_poo.c14_clonage.exercices;

/**
 * Exercice — {@code Equipe.clone()} est à écrire : le tableau {@code joueurs} doit être
 * totalement indépendant entre l'original et le clone après l'appel (aucune mutation via l'un
 * ne doit être visible depuis l'autre).
 */
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
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
