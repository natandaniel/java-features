package org.natandaniel.m02_poo.c14_clonage.lecon;

import java.util.Arrays;

/**
 * Leçon 4/4 — la correction : cloner (ou copier défensivement, {@code c13_classes_immuables})
 * les champs mutables À L'INTÉRIEUR de {@code clone()}, après l'appel à {@code super.clone()}.
 * Javadoc {@code Object#clone()} : "it may be necessary to modify one or more fields of the
 * object returned by super.clone()... typically, this means copying any mutable objects...
 * and replacing the references to these objects with references to the copies."
 */
class Ex04_ClonageProfond {

    static class Commande implements Cloneable {
        private final String reference;
        private String[] articles;

        Commande(String reference, String[] articles) {
            this.reference = reference;
            this.articles = articles;
        }

        String[] articles() {
            return articles;
        }

        void remplacerArticle(String article, int index) {
            articles[index] = article;
        }

        @Override
        public Commande clone() {
            try {
                Commande clone = (Commande) super.clone();
                clone.articles = articles.clone(); // copie le tableau, pas seulement la référence
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }
    }

    public static void main(String[] args) {
        Commande originale = new Commande("CMD-004", new String[] {"Clavier", "Souris"});
        Commande clone = originale.clone();

        clone.remplacerArticle("Écran", 0);

        System.out.println("=== clone profond : le tableau interne est indépendant ===");
        System.out.println("originale.articles() = " + Arrays.toString(originale.articles()));
        System.out.println("clone.articles()     = " + Arrays.toString(clone.articles()));
        System.out.println("Modifier le clone n'affecte plus l'originale.");
    }
}
