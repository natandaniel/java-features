package org.natandaniel.m02_poo.c08_mot_cle_super.solutions;

/** Solution de référence — accès explicite au champ masqué via super.champ. */
class Exo01_ChampMasqueParSuper {

    static class Produit {
        String origine = "Origine inconnue";
    }

    static class ProduitImporte extends Produit {
        String origine = "Import direct";

        String origineHeritee() {
            return super.origine;
        }
    }
}
