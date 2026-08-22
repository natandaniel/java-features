package org.natandaniel.m02_poo.c11_blocs_initialisation.solutions;

/** Solution de référence — le bloc statique ne s'exécute qu'une fois, quel que soit le nombre d'instances. */
class Exo02_UniciteDuBlocStatique {

    static class CapteurTemperature {
        static int initialisations = 0;

        static {
            initialisations++;
        }
    }

    static int nombreDInitialisationsApresTroisInstances() {
        new CapteurTemperature();
        new CapteurTemperature();
        new CapteurTemperature();
        return CapteurTemperature.initialisations;
    }
}
