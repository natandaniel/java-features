package org.natandaniel.m02_poo.c11_blocs_initialisation.lecon;

import java.time.LocalDate;

/**
 * Leçon 1/4 — bloc d'initialisation d'instance (JLS §8.6) : un bloc {@code { ... }} déclaré au
 * niveau du corps de la classe, exécuté à chaque création d'instance, quel que soit le
 * constructeur choisi — y compris quand les constructeurs ne se chaînent pas entre eux via
 * {@code this(...)} (c03_mot_cle_this).
 */
class Ex01_BlocDInstance {

    static class Employe {
        private static int prochainMatricule = 1;

        final int matricule;
        final LocalDate dateEmbauche;
        final String nom;
        final String service;

        // Bloc d'initialisation d'instance (JLS §8.6). Exécuté avant le corps de CHAQUE
        // constructeur ci-dessous (JLS §12.5, étape 6 avant étape 7) : matricule et dateEmbauche
        // n'ont donc pas besoin d'être répétés dans les deux constructeurs.
        {
            matricule = prochainMatricule++;
            dateEmbauche = LocalDate.now();
            System.out.println("[bloc d'instance] matricule " + matricule + " attribué");
        }

        Employe(String nom) {
            this.nom = nom;
            this.service = "Non affecté";
            System.out.println("[constructeur Employe(nom)] " + nom);
        }

        Employe(String nom, String service) {
            this.nom = nom;
            this.service = service;
            System.out.println("[constructeur Employe(nom, service)] " + nom + " / " + service);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Deux constructeurs, un seul bloc d'instance partagé ===");
        Employe alice = new Employe("Alice");
        System.out.println("-> matricule=" + alice.matricule + ", service=" + alice.service);

        System.out.println();
        Employe bruno = new Employe("Bruno", "Développement");
        System.out.println("-> matricule=" + bruno.matricule + ", service=" + bruno.service);

        System.out.println("\n=== Le bloc s'exécute AVANT le corps du constructeur, à chaque fois ===");
        System.out.println("'[bloc d'instance]' apparaît toujours avant '[constructeur ...]' ci-dessus,");
        System.out.println("et matricule/dateEmbauche n'ont été écrits dans aucun des deux constructeurs.");
    }
}
