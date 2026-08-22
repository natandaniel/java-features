package org.natandaniel.m02_poo.c20_enumerations.lecon;

import java.util.HashMap;
import java.util.Map;

/**
 * Leçon 5/5 — piège subtil (JLS §8.9.2, {@code Example 8.9.2-2}) : il est interdit, à la
 * compilation, de référencer depuis le constructeur d'un enum un champ {@code static} qui n'est
 * pas une constante ({@code final} + valeur d'expression constante, §4.12.4). Sans cette règle,
 * le code compilerait mais échouerait à l'exécution avec une {@code NullPointerException} — les
 * constantes d'un enum sont initialisées **avant** les autres champs {@code static} déclarés
 * dans le corps (§8.9.3), donc {@code codesConnus} vaudrait encore {@code null} au moment où le
 * premier constructeur s'exécute. La correction : peupler la table dans un bloc d'initialisation
 * {@code static} placé après les constantes, exécuté seulement une fois toutes déjà créées.
 */
class Ex05_PiegeCirculariteInitialisation {

    enum CategorieCours {
        BACKEND("BE"), FRONTEND("FE"), DEVOPS("OPS");

        CategorieCours(String code) {
            this.code = code;
            // Ne compile pas (JLS §8.9.2) : codesConnus est un champ static non-constant,
            // référencé depuis le constructeur — erreur de compilation, précisément pour éviter
            // la NullPointerException qui surviendrait sinon (codesConnus vaut encore null ici).
            //
            // codesConnus.put(code, this);
        }

        private final String code;

        private static final Map<String, CategorieCours> codesConnus = new HashMap<>();

        // Bloc d'initialisation static (c11_blocs_initialisation), exécuté après que toutes les
        // constantes ont été créées : codesConnus n'est peuplée qu'à ce moment-là.
        static {
            for (CategorieCours categorie : values()) {
                codesConnus.put(categorie.code, categorie);
            }
        }

        static CategorieCours parCode(String code) {
            return codesConnus.get(code);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== codesConnus est peuplée depuis un bloc static, pas depuis le "
                + "constructeur ===");
        System.out.println(CategorieCours.parCode("BE"));
        System.out.println(CategorieCours.parCode("OPS"));
    }
}
