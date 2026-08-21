package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.lecon;

/**
 * Leçon 5/7 — cloner un tableau (JLS §10.7).
 *
 * Tout tableau expose une méthode `clone()` publique (héritée d'Object, mais
 * spécialisée : elle ne lève jamais CloneNotSupportedException et son type de
 * retour est le type exact du tableau). Le clone est SUPERFICIEL (shallow) :
 * pour un tableau à une dimension de primitifs, cela suffit à obtenir une copie
 * totalement indépendante — mais pour un tableau de tableaux, seul le niveau
 * externe est dupliqué : les sous-tableaux restent PARTAGÉS.
 */
class Ex05_ClonageDeTableau {

    public static void main(String[] args) {
        System.out.println("=== clone() d'un tableau de primitifs : copie totalement indépendante ===");
        int[] notes = {12, 15, 9};
        int[] copie = notes.clone();
        System.out.println("notes == copie : " + (notes == copie) + " (deux objets distincts)");
        copie[0] = 99;
        System.out.println("modifier copie[0] laisse notes[0] intact : " + notes[0]);

        System.out.println("\n=== piège : clone() d'un tableau de tableaux est superficiel ===");
        int[][] notesParTrimestre = {{14, 16}, {9, 11}};
        int[][] copieNotes = notesParTrimestre.clone();
        System.out.println("notesParTrimestre == copieNotes : " + (notesParTrimestre == copieNotes)
                + " (le tableau EXTERNE est bien un nouvel objet)");
        System.out.println("notesParTrimestre[0] == copieNotes[0] : " + (notesParTrimestre[0] == copieNotes[0])
                + " (mais chaque SOUS-tableau est le MÊME objet, partagé)");
        copieNotes[0][0] = 99;
        System.out.println("modifier copieNotes[0][0] modifie AUSSI notesParTrimestre[0][0] : "
                + notesParTrimestre[0][0] + " (effet de bord inattendu si on l'ignore)");

        System.out.println("\n=== pour une copie vraiment profonde : cloner chaque sous-tableau soi-même ===");
        int[][] copieProfonde = new int[notesParTrimestre.length][];
        for (int i = 0; i < notesParTrimestre.length; i++) {
            copieProfonde[i] = notesParTrimestre[i].clone();
        }
        copieProfonde[1][0] = 0;
        System.out.println("cette fois, notesParTrimestre[1][0] reste inchangé : " + notesParTrimestre[1][0]);
    }
}
