package app;

import helpers.estaticos.Arguments;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

final public class Parser {
    private Parser() {
    }

    protected static String mensage(final String name, final String format) {
        Arguments.stringNotEmpty("Name", name);
        Arguments.stringNotEmpty("Format", format);
        return String.format("%s no tiene el formato correcto (%s)", name, format);
    }

    public static LocalDateTime fecha(final String name, final String fecha) {
        try {
            return LocalDateTime.parse(fecha, Formatter.fechaFormat);
        } catch (DateTimeParseException ignore) {
            throw new Arguments.ValidationException(mensage(name, "YYYY-MM-DD hh:mm"));
        }
    }

    public static double real(final String name, final String numero) {
        try {
            return Double.parseDouble(numero);
        } catch (NumberFormatException ignored) {
            throw new Arguments.ValidationException(mensage(name, "n.n"));
        }
    }

    public static int entreo(final String name, final String numero) {
        try {
            return Integer.parseInt(numero);
        } catch (NumberFormatException ignored) {
            throw new Arguments.ValidationException(mensage(name, "n"));
        }
    }
}
