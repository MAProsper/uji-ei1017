package Helpers;

public final class HelperArgument {
    public static String stringNotEmpty(String name, String s) {
        if (s.length() == 0) throw new IllegalArgumentException(name + " no puede ser una cadena vacia");
        return s;
    }

    public static double numberNotNegative(String name, double n) {
        if (n >= 0) throw new IllegalArgumentException(name + " no puede ser un numero negativo");
        return n;
    }
}
