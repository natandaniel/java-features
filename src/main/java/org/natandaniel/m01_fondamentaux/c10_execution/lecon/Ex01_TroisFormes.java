package org.natandaniel.m01_fondamentaux.c10_execution.lecon;

import java.net.URL;

/**
 * Leçon 1/6 — Les trois formes d'un programme : source, bytecode, processus.
 *
 * <p>Le fichier `.java` et le fichier `.class` sont des OCTETS INERTES sur le disque.
 * Ce qui s'exécute, c'est un processus en RAM. Cette démo le prouve : à l'exécution,
 * la JVM sait où est le `.class` qu'elle a chargé — et n'a jamais eu besoin du `.java`.
 */
class Ex01_TroisFormes {

    public static void main(String[] args) {
        System.out.println("=== 1. Le bytecode chargé : un fichier bien réel ===");
        // getResource cherche une RESSOURCE à côté de la classe, dans le classpath.
        URL bytecode = Ex01_TroisFormes.class.getResource("Ex01_TroisFormes.class");
        System.out.println("Emplacement du .class    : " + bytecode);
        System.out.println("   → c'est ce fichier-là que la JVM a lu, vérifié, puis interprété.");

        System.out.println("\n=== 2. Le source ? introuvable à l'exécution ===");
        URL source = Ex01_TroisFormes.class.getResource("Ex01_TroisFormes.java");
        System.out.println("Emplacement du .java     : " + source
                + "   → null : le source n'est pas dans le classpath, et la JVM s'en passe.");

        System.out.println("\n=== 3. Où la JVM cherche le bytecode ===");
        System.out.println("java.class.path          : " + System.getProperty("java.class.path"));
        System.out.println("   → la liste des racines fouillées pour trouver les .class.");

        System.out.println("\n=== 4. Le code source de la classe, lui, a disparu ===");
        System.out.println("Nom de la classe chargée : " + Ex01_TroisFormes.class.getName());
        System.out.println("Chargeur de classes      : " + Ex01_TroisFormes.class.getClassLoader());
        System.out.println("   → un objet Class, en mémoire, construit À PARTIR du .class.");

        System.out.println("\n=== 5. Le troisième état : le processus ===");
        System.out.println("Version de la JVM        : " + Runtime.version());   // @since Java 9
        System.out.println("Processeurs disponibles  : " + Runtime.getRuntime().availableProcessors());
        System.out.println("   → il n'y a que MAINTENANT quelque chose de vivant : ce processus.");

        // Depuis Java 11, `java Ex01_TroisFormes.java` compile en mémoire et exécute d'un trait
        // (JEP 330) : aucun .class n'est écrit sur le disque. Le modèle reste
        // source → bytecode → processus ; seule l'étape intermédiaire devient invisible.
    }
}
