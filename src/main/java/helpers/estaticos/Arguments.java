package helpers.estaticos;

import java.util.Collection;

public final class Arguments {
    private Arguments() {
    }

    public static void validate(final String message, final boolean valid) {
        if (!valid) throw new ValidationException(message);
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
        return validate(String.format("%s no sigue el patron %s", name, regex), referenceNotNull(name, s), s.matches(regex));
    }

    public static <T extends Collection<?>> T collectionWithoutNull(final String name, final T ref) {
        return validate(name + " contiene algun nulo", referenceNotNull(name, ref), !ref.contains(null));
    }

    public static <T> T[] collectionWithoutNull(final String name, final T[] ref) {
        referenceNotNull(name, ref);
        for (T item : ref) validate(name + " contiene algun nulo", item != null);
        return ref;
    }

    public static class ValidationException extends IllegalArgumentException {
        private static final long serialVersionUID = 6994228823580948329L;

        public ValidationException(final String message) {
            super(message);
        }
    }
}
