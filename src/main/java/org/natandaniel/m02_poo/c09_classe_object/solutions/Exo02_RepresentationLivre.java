package org.natandaniel.m02_poo.c09_classe_object.solutions;

/** Solution de référence — représentation lisible via toString(). */
class Exo02_RepresentationLivre {

    static class Livre {
        String isbn;
        String titre;

        Livre(String isbn, String titre) {
            this.isbn = isbn;
            this.titre = titre;
        }

        @Override
        public String toString() {
            return "Livre[isbn=" + isbn + ", titre=" + titre + "]";
        }
    }
}
