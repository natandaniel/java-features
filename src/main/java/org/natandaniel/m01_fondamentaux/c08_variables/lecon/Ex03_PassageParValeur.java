package org.natandaniel.m01_fondamentaux.c08_variables.lecon;

/**
 * Leçon 3/3 — Java passe TOUJOURS par valeur.
 */
class Ex03_PassageParValeur {

    public static void main(String[] args) {
        System.out.println("=== Primitif : la méthode reçoit une COPIE ===");
        int original = 5;
        doubler(original);
        System.out.println("original après doubler() : " + original + "  (inchangé)");

        System.out.println("\n=== Référence : l'ADRESSE est copiée ===");
        int[] t = {1, 2, 3};
        modifierContenu(t);
        System.out.println("après modifierContenu()  : t[0] = " + t[0] + "  (changé : on a suivi l'adresse)");
        reaffecter(t);
        System.out.println("après reaffecter()       : t[0] = " + t[0] + "  (inchangé : la réaffectation est locale)");
    }

    static void doubler(int n) {
        n = n * 2;   // ne touche que la copie locale
    }

    static void modifierContenu(int[] tab) {
        tab[0] = 99;   // modifie l'OBJET pointé → visible de l'appelant
    }

    static void reaffecter(int[] tab) {
        tab = new int[]{0, 0, 0};   // réaffecte la copie locale de l'adresse → invisible
    }
}
