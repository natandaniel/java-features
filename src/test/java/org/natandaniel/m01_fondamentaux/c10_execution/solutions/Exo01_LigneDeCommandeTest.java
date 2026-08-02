package org.natandaniel.m01_fondamentaux.c10_execution.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class Exo01_LigneDeCommandeTest {

    @Test
    void optionsJvm_casNominal() {
        assertIterableEquals(List.of("-Xmx512m", "-cp", "lib/a.jar"),
                Exo01_LigneDeCommande.optionsJvm("java -Xmx512m -cp lib/a.jar Main x y"));
    }

    @Test
    void optionsJvm_aucuneOption() {
        assertTrue(Exo01_LigneDeCommande.optionsJvm("java Main").isEmpty());
    }

    @Test
    void optionsJvm_ligneVideOuEspaces() {
        assertTrue(Exo01_LigneDeCommande.optionsJvm("").isEmpty());
        assertTrue(Exo01_LigneDeCommande.optionsJvm("   ").isEmpty());
    }

    @Test
    void optionsJvm_sansClassePrincipale_toutEstPourLaJvm() {
        assertIterableEquals(List.of("-version"),
                Exo01_LigneDeCommande.optionsJvm("java -version"));
    }

    @Test
    void optionsJvm_classpathEnDernierePosition() {
        // `-cp` attend une valeur qui n'arrive jamais : l'option seule reste une option JVM.
        assertIterableEquals(List.of("-cp"), Exo01_LigneDeCommande.optionsJvm("java -cp"));
    }

    @Test
    void optionsJvm_toutesLesFormesDeClasspath() {
        assertIterableEquals(List.of("-classpath", "lib"),
                Exo01_LigneDeCommande.optionsJvm("java -classpath lib Main"));
        assertIterableEquals(List.of("--class-path", "lib"),
                Exo01_LigneDeCommande.optionsJvm("java --class-path lib Main"));
    }

    @Test
    void valeurDuClasspath_nestPasPriseePourLaClassePrincipale() {
        // Piège : `lib` ne doit pas être vu comme la classe principale.
        assertEquals("Main", Exo01_LigneDeCommande.classePrincipale("java -cp lib Main"));
    }

    @Test
    void classePrincipale_casNominal() {
        assertEquals("Main", Exo01_LigneDeCommande.classePrincipale("java -Xmx512m Main x"));
        assertEquals("org.exemple.Appli",
                Exo01_LigneDeCommande.classePrincipale("java org.exemple.Appli"));
    }

    @Test
    void classePrincipale_absente() {
        assertEquals("", Exo01_LigneDeCommande.classePrincipale("java -version"));
        assertEquals("", Exo01_LigneDeCommande.classePrincipale(""));
    }

    @Test
    void classePrincipale_sansLeMotJava() {
        assertEquals("Main", Exo01_LigneDeCommande.classePrincipale("-Xmx1g Main arg"));
    }

    @Test
    void argumentsProgramme_casNominal() {
        assertIterableEquals(List.of("x", "y"),
                Exo01_LigneDeCommande.argumentsProgramme("java -Xmx512m -cp lib Main x y"));
    }

    @Test
    void argumentsProgramme_aucunArgument() {
        assertTrue(Exo01_LigneDeCommande.argumentsProgramme("java -Xmx512m Main").isEmpty());
    }

    @Test
    void argumentsProgramme_optionApresLaClasse_estUnArgumentDuProgramme() {
        // LE piège : après le nom de la classe, le launcher ne lit plus rien pour lui-même.
        assertIterableEquals(List.of("-Xmx1g", "fichier.txt"),
                Exo01_LigneDeCommande.argumentsProgramme("java -cp lib Main -Xmx1g fichier.txt"));
    }

    @Test
    void argumentsProgramme_sansClasse_aucunArgument() {
        assertTrue(Exo01_LigneDeCommande.argumentsProgramme("java -version").isEmpty());
    }

    @Test
    void espacesMultiples_sontIgnores() {
        assertEquals("Main", Exo01_LigneDeCommande.classePrincipale("java   -Xmx1g    Main   a"));
        assertIterableEquals(List.of("a"),
                Exo01_LigneDeCommande.argumentsProgramme("java   -Xmx1g    Main   a"));
    }

    @Test
    void modeSourceDirect_fichierSource() {
        assertTrue(Exo01_LigneDeCommande.modeSourceDirect("java Main.java a"));
        assertTrue(Exo01_LigneDeCommande.modeSourceDirect("java -Xmx64m dossier/Main.java"));
    }

    @Test
    void modeSourceDirect_classeCompilee() {
        assertFalse(Exo01_LigneDeCommande.modeSourceDirect("java Main a"));
        assertFalse(Exo01_LigneDeCommande.modeSourceDirect("java -version"));
        // Un argument .java APRÈS la classe n'est pas le mode source direct.
        assertFalse(Exo01_LigneDeCommande.modeSourceDirect("java Main Autre.java"));
    }
}
