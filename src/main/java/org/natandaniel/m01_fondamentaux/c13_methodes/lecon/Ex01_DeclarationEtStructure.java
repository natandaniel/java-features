package org.natandaniel.m01_fondamentaux.c13_methodes.lecon;

/**
 * Leçon 1/5 — anatomie d'une déclaration de méthode (JLS §8.4).
 *
 * Une déclaration de méthode réunit un type de retour, un nom, une liste de
 * paramètres (entre parenthèses) et un corps (entre accolades) — les
 * modificateurs (`public`, `static`...) sont laissés de côté ici, voir
 * `m02_poo` pour leur rôle. Une méthode n'existe qu'à l'intérieur d'une
 * classe (pas de fonction "libre" en Java) ; structurer un programme en
 * méthodes permet de nommer une étape de calcul et de la réutiliser, plutôt
 * que de tout écrire dans un seul bloc `main`.
 */
class Ex01_DeclarationEtStructure {

    // type de retour : double | nom : convertirEnEuros | paramètre : montantEnCentimes
    static double convertirEnEuros(double montantEnCentimes) {
        return montantEnCentimes / 100.0;
    }

    // une méthode peut en appeler une autre : structuration du programme en étapes nommées
    static double calculerTotalAvecFrais(double montantEnCentimes, double fraisEnCentimes) {
        double montantTotal = montantEnCentimes + fraisEnCentimes;
        return convertirEnEuros(montantTotal); // réutilise la méthode précédente
    }

    public static void main(String[] args) {
        System.out.println("=== une méthode = type de retour + nom + paramètres + corps ===");
        System.out.println("convertirEnEuros(1050) = " + convertirEnEuros(1050));

        System.out.println("\n=== une méthode peut en appeler une autre ===");
        System.out.println("calculerTotalAvecFrais(1050, 250) = " + calculerTotalAvecFrais(1050, 250));
    }
}
