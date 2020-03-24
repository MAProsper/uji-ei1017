package app;

import helpers.estaticos.Arguments;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

final public class Parser {
    private Parser() {
    }

    protected static <T> T validate(final String name, final T value, final String format) {
        Arguments.stringNotEmpty("Name", name);
        Arguments.stringNotEmpty("Format", format);
        Arguments.validate(String.format("%s no tiene el formato correcto (%s)", name, format), value != null);
        return value;
    }


    public static LocalDateTime fecha(final String name, final String fecha) {
        LocalDateTime parsed = null;

        try {
            parsed = LocalDateTime.parse(fecha, Formatter.fechaFormat);
        } catch (DateTimeParseException ignore) {
        }

        return validate(name, parsed, "YYYY-MM-DD hh:mm");
    }

    public static Double real(final String name, final String numero) {
        Double parsed = null;

        try {
            parsed = Double.parseDouble(numero);
        } catch (NumberFormatException ignored) {
        }

        return validate(name, parsed, "n.n");
    }

    public static Integer entreo(final String name, final String numero) {
        Integer parsed = null;

        try {
            parsed = Integer.parseInt(numero);
        } catch (NumberFormatException ignored) {
        }

        return validate(name, parsed, "n");
    }
}
