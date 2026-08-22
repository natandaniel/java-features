package org.natandaniel.m02_poo.c20_enumerations.lecon;

/**
 * Leçon 2/5 — chaque constante peut passer des arguments à un constructeur (JLS §8.9.1,
 * {@code Example 8.9.2-1}), invoqué une seule fois par constante lors de l'initialisation de la
 * classe. Ce constructeur est implicitement {@code private} : il est même une erreur de
 * compilation de le déclarer {@code public} ou {@code protected} (§8.9.2), puisqu'aucun code
 * extérieur ne doit jamais créer de nouvelle instance.
 */
class Ex02_ConstructeurEtChampsParConstante {

    enum NiveauAbonnement {
        GRATUIT(0),
        STANDARD(999),
        PREMIUM(1999);

        // Constructeur sans modificateur d'accès : implicitement private (§8.9.2).
        NiveauAbonnement(int prixMensuelEnCentimes) {
            this.prixMensuelEnCentimes = prixMensuelEnCentimes;
        }

        private final int prixMensuelEnCentimes;

        int prixMensuelEnCentimes() {
            return prixMensuelEnCentimes;
        }
    }

    // Ne compile pas (JLS §8.9.2) : un constructeur d'enum ne peut pas être public/protected.
    //
    // public NiveauAbonnement(int prixMensuelEnCentimes) { ... }
    // error: modifier public not allowed here

    public static void main(String[] args) {
        System.out.println("=== Chaque constante porte son propre état, fixé au constructeur "
                + "===");
        for (NiveauAbonnement niveau : NiveauAbonnement.values()) {
            System.out.printf("%-10s %d,%02d €/mois%n", niveau.name(),
                    niveau.prixMensuelEnCentimes() / 100, niveau.prixMensuelEnCentimes() % 100);
        }
    }
}
