package org.natandaniel.m02_poo.c09_classe_object.lecon;

import java.util.HashSet;
import java.util.Set;

/**
 * Leçon 3/4 — redéfinir equals()/hashCode() selon leur contrat (Javadoc java.lang.Object) :
 * réflexivité, symétrie, transitivité, cohérence, x.equals(null) == false pour equals() ; et la
 * règle qui les relie — objets égaux selon equals() ⇒ même hashCode(), obligatoire.
 */
class Ex03_RedefinirEqualsHashCode {

    /**
     * equals() redéfini SEUL, sans hashCode() : viole le contrat qui relie les deux méthodes.
     * Comparé ici à ProduitAvecReference (correct) pour rendre le piège visible.
     */
    static class ProduitEqualsSeul {
        String reference; // clé métier : identifie le produit indépendamment du nom affiché
        String nom;

        ProduitEqualsSeul(String reference, String nom) {
            this.reference = reference;
            this.nom = nom;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProduitEqualsSeul)) {
                return false;
            }
            ProduitEqualsSeul autre = (ProduitEqualsSeul) obj;
            return reference.equals(autre.reference);
        }
        // Pas de hashCode() : celui d'Object reste actif, basé sur l'identité — incohérent avec
        // cet equals(), qui dit maintenant que deux références égales sont "le même produit".
    }

    /** Version correcte : equals() ET hashCode() redéfinis sur le même champ (reference). */
    static class ProduitAvecReference {
        String reference;
        String nom;

        ProduitAvecReference(String reference, String nom) {
            this.reference = reference;
            this.nom = nom;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProduitAvecReference)) {
                return false;
            }
            ProduitAvecReference autre = (ProduitAvecReference) obj;
            return reference.equals(autre.reference);
        }

        @Override
        public int hashCode() {
            return reference.hashCode();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Contrat d'equals() (Javadoc java.lang.Object), vérifié sur ProduitAvecReference ===");
        ProduitAvecReference x = new ProduitAvecReference("P-001", "Aspirateur");
        ProduitAvecReference y = new ProduitAvecReference("P-001", "Aspirateur reconditionné"); // même référence
        ProduitAvecReference z = new ProduitAvecReference("P-001", "Aspirateur (autre libellé)");

        System.out.println("réflexif   : x.equals(x)                     = " + x.equals(x));
        System.out.println("symétrique : x.equals(y) == y.equals(x)      = " + (x.equals(y) == y.equals(x)));
        System.out.println("transitif  : x.equals(y) && y.equals(z) => x.equals(z) = "
                + (x.equals(y) && y.equals(z) && x.equals(z)));
        System.out.println("x.equals(null)                               = " + x.equals(null));
        System.out.println("Deux objets égaux malgré des \"nom\" différents : l'égalité porte sur");
        System.out.println("la référence métier, pas sur tous les champs — un choix de conception assumé.");

        System.out.println("\n=== Le piège : equals() redéfini SANS hashCode() casse un HashSet ===");
        Set<ProduitEqualsSeul> cassé = new HashSet<>();
        cassé.add(new ProduitEqualsSeul("P-001", "Aspirateur"));
        cassé.add(new ProduitEqualsSeul("P-001", "Aspirateur (doublon)"));
        System.out.println("cassé.size() = " + cassé.size() + " (2 : equals() dit qu'ils sont égaux,");
        System.out.println("mais hashCode() (celui d'Object, basé sur l'identité) les range dans des");
        System.out.println("compartiments différents — le HashSet ne les compare jamais entre eux).");

        System.out.println("\n=== Corrigé : equals() ET hashCode() redéfinis ensemble ===");
        Set<ProduitAvecReference> correct = new HashSet<>();
        correct.add(new ProduitAvecReference("P-001", "Aspirateur"));
        correct.add(new ProduitAvecReference("P-001", "Aspirateur (doublon)"));
        System.out.println("correct.size() = " + correct.size() + " (1 : même hashCode() => même compartiment,");
        System.out.println("où equals() les reconnaît enfin comme égaux).");
    }
}
