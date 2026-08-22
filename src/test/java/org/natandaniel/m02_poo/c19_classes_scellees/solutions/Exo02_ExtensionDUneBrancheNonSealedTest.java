package org.natandaniel.m02_poo.c19_classes_scellees.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Exo02_ExtensionDUneBrancheNonSealedTest {

    @Test
    void convertirEnEuros_multiplieLeMontantParLeTaux() {
        Exo02_ExtensionDUneBrancheNonSealed.EspecesEnDevise especes =
                new Exo02_ExtensionDUneBrancheNonSealed.EspecesEnDevise(
                        "PAY-201", 100.0, "USD", 0.92);

        assertEquals(92.0, especes.convertirEnEuros(), 1e-9);
    }

    @Test
    void especesEnDevise_resteUnModePaiement_bienQuEtendantUneBrancheNonSealed() {
        Exo02_ExtensionDUneBrancheNonSealed.EspecesEnDevise especes =
                new Exo02_ExtensionDUneBrancheNonSealed.EspecesEnDevise(
                        "PAY-201", 100.0, "USD", 0.92);

        assertTrue(especes instanceof Exo02_ExtensionDUneBrancheNonSealed.ModePaiement);
        assertEquals("PAY-201", especes.reference());
        assertEquals("USD", especes.devise());
    }
}
