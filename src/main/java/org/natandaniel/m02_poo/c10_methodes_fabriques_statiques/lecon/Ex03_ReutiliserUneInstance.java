package org.natandaniel.m02_poo.c10_methodes_fabriques_statiques.lecon;

/**
 * Leçon 3/3 — une fabrique statique n'est pas obligée de créer un nouvel objet à chaque appel,
 * contrairement à "new" (qui alloue systématiquement). Ici, deux valeurs immuables et sans état
 * variable sont construites une seule fois et renvoyées par référence partagée.
 * Nuance : ceci n'est PAS le patron de conception Singleton (qui garantit une instance unique
 * dans toute l'application) — seulement deux constantes réutilisées, comme le fait Boolean.valueOf.
 */
class Ex03_ReutiliserUneInstance {

    static class EtatConnexion {
        private static final EtatConnexion CONNECTE = new EtatConnexion("connecté");
        private static final EtatConnexion DECONNECTE = new EtatConnexion("déconnecté");

        private final String libelle;

        private EtatConnexion(String libelle) {
            this.libelle = libelle;
        }

        static EtatConnexion connecte() {
            return CONNECTE;
        }

        static EtatConnexion deconnecte() {
            return DECONNECTE;
        }

        @Override
        public String toString() {
            return libelle;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Deux appels, la même référence ===");
        EtatConnexion a = EtatConnexion.connecte();
        EtatConnexion b = EtatConnexion.connecte();
        System.out.println("a == b = " + (a == b) + " (aucune allocation au deuxième appel)");

        System.out.println("\n=== 'new' ne pourrait pas offrir ça ===");
        System.out.println("'new EtatConnexion(\"connecté\")' allouerait systématiquement une instance,");
        System.out.println("même si une valeur équivalente existe déjà.");
    }
}
