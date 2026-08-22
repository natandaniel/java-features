package org.natandaniel.m02_poo.c20_enumerations.lecon;

/**
 * Leçon 4/5 — la déclaration d'un enum accepte une clause {@code implements} au même titre
 * qu'une classe ordinaire (JLS §8.9 : {@code EnumDeclaration ::= {ClassModifier} enum
 * TypeIdentifier [ClassImplements] EnumBody}). Rien de nouveau côté héritage multiple de type :
 * les mêmes règles que {@code c17_heritage_multiple_types} s'appliquent, seule la classe
 * implémentée est ici un enum plutôt qu'une classe ordinaire.
 */
class Ex04_EnumImplementantDesInterfaces {

    interface Libelle {
        String libelle();
    }

    interface CouleurAffichage {
        String couleurHexa();
    }

    enum StatutCommande implements Libelle, CouleurAffichage {
        EN_ATTENTE("En attente", "#F5A623"),
        EXPEDIEE("Expédiée", "#4A90D9"),
        LIVREE("Livrée", "#2ECC71"),
        ANNULEE("Annulée", "#E74C3C");

        StatutCommande(String libelle, String couleurHexa) {
            this.libelle = libelle;
            this.couleurHexa = couleurHexa;
        }

        private final String libelle;
        private final String couleurHexa;

        @Override
        public String libelle() {
            return libelle;
        }

        @Override
        public String couleurHexa() {
            return couleurHexa;
        }
    }

    static void afficher(Libelle etCouleur) {
        System.out.println(etCouleur.libelle() + " (" + ((CouleurAffichage) etCouleur).couleurHexa()
                + ")");
    }

    public static void main(String[] args) {
        System.out.println("=== StatutCommande implémente deux interfaces, exactement comme "
                + "une classe ordinaire (c17) ===");
        for (StatutCommande statut : StatutCommande.values()) {
            afficher(statut);
        }
    }
}
