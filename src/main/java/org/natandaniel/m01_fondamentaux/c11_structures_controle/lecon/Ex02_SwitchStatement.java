package org.natandaniel.m01_fondamentaux.c11_structures_controle.lecon;

/**
 * Leçon 2/7 — le switch STATEMENT classique (JLS §14.11).
 *
 * Le switch en expression (`switch (x) -> ...`, `yield`) est une notation
 * différente, apparue en Java 14 : elle est traitée à part dans
 * `m11_modernite/c02_switch_expressions`. Ici : la forme historique, où chaque
 * `case` est une étiquette dans un bloc, et où le contrôle « tombe » d'une
 * étiquette à la suivante si on ne l'arrête pas explicitement avec `break`.
 */
class Ex02_SwitchStatement {

    public static void main(String[] args) {
        System.out.println("=== switch avec break : un seul case s'exécute ===");
        int jour = 3;
        switch (jour) {
            case 1:
                System.out.println("lundi");
                break;
            case 2:
                System.out.println("mardi");
                break;
            case 3:
                System.out.println("mercredi");
                break;
            default:
                System.out.println("autre jour");
        }

        System.out.println("\n=== piège : sans break, l'exécution TOMBE dans le case suivant ===");
        // "fall-through" : une fois qu'une étiquette a été atteinte, l'exécution
        // continue dans les cases suivants jusqu'au premier `break` rencontré —
        // même si leur propre condition n'a jamais été testée.
        int mois = 2;
        switch (mois) {
            case 1:
            case 2:
                System.out.println(mois + " -> hiver (janvier ET février partagent ce bloc)");
                break;
            case 3:
                System.out.println(mois + " -> printemps");
                break;
            default:
                System.out.println(mois + " -> autre");
        }

        System.out.println("\n=== le fall-through OUBLI (le vrai piège) ===");
        int note = 2;
        switch (note) {
            case 1:
                System.out.println("insuffisant");
                // pas de break ici : oubli --------v
            case 2:
                System.out.println("passable (et note=1 afficherait aussi cette ligne)");
                break;
            case 3:
                System.out.println("bien");
                break;
            default:
                System.out.println("inconnu");
        }

        System.out.println("\n=== switch sur String (JLS §14.11.1, depuis Java 7) ===");
        // @since Java 7 : le switch statement accepte aussi les chaînes de caractères,
        // comparées avec equals() sous le capot (pas de piège d'identité ici).
        String commande = "demarrer";
        switch (commande) {
            case "demarrer":
                System.out.println("démarrage du service");
                break;
            case "arreter":
                System.out.println("arrêt du service");
                break;
            default:
                System.out.println("commande inconnue");
        }
    }
}
