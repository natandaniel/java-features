package org.natandaniel.m01_fondamentaux.c09_modele_memoire.lecon;

/**
 * Leçon 4/6 — Une case par classe (static) contre une case par objet (instance).
 */
class Ex04_StaticEtInstance {

    static int instancesCreees = 0;     // METASPACE : une seule case, pour toute la classe
    int numero;                         // TAS : une case dans CHAQUE objet

    Ex04_StaticEtInstance() {
        instancesCreees++;              // le compteur partagé avance
        numero = instancesCreees;       // le champ propre à cet objet est figé
    }

    public static void main(String[] args) {
        System.out.println("=== Avant toute création ===");
        System.out.println("instancesCreees = " + instancesCreees
                + "   (le champ static existe déjà : la classe est chargée, pas besoin d'objet)");

        System.out.println("\n=== Trois objets ===");
        Ex04_StaticEtInstance a = new Ex04_StaticEtInstance();
        Ex04_StaticEtInstance b = new Ex04_StaticEtInstance();
        Ex04_StaticEtInstance c = new Ex04_StaticEtInstance();

        System.out.println("a.numero = " + a.numero + " | b.numero = " + b.numero
                + " | c.numero = " + c.numero + "   → trois cases distinctes sur le tas");
        System.out.println("instancesCreees = " + instancesCreees
                + "   → une seule case, incrémentée trois fois");

        System.out.println("\n=== Modifier le static depuis n'importe où ===");
        instancesCreees = 0;
        System.out.println("après remise à zéro, vu depuis la classe : " + instancesCreees);
        System.out.println("a.numero reste " + a.numero + "   → l'état de l'objet est indépendant");

        System.out.println("\n=== Durée de vie ===");
        a = null;
        b = null;
        c = null;
        System.out.println("les trois objets sont devenus inaccessibles → candidats au GC ;");
        System.out.println("instancesCreees, lui, vit tant que la classe est chargée.");
        System.out.println("C'est exactement ce qui rend un cache static si facile à transformer en fuite.");
    }
}
