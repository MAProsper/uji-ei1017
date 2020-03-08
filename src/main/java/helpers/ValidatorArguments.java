package helpers;

import java.util.Collection;

public final class ValidatorArguments {
    private ValidatorArguments() {
    }

    public static void validate(final String message, final boolean valid) {
        if (!valid) throw new IllegalArgumentException(message);
    }

    public static <T> T validate(final String message, T value, final boolean valid) {
        validate(message, valid);
        return value;
    }

    public static <T extends Number> T numberNotNegative(final String name, final T n) {
        return validate(name + " no puede ser un numero negativo", n, n.doubleValue() >= 0);
    }

    public static <T> T referenceNotNull(final String name, final T ref) {
        return validate(name + " no puede ser nulo", ref, ref != null);
    }

    public static String stringNotEmpty(final String name, final String s) {
        return validate(name + " no puede ser la cadena vacia", referenceNotNull(name, s), s.length() != 0);
    }

    public static String stringMatchesPattern(final String name, final String s, final String regex) {
        return validate(name + " no sigue el patron " + regex, referenceNotNull(name, s), s.matches(regex));
    }

    public static <T extends Collection<?>> T collectionWithoutNull(final String name, final T ref) {
        return validate(name + " contiene algun nulo", referenceNotNull(name, ref), !ref.contains(null));
    }
}
