package org.natandaniel.m01_fondamentaux.c13_methodes.lecon;

/**
 * Leçon 2/5 — paramètres formels (JLS §8.4.1) et passage par valeur.
 *
 * Chaque paramètre est une variable LOCALE à la méthode, initialisée avec
 * la valeur de l'argument au moment de l'appel. Java passe toujours par
 * valeur (voir `c08_references_variables` pour la nuance côté référence,
 * non répétée ici) : réaffecter un paramètre dans le corps ne modifie
 * jamais la variable de l'appelant. Les arguments sont aussi associés aux
 * paramètres par POSITION, jamais par nom.
 */
class Ex02_ParametresEtPassageParValeur {

    static int doubler(int nombre) {
        nombre = nombre * 2; // ne modifie que la copie locale
        return nombre;
    }

    static String formaterHeure(int heures, int minutes) {
        return heures + "h" + minutes;
    }

    public static void main(String[] args) {
        int valeur = 21;
        int resultat = doubler(valeur);
        System.out.println("=== réaffecter un paramètre ne touche pas la variable de l'appelant ===");
        System.out.println("valeur (inchangée) = " + valeur);
        System.out.println("resultat = " + resultat);

        System.out.println("\n=== l'ordre des arguments compte, les noms des paramètres ne sont pas des étiquettes ===");
        // formaterHeure(11, 5) et formaterHeure(5, 11) ne renvoient pas la même chose :
        // les arguments sont associés aux paramètres par POSITION, jamais par nom.
        System.out.println("formaterHeure(11, 5) = " + formaterHeure(11, 5));
        System.out.println("formaterHeure(5, 11) = " + formaterHeure(5, 11));
    }
}
