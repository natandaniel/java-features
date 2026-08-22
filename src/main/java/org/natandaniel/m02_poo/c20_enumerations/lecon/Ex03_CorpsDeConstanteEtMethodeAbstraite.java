package org.natandaniel.m02_poo.c20_enumerations.lecon;

/**
 * Leçon 3/5 — une constante peut avoir un corps de classe (JLS §8.9.1, {@code Example 8.9.3-3}) :
 * elle déclare alors une sous-classe anonyme de l'enum, qui peut redéfinir une méthode. C'est
 * l'alternative à un {@code switch} externe pour "ajouter" un comportement par constante (§8.9.3).
 * Dès qu'au moins une constante a un corps, l'enum devient implicitement {@code sealed} — ses
 * seules sous-classes permises sont les corps anonymes de ses constantes (JLS §8.1.1.2, cf.
 * {@code c19_classes_scellees}) ; une méthode abstraite n'est autorisée que si {@code toutes} les
 * constantes fournissent une implémentation concrète (§8.9.2), sans quoi c'est une erreur de
 * compilation.
 */
class Ex03_CorpsDeConstanteEtMethodeAbstraite {

    enum TypeRemise {
        AUCUNE {
            @Override
            double appliquer(double prix) {
                return prix;
            }
        },
        POURCENTAGE_ETUDIANT {
            @Override
            double appliquer(double prix) {
                return prix * 0.90;
            }
        },
        MONTANT_FIXE_PARRAINAGE {
            @Override
            double appliquer(double prix) {
                return Math.max(0, prix - 10.0);
            }
        };

        // Erreur de compilation sans corps sur AUCUNE, POURCENTAGE_ETUDIANT et
        // MONTANT_FIXE_PARRAINAGE (JLS §8.9.2) : les trois constantes doivent implémenter
        // appliquer(...), sinon TypeRemise resterait abstraite sans jamais pouvoir être
        // instanciée.
        abstract double appliquer(double prix);
    }

    public static void main(String[] args) {
        double prixInitial = 49.90;

        System.out.println("=== Chaque constante porte sa propre implémentation de appliquer() "
                + "===");
        for (TypeRemise remise : TypeRemise.values()) {
            System.out.printf("%-25s %.2f €%n", remise.name(), remise.appliquer(prixInitial));
        }

        System.out.println("=== La classe anonyme d'une constante à corps est une sous-classe "
                + "de TypeRemise ===");
        System.out.println(TypeRemise.POURCENTAGE_ETUDIANT.getClass().getSuperclass());
    }
}
