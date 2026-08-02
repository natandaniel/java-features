package org.natandaniel.m01_fondamentaux.c10_execution.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solutions — Ce que le launcher `java` fait de la ligne de commande.
 */
class Exo01_LigneDeCommande {

    /** Les options JVM : tout ce qui précède le nom de la classe principale. */
    static List<String> optionsJvm(String ligne) {
        List<String> mots = mots(ligne);
        int classe = indexClassePrincipale(mots);
        // Pas de classe (ex. `java -version`) : toute la ligne était pour la JVM.
        int fin = classe == -1 ? mots.size() : classe;
        return List.copyOf(mots.subList(0, fin));
    }

    /** Le premier mot qui n'est ni une option ni la valeur d'une option. */
    static String classePrincipale(String ligne) {
        List<String> mots = mots(ligne);
        int classe = indexClassePrincipale(mots);
        return classe == -1 ? "" : mots.get(classe);
    }

    /** Tout ce qui suit la classe principale — options comprises : elles ne sont plus pour la JVM. */
    static List<String> argumentsProgramme(String ligne) {
        List<String> mots = mots(ligne);
        int classe = indexClassePrincipale(mots);
        if (classe == -1) {
            return List.of();
        }
        return List.copyOf(mots.subList(classe + 1, mots.size()));
    }

    /** Mode source direct : la « classe » est en fait un fichier .java. // @since Java 11 (JEP 330) */
    static boolean modeSourceDirect(String ligne) {
        return classePrincipale(ligne).endsWith(".java");
    }

    /** Découpe la ligne en mots, en écartant le binaire `java` s'il est présent. */
    private static List<String> mots(String ligne) {
        if (ligne == null) {
            return List.of();
        }
        String coupee = ligne.trim();
        if (coupee.isEmpty()) {
            return List.of();
        }
        List<String> mots = new ArrayList<>(Arrays.asList(coupee.split("\\s+")));
        if (mots.get(0).equals("java")) {
            mots.remove(0);   // le binaire natif exécuté par le noyau, pas un argument
        }
        return mots;
    }

    /** Position de la classe principale dans la liste de mots, ou -1 s'il n'y en a pas. */
    private static int indexClassePrincipale(List<String> mots) {
        int i = 0;
        while (i < mots.size()) {
            String mot = mots.get(i);
            if (!mot.startsWith("-")) {
                return i;                       // trouvé : à partir d'ici, la JVM ne lit plus rien
            }
            i += prendUneValeur(mot) ? 2 : 1;   // `-cp lib` compte pour deux mots
        }
        return -1;
    }

    /** Les options dont la valeur est un mot séparé. */
    private static boolean prendUneValeur(String option) {
        return option.equals("-cp") || option.equals("-classpath") || option.equals("--class-path");
    }
}
