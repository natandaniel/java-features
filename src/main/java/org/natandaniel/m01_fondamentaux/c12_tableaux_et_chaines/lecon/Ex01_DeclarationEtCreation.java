package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.lecon;

/**
 * Leçon 1/7 — déclaration vs création d'un tableau (JLS §10.2, §10.3).
 *
 * Un tableau est un OBJET (JLS §4.3.1) : le déclarer ne l'alloue pas. Seule une
 * expression de création (`new T[n]`) ou un initialiseur (`{ ... }`) fait exister
 * le tableau en mémoire. `length` est un champ `final` fixé une fois pour toutes
 * à la création — un tableau ne peut jamais changer de taille.
 */
class Ex01_DeclarationEtCreation {

    public static void main(String[] args) {
        System.out.println("=== déclarer ne crée rien : la variable vaut null tant que rien n'est assigné ===");
        int[] notes;
        notes = null;
        System.out.println("notes = " + notes + " (aucun tableau n'existe encore)");

        System.out.println("\n=== création explicite : new T[n] alloue n cases à leur valeur par défaut ===");
        notes = new int[5];
        System.out.println("notes.length = " + notes.length);
        for (int i = 0; i < notes.length; i++) {
            System.out.print(notes[i] + " ");
        }
        System.out.println("(int[] : valeur par défaut 0, comme tout champ int non initialisé)");

        System.out.println("\n=== initialiseur : crée le tableau ET fixe ses valeurs en une expression ===");
        int[] premieresNotes = {12, 15, 9, 18, 14};
        System.out.print("premieresNotes = ");
        for (int note : premieresNotes) {
            System.out.print(note + " ");
        }
        System.out.println();

        System.out.println("\n=== tableau de références : valeur par défaut null, pas un objet vide ===");
        String[] participants = new String[3];
        System.out.println("participants[0] = " + participants[0] + " (aucune String créée, juste une case vide)");

        System.out.println("\n=== length est final : impossible d'agrandir un tableau existant ===");
        // notes.length = 10; // ne compile pas : `length` est en lecture seule
        int[] notesEtendues = new int[notes.length + 1];
        System.arraycopy(notes, 0, notesEtendues, 0, notes.length);
        System.out.println("pour \"agrandir\", il faut créer un NOUVEAU tableau plus grand et y recopier"
                + " (notesEtendues.length = " + notesEtendues.length + ")");
    }
}
