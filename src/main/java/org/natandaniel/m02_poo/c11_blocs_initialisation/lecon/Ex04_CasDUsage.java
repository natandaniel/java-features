package org.natandaniel.m02_poo.c11_blocs_initialisation.lecon;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Leçon 4/4 — pourquoi un bloc statique plutôt qu'un simple initialiseur de champ : un
 * initialiseur de champ (JLS §8.3.2) n'accepte qu'une expression ; un bloc statique (JLS §8.7)
 * accepte n'importe quelle suite d'instructions Java (boucle, condition, gestion d'erreur...).
 */
class Ex04_CasDUsage {

    static class RegistreServices {
        static final Map<String, String> NOMS_PAR_CODE;

        // Les couples code/nom viennent de deux tableaux parallèles : les assembler demande une
        // boucle. "NOMS_PAR_CODE = Map.of(...)" ne pourrait pas exprimer ça (une seule expression,
        // pas d'itération) — le bloc statique est ici la seule façon de rester une constante de
        // classe immuable tout en construisant la table par calcul.
        static {
            String[] codes = {"RH", "DEV", "COM", "FIN"};
            String[] noms = {"Ressources humaines", "Développement", "Commercial", "Finance"};

            Map<String, String> table = new HashMap<>();
            for (int i = 0; i < codes.length; i++) {
                table.put(codes[i], noms[i]);
            }
            NOMS_PAR_CODE = Collections.unmodifiableMap(table);
        }

        static String nomDuService(String code) {
            return NOMS_PAR_CODE.getOrDefault(code, "Service inconnu");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Table construite par boucle dans un bloc statique, puis figée ===");
        for (String code : new String[] {"RH", "DEV", "COM", "FIN", "XX"}) {
            System.out.println(code + " -> " + RegistreServices.nomDuService(code));
        }
    }
}
