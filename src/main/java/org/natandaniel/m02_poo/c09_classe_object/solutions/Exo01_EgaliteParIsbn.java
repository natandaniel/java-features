package org.natandaniel.m02_poo.c09_classe_object.solutions;

/** Solution de référence — egalite basée sur isbn, equals()/hashCode() cohérents entre eux. */
class Exo01_EgaliteParIsbn {

    static class Livre {
        String isbn;
        String titre;

        Livre(String isbn, String titre) {
            this.isbn = isbn;
            this.titre = titre;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Livre)) {
                return false;
            }
            Livre autre = (Livre) obj;
            return isbn.equals(autre.isbn);
        }

        @Override
        public int hashCode() {
            return isbn.hashCode();
        }
    }
}
