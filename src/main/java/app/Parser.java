package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static helpers.estaticos.Arguments.ValidationException;
import static helpers.estaticos.Arguments.stringNotEmpty;

final public class Parser {
    private Parser() {
    }

    protected static ValidationException error(final String name, final String format) {
        stringNotEmpty("Name", name);
        stringNotEmpty("Format", format);
        final String message = String.format("%s no tiene el formato correcto (%s)", name, format);
        return new ValidationException(message);
    }

    public static LocalDateTime fecha(final String name, final String fecha) {
        try {
            return LocalDateTime.parse(fecha, Formatter.fechaFormat);
        } catch (DateTimeParseException ignore) {
            throw error(name, "YYYY-MM-DD hh:mm");
        }
    }

    public static double real(final String name, final String numero) {
        try {
            return Double.parseDouble(numero);
        } catch (NumberFormatException ignored) {
            throw error(name, "n.n");
        }
    }

    public static int entreo(final String name, final String numero) {
        try {
            return Integer.parseInt(numero);
        } catch (NumberFormatException ignored) {
            throw error(name, "n");
        }
    }
}
