package org.natandaniel.m01_fondamentaux.c01_types_primitifs.solutions;

/** Solution de référence — Représentation binaire. */
class Exo01_RepresentationBinaire {

    static int valeurNonSignee(int[] bits) {
        int valeur = 0;
        for (int bit : bits) {
            valeur = (valeur << 1) | (bit & 1);   // méthode de Horner en base 2
        }
        return valeur;
    }

    static int compterBitsAUn(int n) {
        int compte = 0;
        for (int i = 0; i < 32; i++) {
            compte += (n >>> i) & 1;   // >>> : décalage non signé (pas d'extension de signe)
        }
        return compte;
    }
}
