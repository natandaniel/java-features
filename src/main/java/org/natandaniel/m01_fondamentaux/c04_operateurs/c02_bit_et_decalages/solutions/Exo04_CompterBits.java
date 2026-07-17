package org.natandaniel.m01_fondamentaux.c04_operateurs.c02_bit_et_decalages.solutions;

/** Solution de référence — compter les bits à 1 (décalage non signé + masque). */
class Exo04_CompterBits {

    static int compterBitsAUn(int n) {
        int compte = 0;
        for (int i = 0; i < 32; i++) {
            compte += (n >>> i) & 1;   // >>> : décalage non signé (pas d'extension de signe)
        }
        return compte;
    }
}
