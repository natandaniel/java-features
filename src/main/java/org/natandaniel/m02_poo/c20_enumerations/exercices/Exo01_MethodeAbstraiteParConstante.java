package org.natandaniel.m02_poo.c20_enumerations.exercices;

/**
 * Exercice — {@code fraisEnEuros(double poidsKg)} est à écrire pour chacune des trois constantes
 * de {@code ModeExpedition} : {@code STANDARD} coûte {@code 2 + poidsKg * 0.5} ; {@code EXPRESS}
 * coûte {@code 5 + poidsKg * 1.2} ; {@code GRATUITE} coûte toujours {@code 0}, quel que soit le
 * poids.
 */
class Exo01_MethodeAbstraiteParConstante {

    enum ModeExpedition {
        STANDARD {
            @Override
            double fraisEnEuros(double poidsKg) {
                throw new UnsupportedOperationException("À implémenter");
            }
        },
        EXPRESS {
            @Override
            double fraisEnEuros(double poidsKg) {
                throw new UnsupportedOperationException("À implémenter");
            }
        },
        GRATUITE {
            @Override
            double fraisEnEuros(double poidsKg) {
                throw new UnsupportedOperationException("À implémenter");
            }
        };

        abstract double fraisEnEuros(double poidsKg);
    }
}
