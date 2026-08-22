package org.natandaniel.m02_poo.c14_clonage.lecon;

import java.util.Arrays;

/**
 * Leçon 3/4 — le piège : {@code super.clone()} fait une copie "field-for-field" — Javadoc
 * {@code Object#clone()} : "initializes all its fields with exactly the contents of the
 * corresponding fields of this object... the contents of the fields are not themselves
 * cloned... a 'shallow copy' of this object, not a 'deep copy'". Un champ référence (ici un
 * tableau) reste donc PARTAGÉ entre l'original et le clone.
 */
class Ex03_ClonageSuperficiel {

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
                return (Commande) super.clone(); // copie superficielle : 'articles' reste partagé
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }
    }

    public static void main(String[] args) {
        Commande originale = new Commande("CMD-003", new String[] {"Clavier", "Souris"});
        Commande clone = originale.clone();

        clone.remplacerArticle("Écran", 0);

        System.out.println("=== clone superficiel : le tableau interne reste partagé ===");
        System.out.println("originale.articles() = " + Arrays.toString(originale.articles()));
        System.out.println("clone.articles()     = " + Arrays.toString(clone.articles()));
        System.out.println("Modifier le clone a aussi modifié l'originale !");
    }
}
