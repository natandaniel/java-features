package org.natandaniel.m01_fondamentaux.c03_litteraux.solutions;

/** Solution de référence — écrire un même nombre dans les différentes notations de littéraux. */
class Exo01_Notations {

    static int deuxCentCinquanteEnHexa() {
        return 0xFA;                 // hexadécimal : préfixe 0x
    }

    static int dixEnBinaire() {
        return 0b1010;               // binaire : préfixe 0b (@since Java 7)
    }

    static int soixanteQuatreEnOctal() {
        return 0100;                 // octal : préfixe 0
    }

    static int unMilliard() {
        return 1_000_000_000;        // underscores de lisibilité (@since Java 7)
    }
}
