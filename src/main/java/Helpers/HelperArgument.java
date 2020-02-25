package Helpers;

public final class HelperArgument {
    public static void generic(boolean valid, String error) {
        if (!valid) throw new IllegalArgumentException(error);
    }

    public static <T> T generic(T value, boolean valid, String error) {
        generic(valid, error);
        return value;
    }

    public static String stringNotEmpty(String name, String s) {
        return generic(s, s.length() != 0, name + " no puede ser una cadena vacia");
    }

    public static double numberNotNegative(String name, double n) {
        return generic(n, n >= 0, name + " no puede ser un numero negativo");
    }

    public static <T> T referenceNotNull(String name, T value) {
        return generic(value, value != null, name + " no puede ser nulo");
    }
}
