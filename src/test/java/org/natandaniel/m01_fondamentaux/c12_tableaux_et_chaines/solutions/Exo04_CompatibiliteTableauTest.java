package org.natandaniel.m01_fondamentaux.c12_tableaux_et_chaines.solutions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo04_CompatibiliteTableauTest {

    @Test
    void peutStocker_valeurCompatibleAvecLeVraiTypeDuTableau() {
        String[] mots = new String[3];
        Object[] tableau = mots;
        assertTrue(Exo04_CompatibiliteTableau.peutStocker(tableau, 0, "bonjour"));
    }

    @Test
    void peutStocker_valeurIncompatibleAvecLeVraiTypeDuTableau() {
        String[] mots = new String[3];
        Object[] tableau = mots;
        assertFalse(Exo04_CompatibiliteTableau.peutStocker(tableau, 0, 42));
    }

    @Test
    void peutStocker_tableauDeclareObjectAcceptantToutObjet() {
        Object[] tableau = new Object[3];
        assertTrue(Exo04_CompatibiliteTableau.peutStocker(tableau, 0, 42));
        assertTrue(Exo04_CompatibiliteTableau.peutStocker(tableau, 1, "texte"));
    }
}
