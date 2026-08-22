package org.natandaniel.m02_poo.c20_enumerations.solutions;

/** Solution de référence — chaque constante applique sa propre formule de frais. */
class Exo01_MethodeAbstraiteParConstante {

    enum ModeExpedition {
        STANDARD {
            @Override
            double fraisEnEuros(double poidsKg) {
                return 2 + poidsKg * 0.5;
            }
        },
        EXPRESS {
            @Override
            double fraisEnEuros(double poidsKg) {
                return 5 + poidsKg * 1.2;
            }
        },
        GRATUITE {
            @Override
            double fraisEnEuros(double poidsKg) {
                return 0;
            }
        };

        abstract double fraisEnEuros(double poidsKg);
    }
}
