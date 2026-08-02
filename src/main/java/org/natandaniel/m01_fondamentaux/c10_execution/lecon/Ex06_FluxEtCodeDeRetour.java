package org.natandaniel.m01_fondamentaux.c10_execution.lecon;

import java.util.Arrays;

/**
 * Leçon 6/6 — Le contrat entre le processus et le système.
 *
 * <p>Le noyau donne au processus des arguments et trois descripteurs de fichiers ouverts ;
 * le processus lui rend un code de retour. Tout le reste (heap, objets, threads) est
 * interne et meurt avec lui.
 *
 * <p>À essayer :
 * <pre>
 *   java ... Ex06_FluxEtCodeDeRetour bonjour 42
 *   echo $?              # affiche le code de retour
 *   java ... Ex06_FluxEtCodeDeRetour 2&gt;/dev/null    # stderr jeté, stdout conservé
 * </pre>
 */
class Ex06_FluxEtCodeDeRetour {

    public static void main(String[] args) {
        System.out.println("=== 1. Ce que le noyau nous a donné : les arguments ===");
        System.out.println("args.length = " + args.length + ", args = " + Arrays.toString(args));
        System.out.println("   → le noyau passe argv au binaire `java` ; le launcher retire les");
        System.out.println("     options JVM (-Xmx…, -cp…) et ne transmet que le reste à main().");

        System.out.println("\n=== 2. Trois descripteurs ouverts d'office ===");
        System.out.println("stdout (descripteur 1) : cette ligne — le flux normal.");
        System.err.println("stderr (descripteur 2) : cette ligne — le flux d'erreur, redirigeable à part.");
        System.out.println("stdin  (descripteur 0) : System.in, disponible même si personne n'écrit dedans.");
        System.out.println("   → ce sont des ressources du PROCESSUS, héritées de son parent (le shell).");

        System.out.println("\n=== 3. Ce que nous rendrons au système : un entier ===");
        int code = args.length;   // convention arbitraire, pour la démonstration
        System.out.println("Nous allons sortir avec le code " + code);
        System.out.println("   → 0 signifie « succès » par convention Unix ; tout le reste, un échec.");
        System.out.println("   → une exception non rattrapée dans main() ferait sortir avec le code 1.");

        System.out.println("\n=== 4. Ce qui se passe à la sortie ===");
        System.out.println("- le noyau ferme les descripteurs restés ouverts ;");
        System.out.println("- il libère TOUT l'espace d'adressage : heap, metaspace, piles ;");
        System.out.println("- il remonte le code de retour au parent, qui l'attendait (waitpid).");
        System.out.println("Aucun `null`, aucun GC, aucun `finally` n'est nécessaire pour cela.");

        System.exit(code);   // termine immédiatement le processus : le code après ne s'exécute pas
    }
}
