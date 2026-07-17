package org.natandaniel.m01_fondamentaux.c01_types_primitifs.exercices;

/**
 * Exercices — Représentation binaire.
 *
 * Implémente chaque méthode (remplace le TODO). Pour te vérifier, compare ton code
 * avec {@code solutions/Exo01_RepresentationBinaire} : le test
 * {@code Exo01_RepresentationBinaireTest} valide la solution de référence et te sert
 * de spécification.
 */
class Exo01_RepresentationBinaire {

    /**
     * Convertit un tableau de 8 bits (bits[0] = bit de poids fort) en sa valeur
     * non signée 0..255.
     * Exemple : {1,1,1,1,1,1,1,1} → 255 ; {0,0,0,0,1,0,1,0} → 10.
     */
    static int valeurNonSignee(int[] bits) {
        // TODO : parcourir les bits et accumuler les puissances de 2
        throw new UnsupportedOperationException("À implémenter");
    }

    /**
     * Compte le nombre de bits à 1 dans la représentation 32 bits de n,
     * SANS utiliser Integer.bitCount. Indice : décalage non signé >>> et masque & 1.
     */
    static int compterBitsAUn(int n) {
        // TODO
        throw new UnsupportedOperationException("À implémenter");
    }
}
