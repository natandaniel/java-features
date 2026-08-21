package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.lecon;

/**
 * Leçon 2/7 — accès indexé et bornes (JLS §10.4).
 *
 * Tous les tableaux sont 0-origin : un tableau de longueur n s'indexe de 0 à
 * n-1 inclus. Chaque accès est vérifié À L'EXÉCUTION — un index hors bornes ne
 * provoque jamais de comportement indéfini, il lève systématiquement une
 * exception.
 */
class Ex02_AccesEtBornes {

    public static void main(String[] args) {
        int[] notes = {12, 15, 9, 18, 14};

        System.out.println("=== accès valides : indices 0 à length - 1 ===");
        System.out.println("premier élément (index 0) : " + notes[0]);
        System.out.println("dernier élément (index length - 1 = " + (notes.length - 1) + ") : " + notes[notes.length - 1]);

        System.out.println("\n=== écriture : même syntaxe que la lecture ===");
        notes[2] = 20;
        System.out.println("notes[2] remplacé par 20 : " + notes[2]);

        System.out.println("\n=== piège : index == length est déjà hors bornes ===");
        // Erreur classique en fin de boucle : `for (int i = 0; i <= notes.length; i++)`
        // teste `notes[notes.length]`, qui n'existe pas — la borne haute est EXCLUSIVE.
        try {
            int horsBornes = notes[notes.length];
            System.out.println("jamais atteint : " + horsBornes);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException : " + e.getMessage());
        }

        System.out.println("\n=== index négatif : même exception, même vérification ===");
        try {
            int negatif = notes[-1];
            System.out.println("jamais atteint : " + negatif);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException : " + e.getMessage());
        }

        System.out.println("\n=== indexer avec un long ne compile pas : seuls int (ou promu en int) sont acceptés ===");
        // int mauvais = notes[3L]; // erreur de compilation
        // short, byte et char sont acceptés : ils sont promus en int (JLS §5.6).
        short indexCourt = 1;
        System.out.println("notes[(short) 1] = " + notes[indexCourt] + " (short promu en int, autorisé)");
    }
}
