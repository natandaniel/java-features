package org.natandaniel.m02_poo.c12_final_classe_methode.lecon;

/**
 * Leçon 1/4 — classe {@code final} (JLS §8.1.1.2) : une classe déclarée {@code final} ne peut
 * avoir aucune sous-classe. Utile pour un type valeur dont les invariants (ici : jamais de
 * montant négatif) ne doivent jamais pouvoir être contournés par héritage.
 */
class Ex01_ClasseFinal {

    static final class Montant {
        private final long centimes;

        Montant(long centimes) {
            if (centimes < 0) {
                throw new IllegalArgumentException("un montant ne peut pas être négatif");
            }
            this.centimes = centimes;
        }

        long enCentimes() {
            return centimes;
        }

        Montant plus(Montant autre) {
            return new Montant(this.centimes + autre.centimes);
        }
    }

    // Ne compile pas (JLS §8.1.1.2) : "Because a final class never has any subclasses, the
    // methods of a final class are never overridden."
    //
    // class MontantBonus extends Montant {
    //     // error: cannot inherit from final 'Ex01_ClasseFinal.Montant'
    // }

    public static void main(String[] args) {
        Montant prix = new Montant(1999);
        Montant remise = new Montant(200);
        Montant total = prix.plus(remise);

        System.out.println("=== Montant est final : aucune sous-classe possible ===");
        System.out.println("prix + remise = " + total.enCentimes() + " centimes");
        System.out.println("Aucune sous-classe ne peut redéfinir enCentimes()/plus() pour");
        System.out.println("relâcher l'invariant 'jamais négatif' vérifié dans le constructeur.");
    }
}
