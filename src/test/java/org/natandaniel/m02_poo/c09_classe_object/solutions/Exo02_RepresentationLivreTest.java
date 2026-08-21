package org.natandaniel.m02_poo.c09_classe_object.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo02_RepresentationLivreTest {

    @Test
    void toString_formateIsbnEtTitre() {
        Exo02_RepresentationLivre.Livre livre =
                new Exo02_RepresentationLivre.Livre("978-2-1234-5678-9", "Java Efficace");

        assertEquals("Livre[isbn=978-2-1234-5678-9, titre=Java Efficace]", livre.toString());
    }

    @Test
    void toString_estAppeleImplicitementParConcatenation() {
        Exo02_RepresentationLivre.Livre livre =
                new Exo02_RepresentationLivre.Livre("978-2-0000-0000-0", "Effective Java");

        String resultat = "" + livre;

        assertEquals("Livre[isbn=978-2-0000-0000-0, titre=Effective Java]", resultat);
    }
}
