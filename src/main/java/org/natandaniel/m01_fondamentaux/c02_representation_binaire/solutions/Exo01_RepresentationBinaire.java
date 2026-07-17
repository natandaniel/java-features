package org.natandaniel.m01_fondamentaux.c02_representation_binaire.solutions;

/** Solution de référence — Représentation binaire (arithmétique pure, sans opérateurs de bits). */
class Exo01_RepresentationBinaire {

    static int valeurNonSignee(int[] bits) {
        int valeur = 0;
        for (int bit : bits) {
            valeur = valeur * 2 + bit;   // méthode de Horner en base 2 : on double, puis on ajoute le bit
        }
        return valeur;
    }
}
