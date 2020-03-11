package helpers.estaticos;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

final public class Parse {
    private Parse() {
    }

    static <T> T validate(final String name, final T value) {
        Arguments.referenceNotNull("name", name);
        return Arguments.validate(name + " no tiene el formato correcto", value, value != null);
    }

    public static LocalDate fecha(final String name, final String fecha) {
        LocalDate parsed = null;

        try {
            parsed = LocalDate.parse(fecha);
        } catch (DateTimeParseException ignore) {
        }

        return validate(name, parsed);
    }

    public static Double real(final String name, final String numero) {
        Double parsed = null;

        try {
            parsed = Double.parseDouble(numero);
        } catch (NumberFormatException ignored) {
        }

        return validate(name, parsed);
    }

    public static Integer entreo(final String name, final String numero) {
        Integer parsed = null;

        try {
            parsed = Integer.parseInt(numero);
        } catch (NumberFormatException ignored) {
        }

        return validate(name, parsed);
    }
}
