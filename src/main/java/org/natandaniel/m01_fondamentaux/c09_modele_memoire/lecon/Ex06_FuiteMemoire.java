package org.natandaniel.m01_fondamentaux.c09_modele_memoire.lecon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Leçon 6/6 — Une fuite en Java : un objet inutile mais toujours accessible.
 */
class Ex06_FuiteMemoire {

    /** Le coupable classique : une collection static que rien ne purge jamais. */
    static final Map<String, byte[]> CACHE_QUI_FUIT = new HashMap<>();

    /** Le coupable n°2 : des écouteurs inscrits et jamais désinscrits. */
    static final List<String> ECOUTEURS = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Un cache sans purge grandit sans fin ===");
        for (int i = 0; i < 5; i++) {
            traiterRequete("requete-" + i);
        }
        System.out.println("entrées retenues : " + CACHE_QUI_FUIT.size()
                + "   → aucune ne sera jamais collectée : la Map static les rend accessibles.");
        System.out.println("Le GC fonctionne parfaitement. C'est le code qui refuse de lâcher prise.");

        System.out.println("\n=== Le même cache, borné ===");
        Map<String, byte[]> borne = cacheBorne(3);
        for (int i = 0; i < 5; i++) {
            borne.put("requete-" + i, new byte[1024]);
        }
        System.out.println("entrées retenues : " + borne.size() + "   " + borne.keySet());
        System.out.println("Les deux plus anciennes ont été RETIRÉES → plus aucun chemin vers elles");
        System.out.println("→ devenues inaccessibles → collectables.");

        System.out.println("\n=== Écouteur inscrit et oublié ===");
        ECOUTEURS.add("fenetre-principale");
        ECOUTEURS.add("boite-dialogue");
        System.out.println("inscrits : " + ECOUTEURS.size() + " " + ECOUTEURS);
        System.out.println("La boîte de dialogue est fermée à l'écran... mais toujours dans la liste.");

        ECOUTEURS.remove("boite-dialogue");     // la désinscription qu'on oublie une fois sur deux
        System.out.println("après désinscription : " + ECOUTEURS.size() + " " + ECOUTEURS);

        System.out.println("\n=== StackOverflowError vs OutOfMemoryError ===");
        System.out.println("StackOverflowError  : la PILE est saturée (récursion sans fin).");
        System.out.println("OutOfMemoryError    : le TAS est saturé (objets accessibles trop nombreux).");
        System.out.println("Deux zones, deux erreurs, deux causes — ne pas les confondre.");
        System.out.println("Pour voir la seconde : java -Xmx16m ... en accumulant dans CACHE_QUI_FUIT.");
    }

    static void traiterRequete(String cle) {
        CACHE_QUI_FUIT.put(cle, new byte[1024]);   // on met, on ne retire jamais
    }

    /**
     * Cache borné : {@link LinkedHashMap} en mode « accès » qui évince l'entrée la plus
     * ancienne dès que la capacité est dépassée.
     */
    static Map<String, byte[]> cacheBorne(int capacite) {
        return new LinkedHashMap<>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, byte[]> eldest) {
                return size() > capacite;
            }
        };
    }
}
