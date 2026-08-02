package org.natandaniel.m01_fondamentaux.c10_execution.lecon;

import java.util.Arrays;

/**
 * Leçon 2/6 — L'identité que le noyau donne au processus.
 *
 * <p>Une exécution Java est un processus comme un autre : il a un PID, un parent,
 * une commande, un utilisateur, un instant de naissance. `ProcessHandle` donne accès
 * à ces informations depuis Java.
 */
class Ex02_IdentiteDuProcessus {

    public static void main(String[] args) {
        ProcessHandle moi = ProcessHandle.current();   // @since Java 9

        System.out.println("=== Ce que le noyau sait de nous ===");
        System.out.println("PID                  : " + moi.pid());
        System.out.println("   → l'identifiant unique attribué à la création du processus.");

        ProcessHandle.Info info = moi.info();
        System.out.println("Commande lancée      : " + info.command().orElse("(non disponible)"));
        System.out.println("   → le BINAIRE NATIF exécuté : `java`, pas ta classe.");

        System.out.println("Arguments            : "
                + info.arguments().map(Arrays::toString).orElse("(non disponible)"));
        System.out.println("Utilisateur (UID)    : " + info.user().orElse("(non disponible)"));
        System.out.println("Démarré à            : " + info.startInstant().map(Object::toString)
                .orElse("(non disponible)"));
        System.out.println("Temps CPU consommé   : " + info.totalCpuDuration().map(Object::toString)
                .orElse("(non disponible)"));

        System.out.println("\n=== Le parent : tout processus est forké d'un autre ===");
        moi.parent().ifPresentOrElse(
                parent -> {
                    System.out.println("PID du parent        : " + parent.pid());
                    System.out.println("Commande du parent   : "
                            + parent.info().command().orElse("(non disponible)"));
                    System.out.println("   → le shell (ou l'IDE) qui a appelé fork() puis exec().");
                },
                () -> System.out.println("Pas de parent visible."));

        System.out.println("\n=== Les arguments passés au PROGRAMME (pas à la JVM) ===");
        System.out.println("args = " + Arrays.toString(args) + "   (essaie : java ... Ex02 a b c)");
        System.out.println("   → le noyau transmet argv au binaire `java` ;");
        System.out.println("     le launcher en retire les options JVM et passe le reste à main().");

        System.out.println("\n=== Le processus meurt, son identité disparaît ===");
        System.out.println("Encore vivant ?      : " + moi.isAlive() + " (forcément : c'est nous)");
        System.out.println("   → à la fin de main(), le noyau détruit le processus et");
        System.out.println("     récupère TOUTE sa mémoire. Le PID pourra être réattribué.");
    }
}
