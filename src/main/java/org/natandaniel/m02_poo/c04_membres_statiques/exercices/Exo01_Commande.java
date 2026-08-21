package org.natandaniel.m02_poo.c04_membres_statiques.exercices;

/**
 * Exercices — compteur partagé (static) sur une classe Commande.
 */
class Exo01_Commande {

    static class Commande {
        static int nombreCreees = 0;

        final double montant;

        Commande(double montant) {
            this.montant = montant;
            nombreCreees++;
        }
    }

    /** Nombre total de commandes créées depuis le démarrage. */
    static int nombreCommandes() {
        throw new UnsupportedOperationException("À implémenter");
    }

    /** Montant moyen d'un ensemble de commandes (0 si le tableau est vide). */
    static double montantMoyen(Commande[] commandes) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
