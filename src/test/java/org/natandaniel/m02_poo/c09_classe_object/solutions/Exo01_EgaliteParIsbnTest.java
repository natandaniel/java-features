package org.natandaniel.m02_poo.c09_classe_object.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class Exo01_EgaliteParIsbnTest {

    @Test
    void equals_estReflexif() {
        Exo01_EgaliteParIsbn.Livre livre = new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Java Efficace");

        assertTrue(livre.equals(livre));
    }

    @Test
    void equals_estSymetrique_memeIsbn() {
        Exo01_EgaliteParIsbn.Livre a = new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Java Efficace");
        Exo01_EgaliteParIsbn.Livre b = new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Effective Java");

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    void equals_estTransitif_memeIsbn() {
        Exo01_EgaliteParIsbn.Livre a = new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Java Efficace");
        Exo01_EgaliteParIsbn.Livre b = new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Effective Java");
        Exo01_EgaliteParIsbn.Livre c = new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Java, la référence");

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    void equals_isbnDifferent_renvoieFaux() {
        Exo01_EgaliteParIsbn.Livre a = new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Java Efficace");
        Exo01_EgaliteParIsbn.Livre b = new Exo01_EgaliteParIsbn.Livre("978-2-0000-0000-0", "Java Efficace");

        assertFalse(a.equals(b));
    }

    @Test
    void equals_avecNull_renvoieFaux() {
        Exo01_EgaliteParIsbn.Livre livre = new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Java Efficace");

        assertFalse(livre.equals(null));
    }

    @Test
    void equals_avecTypeIncompatible_renvoieFaux() {
        Exo01_EgaliteParIsbn.Livre livre = new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Java Efficace");

        assertFalse(livre.equals("978-2-1234-5678-9"));
    }

    @Test
    void hashCode_coherentAvecEquals_memeIsbn() {
        Exo01_EgaliteParIsbn.Livre a = new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Java Efficace");
        Exo01_EgaliteParIsbn.Livre b = new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Effective Java");

        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void hashCode_isbnDifferent_produitGeneralementUnHashCodeDifferent() {
        Exo01_EgaliteParIsbn.Livre a = new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Java Efficace");
        Exo01_EgaliteParIsbn.Livre b = new Exo01_EgaliteParIsbn.Livre("978-2-0000-0000-0", "Java Efficace");

        // Non garanti par le contrat (seule l'implication inverse l'est), mais vrai ici : String.hashCode()
        // est bien réparti pour des isbn distincts.
        assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void hashSet_dedoublonneParIsbn_maisPasParTitre() {
        Set<Exo01_EgaliteParIsbn.Livre> livres = new HashSet<>();
        livres.add(new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Java Efficace"));
        livres.add(new Exo01_EgaliteParIsbn.Livre("978-2-1234-5678-9", "Effective Java")); // même isbn
        livres.add(new Exo01_EgaliteParIsbn.Livre("978-2-0000-0000-0", "Un autre livre")); // isbn différent

        assertEquals(2, livres.size());
    }
}
