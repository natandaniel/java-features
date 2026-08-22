package org.natandaniel.m02_poo.c13_classes_immuables.lecon;

/**
 * Leçon 1/4 — classe immuable élémentaire : tous les champs sont {@code final} (JLS §8.3.1.2,
 * §4.12.4), assignés une seule fois dans le constructeur, sans aucun mutateur. Une fois créé, un
 * {@code Trajet} ne peut plus changer d'état — sûr à partager sans risque de modification
 * inattendue.
 */
class Ex01_ChampsFinalSansMutateur {

    static class Trajet {
        private final String origine;
        private final String destination;
        private final double distanceKm;

        Trajet(String origine, String destination, double distanceKm) {
            this.origine = origine;
            this.destination = destination;
            this.distanceKm = distanceKm;
        }

        String origine() {
            return origine;
        }

        String destination() {
            return destination;
        }

        double distanceKm() {
            return distanceKm;
        }
    }

    // Ne compile pas (JLS §8.3.1.2 : un champ final "blank" — assigné dans le constructeur — ne
    // peut plus jamais être réassigné) :
    //
    // trajet.distanceKm = 42.0; // error: cannot assign a value to final variable distanceKm

    public static void main(String[] args) {
        Trajet trajet = new Trajet("Lyon", "Marseille", 315.0);

        System.out.println("=== Trajet est immuable : aucun mutateur, tout est final ===");
        System.out.println(trajet.origine() + " -> " + trajet.destination()
                + " (" + trajet.distanceKm() + " km)");
        System.out.println("Aucune méthode ne permet de changer cet état après construction.");
    }
}
