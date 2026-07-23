package org.natandaniel.m01_fondamentaux.c09_modele_memoire.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class Exo03_AccessibiliteTest {

    @Test
    void cacheBorne_sousLaCapacite_toutEstRetenu() {
        Map<String, String> cache = Exo03_Accessibilite.cacheBorne(List.of("a", "b"), 5);

        assertEquals(2, cache.size());
        assertEquals("valeur:a", cache.get("a"));
        assertEquals("valeur:b", cache.get("b"));
    }

    @Test
    void cacheBorne_neDepasseJamaisLaCapacite() {
        List<String> cles = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            cles.add("k" + i);
        }
        assertEquals(3, Exo03_Accessibilite.cacheBorne(cles, 3).size());
    }

    @Test
    void cacheBorne_retientLesPlusRecentes() {
        Map<String, String> cache = Exo03_Accessibilite.cacheBorne(List.of("a", "b", "c"), 2);

        assertIterableEquals(List.of("b", "c"), cache.keySet());   // ordre d'insertion préservé
        assertFalse(cache.containsKey("a"));                       // évincée → devenue collectable
    }

    @Test
    void cacheBorne_capaciteNulleOuNegative_neRetientRien() {
        assertTrue(Exo03_Accessibilite.cacheBorne(List.of("a", "b"), 0).isEmpty());
        assertTrue(Exo03_Accessibilite.cacheBorne(List.of("a", "b"), -1).isEmpty());
    }

    @Test
    void cacheBorne_listeVide() {
        assertTrue(Exo03_Accessibilite.cacheBorne(List.of(), 3).isEmpty());
    }

    @Test
    void registreFinal_inscriptionsSimples() {
        assertIterableEquals(List.of("a", "b"),
                Exo03_Accessibilite.registreFinal(List.of("+a", "+b")));
    }

    @Test
    void registreFinal_desinscriptionRetireLEntree() {
        assertIterableEquals(List.of("b"),
                Exo03_Accessibilite.registreFinal(List.of("+a", "+b", "-a")));
    }

    @Test
    void registreFinal_inscriptionEnDoubleNeCreePasDeDoublon() {
        assertIterableEquals(List.of("b"),
                Exo03_Accessibilite.registreFinal(List.of("+a", "+b", "-a", "+b")));
    }

    @Test
    void registreFinal_desinscriptionDUnAbsentEstSansEffet() {
        assertIterableEquals(List.of("a"),
                Exo03_Accessibilite.registreFinal(List.of("+a", "-inconnu")));
    }

    @Test
    void registreFinal_ordreDePremiereInscriptionPreserve() {
        assertIterableEquals(List.of("a", "b", "c"),
                Exo03_Accessibilite.registreFinal(List.of("+a", "+b", "+c", "+a")));
    }

    @Test
    void registreFinal_reinscriptionApresDesinscription_repartEnFin() {
        assertIterableEquals(List.of("b", "a"),
                Exo03_Accessibilite.registreFinal(List.of("+a", "+b", "-a", "+a")));
    }

    @Test
    void registreFinal_journalVide() {
        assertTrue(Exo03_Accessibilite.registreFinal(List.of()).isEmpty());
    }

    @Test
    void toujoursAccessible_vraiTantQuInscrit() {
        assertTrue(Exo03_Accessibilite.toujoursAccessible(List.of("+fenetre"), "fenetre"));
    }

    @Test
    void toujoursAccessible_fauxApresDesinscription() {
        // La fuite mémoire, c'est l'oubli de la ligne "-dialogue".
        assertFalse(Exo03_Accessibilite.toujoursAccessible(
                List.of("+fenetre", "+dialogue", "-dialogue"), "dialogue"));
        assertTrue(Exo03_Accessibilite.toujoursAccessible(
                List.of("+fenetre", "+dialogue", "-dialogue"), "fenetre"));
    }

    @Test
    void toujoursAccessible_nomJamaisInscrit() {
        assertFalse(Exo03_Accessibilite.toujoursAccessible(List.of("+a"), "b"));
    }
}
