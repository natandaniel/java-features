package org.natandaniel.m01_fondamentaux.c01_types_primitifs.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exo01_TypesPrimitifsTest {

    @Test
    void tailleEnBits_tousLesTypes() {
        assertEquals(8, Exo01_TypesPrimitifs.tailleEnBits("byte"));
        assertEquals(16, Exo01_TypesPrimitifs.tailleEnBits("short"));
        assertEquals(16, Exo01_TypesPrimitifs.tailleEnBits("char"));
        assertEquals(32, Exo01_TypesPrimitifs.tailleEnBits("int"));
        assertEquals(32, Exo01_TypesPrimitifs.tailleEnBits("float"));
        assertEquals(64, Exo01_TypesPrimitifs.tailleEnBits("long"));
        assertEquals(64, Exo01_TypesPrimitifs.tailleEnBits("double"));
    }

    @Test
    void valeurMaximale_typesEntiers() {
        assertEquals(127, Exo01_TypesPrimitifs.valeurMaximale("byte"));
        assertEquals(32767, Exo01_TypesPrimitifs.valeurMaximale("short"));
        assertEquals(65535, Exo01_TypesPrimitifs.valeurMaximale("char"));
        assertEquals(Integer.MAX_VALUE, Exo01_TypesPrimitifs.valeurMaximale("int"));
        assertEquals(Long.MAX_VALUE, Exo01_TypesPrimitifs.valeurMaximale("long"));
    }
}
