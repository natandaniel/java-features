package org.natandaniel.m02_poo.c04_membres_statiques.solutions;

/** Solution de référence — compteur partagé (static) sur une classe Commande. */
class Exo01_Commande {

    static class Commande {
        static int nombreCreees = 0;

        final double montant;

        Commande(double montant) {
            this.montant = montant;
            nombreCreees++;
        }
    }

    static int nombreCommandes() {
        return Commande.nombreCreees;
    }

    static double montantMoyen(Commande[] commandes) {
        if (commandes.length == 0) {
            return 0;
        }
        double total = 0;
        for (Commande commande : commandes) {
            total += commande.montant;
        }
        return total / commandes.length;
    }
}
