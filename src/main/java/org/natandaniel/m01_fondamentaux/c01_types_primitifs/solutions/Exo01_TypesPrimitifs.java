package org.natandaniel.m01_fondamentaux.c01_types_primitifs.solutions;

/** Solution de référence — Types primitifs : tailles et domaines. */
class Exo01_TypesPrimitifs {

    static int tailleEnBits(String type) {
        return switch (type) {
            case "byte"   -> Byte.SIZE;
            case "short"  -> Short.SIZE;
            case "int"    -> Integer.SIZE;
            case "long"   -> Long.SIZE;
            case "char"   -> Character.SIZE;
            case "float"  -> Float.SIZE;
            case "double" -> Double.SIZE;
            default -> throw new IllegalArgumentException("Type inconnu : " + type);
        };
    }

    static long valeurMaximale(String type) {
        return switch (type) {
            case "byte"  -> Byte.MAX_VALUE;
            case "short" -> Short.MAX_VALUE;
            case "int"   -> Integer.MAX_VALUE;
            case "long"  -> Long.MAX_VALUE;
            case "char"  -> Character.MAX_VALUE;   // 65535 : char est non signé
            default -> throw new IllegalArgumentException("Type sans MAX_VALUE entier : " + type);
        };
    }
}
