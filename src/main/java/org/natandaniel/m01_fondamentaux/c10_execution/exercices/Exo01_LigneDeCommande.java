package org.natandaniel.m01_fondamentaux.c10_execution.exercices;

import java.util.List;

/**
 * Exercices — Ce que le launcher `java` fait de la ligne de commande.
 *
 * <p>Quand tu tapes {@code java -Xmx512m -cp lib/a.jar Main x y}, le noyau transmet
 * TOUS ces mots au binaire natif `java`. C'est le launcher qui les trie : ce qui le
 * concerne (les options JVM), le nom de la classe à lancer, et le reste — qui devient
 * le tableau {@code args} de {@code main()}.
 *
 * <p><b>Modèle retenu</b> (simplifié mais fidèle) :
 * <ul>
 *   <li>les mots sont séparés par un ou plusieurs espaces ;</li>
 *   <li>si le premier mot est {@code "java"}, il est ignoré (c'est le binaire, pas un argument) ;</li>
 *   <li>tant qu'on n'a pas rencontré la classe principale, tout mot commençant par {@code "-"}
 *       est une option JVM ; {@code -cp}, {@code -classpath} et {@code --class-path} consomment
 *       en plus le mot suivant (leur valeur) ;</li>
 *   <li>le premier mot restant est la <b>classe principale</b> ;</li>
 *   <li>tout ce qui suit appartient au programme — <b>y compris</b> les mots commençant par
 *       {@code "-"} : après le nom de la classe, le launcher ne lit plus rien pour lui-même.</li>
 * </ul>
 */
class Exo01_LigneDeCommande {

    /**
     * Renvoie les options destinées à la JVM, dans l'ordre, valeurs comprises.
     *
     * <p>Exemple : {@code optionsJvm("java -Xmx512m -cp lib/a.jar Main x")}
     * → {@code [-Xmx512m, -cp, lib/a.jar]}.
     *
     * <p>Ligne vide ou uniquement composée d'espaces → liste vide.
     */
    static List<String> optionsJvm(String ligne) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Renvoie le nom de la classe principale (ou du fichier source en mode direct),
     * ou la chaîne vide s'il n'y en a pas.
     *
     * <p>Exemples : {@code "java -Xmx512m Main x"} → {@code "Main"} ;
     * {@code "java -version"} → {@code ""} (aucune classe à lancer).
     */
    static String classePrincipale(String ligne) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Renvoie ce que recevra le tableau {@code args} de {@code main()}.
     *
     * <p>Exemple : {@code argumentsProgramme("java -cp lib Main -Xmx1g fichier.txt")}
     * → {@code [-Xmx1g, fichier.txt]} — le {@code -Xmx1g} est ici un argument du
     * PROGRAMME, car il vient après le nom de la classe. C'est le piège classique.
     */
    static List<String> argumentsProgramme(String ligne) {
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Indique si la ligne utilise le mode « source direct » : lancer un {@code .java}
     * sans compilation préalable, la compilation ayant lieu en mémoire.
     * // @since Java 11 (JEP 330)
     *
     * <p>Exemple : {@code modeSourceDirect("java Main.java a")} → {@code true} ;
     * {@code modeSourceDirect("java Main a")} → {@code false}.
     */
    static boolean modeSourceDirect(String ligne) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
