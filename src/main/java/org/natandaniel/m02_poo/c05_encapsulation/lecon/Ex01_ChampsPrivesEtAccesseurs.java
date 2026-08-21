package org.natandaniel.m02_poo.c05_encapsulation.lecon;

/**
 * Leçon 1/3 — champs privés + accesseurs : contrôler l'accès à l'état d'un objet.
 */
class Ex01_ChampsPrivesEtAccesseurs {

    static class CompteBancaire {
        private double solde; // private : inaccessible depuis l'extérieur de la classe

        CompteBancaire(double soldeInitial) {
            this.solde = soldeInitial;
        }

        double getSolde() {
            return solde;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== private : l'état interne n'est visible que via des méthodes ===");
        CompteBancaire compte = new CompteBancaire(100.0);
        System.out.println("compte.getSolde() = " + compte.getSolde());

        System.out.println("\n=== Piège : private se limite à la classe de PREMIER NIVEAU, pas à la classe imbriquée ===");
        // CompteBancaire est une classe imbriquée DANS Ex01_ChampsPrivesEtAccesseurs : ce main()
        // partage la même classe de premier niveau, donc compte.solde compile ici (JLS §6.6.1).
        compte.solde = 500;
        System.out.println("compte.solde = 500 compile ICI, car main() est dans la même classe de premier niveau.");
        System.out.println("compte.getSolde() = " + compte.getSolde());
        System.out.println("Depuis une AUTRE classe de premier niveau (un autre fichier .java), 'compte.solde'");
        System.out.println("ne compilerait pas : c'est là que private protège réellement l'état.");
    }
}
