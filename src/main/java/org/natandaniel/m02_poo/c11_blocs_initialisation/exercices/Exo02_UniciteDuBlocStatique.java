package org.natandaniel.m02_poo.c11_blocs_initialisation.exercices;

/**
 * Exercice — un bloc statique s'exécute une seule fois pour la classe (JLS §12.4.1), jamais une
 * fois par instance (piège classique : le confondre avec le bloc d'instance de {@code Exo01}).
 */
class Exo02_UniciteDuBlocStatique {

    static class CapteurTemperature {
        static int initialisations = 0;

        static {
            initialisations++;
        }
    }

    /**
     * Crée trois {@code CapteurTemperature}, puis renvoie le nombre de fois où le bloc statique
     * de la classe s'est exécuté (attendu : 1, pas 3).
     */
    static int nombreDInitialisationsApresTroisInstances() {
        throw new UnsupportedOperationException("À implémenter");
    }
}
