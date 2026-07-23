package org.natandaniel.m01_fondamentaux.c09_modele_memoire.lecon;

/**
 * Leçon 1/6 — Où vit chaque variable : pile, tas, metaspace.
 */
class Ex01_PileEtTas {

    int compteur = 7;                        // champ d'instance → TAS, inline dans l'objet
    static int total = 100;                  // champ de classe  → METASPACE (zone de la classe)

    public static void main(String[] args) {
        System.out.println("=== PILE : les variables locales ===");
        int x = 42;                          // le slot de x contient les 4 octets de 42
        System.out.println("x (primitif local)          : " + x + "   → la VALEUR est dans le frame");

        int[] tableau = {10, 20, 30};        // le slot de tableau contient une ADRESSE
        System.out.println("tableau (référence locale)  : " + tableau);
        System.out.println("   la variable est sur la pile, l'objet tableau est sur le tas");

        System.out.println("\n=== TAS : les objets et leurs champs ===");
        Ex01_PileEtTas a = new Ex01_PileEtTas();
        Ex01_PileEtTas b = new Ex01_PileEtTas();
        a.compteur = 1;
        b.compteur = 2;
        System.out.println("a.compteur = " + a.compteur + ", b.compteur = " + b.compteur
                + "   → une case par objet");

        System.out.println("\n=== METASPACE : les champs static ===");
        System.out.println("total vu par a : " + total + "   (une seule case pour toute la classe)");
        total = 999;
        System.out.println("total après modification : " + total + "   → partagé par toutes les instances");

        System.out.println("\n=== La référence locale survit-elle à l'objet ? ===");
        System.out.println("tableau[1] avant l'appel : " + tableau[1]);
        remplacerLocalement(tableau);
        System.out.println("tableau[1] après l'appel : " + tableau[1]
                + "   → le frame de la méthode a disparu, pas l'objet");
    }

    static void remplacerLocalement(int[] recu) {
        // `recu` est un NOUVEAU slot, dans un NOUVEAU frame, qui contient une COPIE de l'adresse.
        recu = new int[]{0, 0, 0};   // ne réaffecte que ce slot local ; l'appelant ne voit rien
        // au retour, le frame est dépilé : `recu` disparaît, et le tableau {0,0,0} qu'il
        // était seul à référencer devient inaccessible → candidat au GC.
    }
}
