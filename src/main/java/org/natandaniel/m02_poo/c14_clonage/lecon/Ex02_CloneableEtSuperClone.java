package org.natandaniel.m02_poo.c14_clonage.lecon;

/**
 * Leçon 2/4 — la plomberie correcte : {@code implements Cloneable} (interface marqueur, sans
 * aucune méthode — Javadoc {@code Cloneable} : "does not contain the clone method... indicates
 * to Object.clone() that it is legal... to make a field-for-field copy"), puis republication de
 * {@code clone()} en {@code public}, qui délègue à {@code super.clone()}. Une fois
 * {@code Cloneable} implémenté, {@code CloneNotSupportedException} ne peut plus réellement
 * survenir ici : on la convertit en {@code AssertionError} (branche inatteignable en pratique).
 */
class Ex02_CloneableEtSuperClone {

    static class Commande implements Cloneable {
        private final String reference;

        Commande(String reference) {
            this.reference = reference;
        }

        String reference() {
            return reference;
        }

        // Retour covariant (c07_polymorphisme) : Commande, pas Object — l'appelant n'a pas à
        // caster le résultat.
        @Override
        public Commande clone() {
            try {
                return (Commande) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }
    }

    public static void main(String[] args) {
        Commande originale = new Commande("CMD-002");
        Commande clone = originale.clone();

        System.out.println("=== Cloneable + super.clone() : identité de l'objet cloné ===");
        System.out.println("clone != originale : " + (clone != originale));
        System.out.println("clone.getClass() == originale.getClass() : "
                + (clone.getClass() == originale.getClass()));
        System.out.println("clone.reference().equals(originale.reference()) : "
                + clone.reference().equals(originale.reference()));
    }
}
