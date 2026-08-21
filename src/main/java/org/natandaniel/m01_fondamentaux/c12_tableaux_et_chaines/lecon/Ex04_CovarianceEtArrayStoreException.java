package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.lecon;

/**
 * Leçon 4/7 — covariance des tableaux et ArrayStoreException (JLS §10.5, §4.10.3).
 *
 * Si B est un sous-type de A, alors B[] est un sous-type de A[] (les tableaux
 * sont COVARIANTS, contrairement aux génériques — vu plus tard). Cette
 * covariance est pratique en lecture, mais dangereuse en écriture : le
 * compilateur ne peut pas vérifier qu'une affectation dans un tableau vu comme
 * A[] respecte le VRAI type d'exécution du tableau. La JVM fait donc une
 * vérification À CHAQUE écriture, et lève ArrayStoreException si elle échoue.
 */
class Ex04_CovarianceEtArrayStoreException {

    static class Produit {
        String nom;

        Produit(String nom) {
            this.nom = nom;
        }
    }

    static class ProduitPhysique extends Produit {
        ProduitPhysique(String nom) {
            super(nom);
        }
    }

    static class ProduitNumerique extends Produit {
        ProduitNumerique(String nom) {
            super(nom);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== covariance : ProduitPhysique[] est assignable à Produit[] ===");
        ProduitPhysique[] produitsPhysiques = new ProduitPhysique[3];
        Produit[] produits = produitsPhysiques; // légal : covariance des tableaux
        System.out.println("produits[1] == null : " + (produits[1] == null) + " (aucune erreur, simple lecture)");

        System.out.println("\n=== écriture compatible avec le VRAI type du tableau : aucun problème ===");
        produits[0] = new ProduitPhysique("Casque audio");
        System.out.println("produits[0] est bien un ProduitPhysique : " + (produitsPhysiques[0] != null));

        System.out.println("\n=== piège : le compilateur laisse passer, la JVM refuse à l'exécution ===");
        // Le compilateur ne voit que la variable `produits`, de type Produit[] :
        // affecter un ProduitNumerique est autorisé À LA COMPILATION.
        // Mais le tableau RÉEL en mémoire est un ProduitPhysique[] : y stocker un
        // ProduitNumerique violerait son vrai type. La JVM vérifie ce cas à
        // chaque écriture et lève ArrayStoreException plutôt que de corrompre le tableau.
        try {
            produits[1] = new ProduitNumerique("Cours en ligne");
            System.out.println("jamais atteint");
        } catch (ArrayStoreException e) {
            System.out.println("ArrayStoreException : " + e.getMessage());
        }

        System.out.println("\n=== pas de piège si le tableau déclaré ET réel coïncident dès le départ ===");
        Produit[] produitsMixtes = new Produit[2]; // vrai type : Produit[], pas une sous-classe
        produitsMixtes[0] = new ProduitPhysique("Clavier");
        produitsMixtes[1] = new ProduitNumerique("Formation Java");
        System.out.println("aucune ArrayStoreException : le tableau accepte tout Produit, comme déclaré");
    }
}
