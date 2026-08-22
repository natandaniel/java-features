package org.natandaniel.m02_poo.c12_final_classe_methode.lecon;

/**
 * Leçon 3/4 — deux cas où une méthode est déjà impossible à redéfinir SANS le mot-clé {@code
 * final} (JLS §8.4.3.3) : une méthode déclarée dans une classe {@code final}, et une méthode
 * {@code private} (qui n'est de toute façon pas héritée, c07_polymorphisme). Ajouter {@code
 * final} dans ces deux cas est légal mais redondant.
 */
class Ex03_MethodeEffectivementFinale {

    static final class Montant {
        private final long centimes;

        Montant(long centimes) {
            this.centimes = centimes;
        }

        // "final" ici est légal mais redondant : Montant étant déjà final (Ex01), AUCUNE
        // sous-classe ne peut jamais exister, donc aucune méthode ne peut jamais être redéfinie
        // — avec ou sans le mot-clé.
        final long enCentimes() {
            return centimes;
        }

        // Sans "final", cette méthode est tout aussi impossible à redéfinir : c'est la classe
        // final qui l'interdit, pas le mot-clé sur la méthode elle-même.
        long enEuros() {
            return centimes / 100;
        }
    }

    static class CompteBancaire {
        private long soldeCentimes;

        CompteBancaire(long soldeInitialCentimes) {
            this.soldeCentimes = soldeInitialCentimes;
        }

        // "private" seul suffit déjà à interdire toute redéfinition : une méthode private n'est
        // pas héritée (JLS §8.4.8.1, c07_polymorphisme). Ajouter "final" ici est légal mais tout
        // aussi redondant que dans Montant ci-dessus.
        private final boolean journaliserOperation(String libelle) {
            System.out.println("[journal] " + libelle);
            return true;
        }

        void deposer(long montantCentimes) {
            journaliserOperation("dépôt de " + montantCentimes);
            soldeCentimes += montantCentimes;
        }
    }

    public static void main(String[] args) {
        Montant montant = new Montant(150);
        System.out.println("=== final redondant sur une méthode déjà impossible à redéfinir ===");
        System.out.println("enCentimes() [final explicite]  = " + montant.enCentimes());
        System.out.println("enEuros()    [pas de 'final']   = " + montant.enEuros());
        System.out.println("Les deux sont déjà irrédéfinissables : Montant est une classe final.");

        System.out.println();
        new CompteBancaire(0).deposer(500);
        System.out.println("journaliserOperation est private : jamais héritée, donc jamais");
        System.out.println("redéfinie, que 'final' soit écrit ou non.");
    }
}
