package org.natandaniel.m01_fondamentaux.c09_modele_memoire.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo02_RecursionTest {

    @Test
    void sommeIterative_casNominaux() {
        assertEquals(0, Exo02_Recursion.sommeIterative(0));
        assertEquals(1, Exo02_Recursion.sommeIterative(1));
        assertEquals(5050, Exo02_Recursion.sommeIterative(100));
    }

    @Test
    void sommeIterative_entreeNegative() {
        assertEquals(0, Exo02_Recursion.sommeIterative(-1));
        assertEquals(0, Exo02_Recursion.sommeIterative(Integer.MIN_VALUE));
    }

    @Test
    void sommeRecursive_memeResultatQueIterative() {
        for (int n : new int[]{0, 1, 2, 10, 100, 1000}) {
            assertEquals(Exo02_Recursion.sommeIterative(n), Exo02_Recursion.sommeRecursive(n),
                    "divergence pour n = " + n);
        }
    }

    @Test
    void sommeRecursive_entreeNegative() {
        assertEquals(0, Exo02_Recursion.sommeRecursive(-1));
    }

    @Test
    void sommeIterative_supporteUnGrandNSansToucherLaPile() {
        // Un seul frame : aucun risque de débordement, quel que soit n.
        assertEquals(500_000_500_000L, Exo02_Recursion.sommeIterative(1_000_000));
    }

    @Test
    void sommeRecursive_saturelaPilePourUnGrandN() {
        // Un frame par appel : la pile cède avant la fin du calcul.
        assertThrows(StackOverflowError.class, () -> Exo02_Recursion.sommeRecursive(10_000_000));
    }

    @Test
    void profondeurAtteinte_ordreDeGrandeur() {
        int profondeur = Exo02_Recursion.profondeurAtteinte();

        // Assertion volontairement lâche : la valeur exacte dépend de -Xss, de la taille
        // des frames et du JIT. Seul l'ordre de grandeur est reproductible.
        assertTrue(profondeur > 1_000, "profondeur inattendue : " + profondeur);
    }
}
