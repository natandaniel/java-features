package org.natandaniel.m02_poo.c17_heritage_multiple_types.lecon;

/**
 * Leçon 3/4 — variante « côté classe » du conflit vu en {@code c16} (Ex04) : deux interfaces
 * indépendantes (pas de diamant, implémentées directement) portent chacune un {@code default
 * resume()} différent. JLS §8.4.8.4 : "It is a compile-time error if a class C inherits a
 * default method whose signature is override-equivalent with another method inherited by C
 * [...]". La classe doit redéfinir {@code resume()} elle-même, en désambiguïsant chaque appel
 * avec {@code Interface.super.methode()} (§9.4.1.1, déjà utilisé entre interfaces en c16 — tout
 * aussi valide depuis une classe).
 */
class Ex03_ConflitEntreDefautsDInterfacesDirectes {

    interface Remboursable {
        default String resume() {
            return "remboursement";
        }
    }

    interface Facturable {
        default String resume() {
            return "facturation";
        }
    }

    // Ne compile pas sans la redéfinition ci-dessous (JLS §8.4.8.4) : Avoir hériterait à la fois
    // de Remboursable.resume() et de Facturable.resume(), deux default override-équivalents sans
    // qu'aucun ne supplante l'autre.
    //
    // static class Avoir implements Remboursable, Facturable {
    // }
    // error: class Avoir inherits unrelated defaults for resume() from types Remboursable and
    // Facturable

    static class Avoir implements Remboursable, Facturable {
        @Override
        public String resume() {
            return Remboursable.super.resume() + " + " + Facturable.super.resume();
        }
    }

    public static void main(String[] args) {
        Avoir avoir = new Avoir();

        System.out.println("=== Un conflit entre deux default d'interfaces directement "
                + "implémentées se résout comme entre interfaces (c16), depuis la classe ===");
        System.out.println("Résumé : " + avoir.resume());
    }
}
