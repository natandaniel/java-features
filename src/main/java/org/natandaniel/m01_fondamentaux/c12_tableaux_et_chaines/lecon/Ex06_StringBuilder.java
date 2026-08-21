package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.lecon;

/**
 * Leçon 6/7 — StringBuilder, l'alternative mutable à String (JLS §4.3.3).
 *
 * `String` est immuable (§4.3.3 : « A String object has a constant value ») —
 * voir `c08_references_variables` pour le pool et `==` vs `.equals()`, non
 * répétés ici. Chaque `+=` sur une String ne MODIFIE rien : il crée un nouvel
 * objet et réaffecte la variable. Dans une boucle, ce gaspillage devient
 * mesurable. `StringBuilder` (@since Java 5 ; son ancêtre `StringBuffer`
 * existe depuis Java 1.0 et reste thread-safe, mais plus lent — hors scope ici)
 * expose un tableau de caractères réellement MUTABLE.
 */
class Ex06_StringBuilder {

    public static void main(String[] args) {
        System.out.println("=== piège : concaténer des String en boucle recrée un objet à chaque tour ===");
        String[] participants = {"Alice", "Bilal", "Chloé", "Driss"};
        String rapport = "";
        for (String participant : participants) {
            rapport += participant + ", "; // chaque += : nouvel objet String, l'ancien est abandonné
        }
        System.out.println("rapport (String) = " + rapport);
        System.out.println("(" + participants.length + " tours de boucle -> " + participants.length
                + " objets String intermédiaires créés puis jetés)");

        System.out.println("\n=== StringBuilder : un seul objet, modifié en place ===");
        StringBuilder constructeur = new StringBuilder();
        for (String participant : participants) {
            constructeur.append(participant).append(", "); // append() modifie le MÊME objet
        }
        System.out.println("constructeur (StringBuilder) = " + constructeur);

        System.out.println("\n=== méthodes de mutation en place, pas de nouvel objet à chaque appel ===");
        StringBuilder titre = new StringBuilder("bilan trimestriel");
        titre.insert(0, "[BROUILLON] ");
        titre.replace(0, 11, "[VALIDÉ]"); // 11 = longueur de "[BROUILLON]", l'espace qui suit n'est pas touché
        System.out.println("titre après insert()+replace() : " + titre);

        System.out.println("\n=== conversion finale en String, seulement quand le résultat est figé ===");
        String rapportFinal = constructeur.toString();
        System.out.println("rapportFinal (String, immuable désormais) = " + rapportFinal);

        System.out.println("\n=== deux StringBuilder de même contenu ne sont PAS égaux avec equals() par défaut ===");
        // StringBuilder n'a pas redéfini equals() (contrairement à String) : la comparaison
        // reste celle d'Object, donc l'IDENTITÉ. Comparer le contenu : toString().equals(...).
        StringBuilder a = new StringBuilder("Java");
        StringBuilder b = new StringBuilder("Java");
        System.out.println("a.equals(b) : " + a.equals(b) + " (identité, pas contenu)");
        System.out.println("a.toString().equals(b.toString()) : " + a.toString().equals(b.toString()));
    }
}
