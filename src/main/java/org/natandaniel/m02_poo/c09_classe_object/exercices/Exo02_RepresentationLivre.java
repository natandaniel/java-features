package org.natandaniel.m02_poo.c09_classe_object.exercices;

/**
 * Exercice — redéfinir toString() pour une représentation lisible (Javadoc java.lang.Object).
 */
class Exo02_RepresentationLivre {

    static class Livre {
        String isbn;
        String titre;

        Livre(String isbn, String titre) {
            this.isbn = isbn;
            this.titre = titre;
        }

        /** Doit renvoyer exactement : "Livre[isbn=<isbn>, titre=<titre>]". */
        @Override
        public String toString() {
            throw new UnsupportedOperationException("À implémenter");
        }
    }
}
