package org.natandaniel.m02_poo.c09_classe_object.exercices;

/**
 * Exercice — redéfinir equals()/hashCode() selon leur contrat (Javadoc java.lang.Object) :
 * deux Livre sont égaux s'ils ont le même isbn, indépendamment du titre.
 */
class Exo01_EgaliteParIsbn {

    static class Livre {
        String isbn;
        String titre;

        Livre(String isbn, String titre) {
            this.isbn = isbn;
            this.titre = titre;
        }

        /** Deux Livre sont égaux si et seulement si leur isbn est égal (le titre n'entre pas en compte). */
        @Override
        public boolean equals(Object obj) {
            throw new UnsupportedOperationException("À implémenter");
        }

        /** Doit rester cohérent avec equals() : deux Livre égaux doivent avoir le même hashCode(). */
        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
