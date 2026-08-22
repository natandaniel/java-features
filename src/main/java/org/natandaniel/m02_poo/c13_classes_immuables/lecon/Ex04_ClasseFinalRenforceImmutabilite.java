package org.natandaniel.m02_poo.c13_classes_immuables.lecon;

/**
 * Leçon 4/4 — pourquoi une classe immuable est souvent aussi déclarée {@code final}
 * (c12_final_classe_methode) : les champs {@code private final} interdisent déjà à toute
 * sous-classe de MODIFIER l'état hérité, mais rien n'empêche une sous-classe de redéfinir un
 * accesseur non-final pour le rendre impur — brisant la garantie « toujours la même valeur » que
 * l'appelant associe à un type immuable.
 */
class Ex04_ClasseFinalRenforceImmutabilite {

    static class Identifiant {
        private final String valeur;

        Identifiant(String valeur) {
            this.valeur = valeur;
        }

        // Pas final : rien n'empêche une sous-classe de redéfinir ce comportement.
        String valeur() {
            return valeur;
        }
    }

    static class IdentifiantInstable extends Identifiant {
        private int appels = 0;

        IdentifiantInstable(String valeur) {
            super(valeur);
        }

        // Ne touche à aucun champ final de la superclasse : impossible. Mais redéfinit
        // l'accesseur pour renvoyer une valeur différente à chaque appel — Identifiant n'a plus
        // rien d'immuable, malgré son champ 'valeur' toujours final.
        @Override
        String valeur() {
            appels++;
            return super.valeur() + "#" + appels;
        }
    }

    public static void main(String[] args) {
        Identifiant identifiant = new IdentifiantInstable("commande-42");

        System.out.println("=== accesseur non-final : la sous-classe casse l'immuabilité ===");
        System.out.println("premier appel  : " + identifiant.valeur());
        System.out.println("deuxième appel : " + identifiant.valeur());
        System.out.println("Même objet, même champ final 'valeur' jamais réassigné, pourtant");
        System.out.println("deux résultats différents : la garantie d'immuabilité ne tient plus.");
        System.out.println();
        System.out.println("Correction (c12_final_classe_methode) : déclarer valeur() 'final', ou");
        System.out.println("directement la classe Identifiant 'final' — comme Montant en c12.");
    }
}
