package org.natandaniel.m01_fondamentaux.c13_methodes.lecon;

/**
 * Leçon 5/5 — surcharge de méthodes (JLS §8.4.2, §8.4.9).
 *
 * Deux méthodes de même nom sont surchargées si leurs signatures (nom +
 * types des paramètres) diffèrent — le type de retour seul ne suffit
 * jamais à les distinguer (§8.4.2 : deux signatures override-equivalentes
 * en conflit sont une erreur de compilation, même avec des types de retour
 * différents). Le principe est déjà posé pour les constructeurs dans
 * `m02_poo/c02_constructeurs`, non répété ici. La variante appelée est
 * choisie à la COMPILATION, à partir du type statique des arguments (JLS
 * §15.12.2, mécanique non détaillée ici).
 */
class Ex05_Surcharge {

    // signatures différentes (int,int) vs (float,float) : bien deux méthodes distinctes
    static float deplacer(int dx, int dy) {
        System.out.println("(variante int,int appelée)");
        return dx + dy;
    }

    static float deplacer(float dx, float dy) {
        System.out.println("(variante float,float appelée)");
        return dx + dy;
    }

    // piège : varargs + surcharge — à arité égale, la variante à arité FIXE est
    // toujours préférée quand elle correspond exactement (résolution en plusieurs
    // phases, JLS §15.12.2)
    static int compter(int a, int b) {
        System.out.println("(variante à arité fixe appelée)");
        return a + b;
    }

    static int compter(int... nombres) {
        System.out.println("(variante varargs appelée)");
        int total = 0;
        for (int n : nombres) {
            total += n;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("=== le compilateur choisit la variante selon le type des arguments ===");
        deplacer(1, 2); // choisit deplacer(int,int)
        deplacer(1.5f, 2.5f); // choisit deplacer(float,float)

        System.out.println("\n=== à arité égale, la variante à arité fixe gagne sur le varargs ===");
        compter(1, 2); // deux arguments int : (int,int) est préférée à (int...)
        compter(1, 2, 3); // trois arguments : seule (int...) correspond
    }
}
