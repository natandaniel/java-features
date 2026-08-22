package org.natandaniel.m02_poo.c20_enumerations.lecon;

/**
 * Leçon 1/5 — {@code enum} (JLS §8.9, {@code // @since Java 5}) déclare un ensemble fixe et fermé
 * d'instances nommées. Chaque constante ({@code EN_ATTENTE}, ...) est un champ implicite
 * {@code public static final} de type {@code StatutCommande} (§8.9.3) ; la classe a pour
 * superclasse directe implicite {@code Enum<StatutCommande>} (§8.9), sans clause {@code extends}
 * possible. Comme il ne peut jamais exister d'autre instance que ces constantes, {@code ==}
 * suffit pour comparer deux statuts (§8.9.1) — pas besoin d'{@code equals}.
 */
class Ex01_EnumDeBase {

    enum StatutCommande {
        EN_ATTENTE, EXPEDIEE, LIVREE, ANNULEE
    }

    // Ne compile pas (JLS §8.9) : un enum n'a pas d'autres instances que ses constantes.
    //
    // StatutCommande statut = new StatutCommande();
    // error: enum classes may not be instantiated

    static boolean estTermine(StatutCommande statut) {
        // switch statement (déjà vu en c11_structures_controle), ici sur des constantes d'enum :
        // pas de qualification (StatutCommande.LIVREE), le type du sélecteur suffit.
        switch (statut) {
            case LIVREE:
            case ANNULEE:
                return true;
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        StatutCommande statut = StatutCommande.EXPEDIEE;

        System.out.println("=== values() renvoie toutes les constantes, dans l'ordre de "
                + "déclaration (§8.9.3) ===");
        for (StatutCommande s : StatutCommande.values()) {
            System.out.println(s.ordinal() + " : " + s.name());
        }

        System.out.println("=== == compare par identité : une seule instance par constante ===");
        System.out.println(statut == StatutCommande.EXPEDIEE);
        System.out.println(statut == StatutCommande.valueOf("EXPEDIEE"));

        System.out.println("=== estTermine(...) ===");
        System.out.println("LIVREE terminé ? " + estTermine(StatutCommande.LIVREE));
        System.out.println("EN_ATTENTE terminé ? " + estTermine(StatutCommande.EN_ATTENTE));
    }
}
