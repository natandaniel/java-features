package org.natandaniel.m02_poo.c02_encapsulation.lecon;

/**
 * Leçon 3/3 — les quatre modificateurs d'accès : private, (package-private), protected, public.
 */
class Ex03_ModificateursAcces {

    static class Exemple {
        private int champPrive;       // visible seulement dans SA classe de premier niveau
        int champPackage;             // pas de modificateur = visible dans tout le package
        protected int champProtege;   // visible dans le package + les sous-classes (autres packages)
        public int champPublic;       // visible de partout
    }

    public static void main(String[] args) {
        System.out.println("=== Les quatre niveaux de visibilité, du plus restrictif au plus ouvert ===");
        Exemple e = new Exemple();
        e.champPackage = 1;   // OK : même package
        e.champProtege = 2;   // OK : même package (protected inclut le package)
        e.champPublic = 3;    // OK : toujours accessible
        System.out.println("champPackage=" + e.champPackage + ", champProtege=" + e.champProtege
                + ", champPublic=" + e.champPublic);

        System.out.println("\n=== private, lui, n'est visible que dans la classe de premier niveau qui le déclare ===");
        // Ici, Exemple est imbriquée DANS Ex03_ModificateursAcces : ce main() partage la même
        // classe de premier niveau, donc e.champPrive compile encore (cf. leçon précédente).
        e.champPrive = 4;
        System.out.println("champPrive=" + e.champPrive + " — accessible ICI seulement parce que main()");
        System.out.println("est dans la même classe de premier niveau qu'Exemple.");
        System.out.println("Depuis une autre classe de premier niveau, 'e.champPrive' ne compilerait pas.");

        System.out.println("\nprivate  : la classe de premier niveau qui le déclare, uniquement");
        System.out.println("(défaut) : ce package seulement");
        System.out.println("protected: ce package + les sous-classes, même hors package");
        System.out.println("public   : partout");
    }
}
