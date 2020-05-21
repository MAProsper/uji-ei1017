package app.helpers.interfaces;

import static helpers.estaticos.Arguments.ValidationException;
import static helpers.estaticos.Arguments.stringNotEmpty;

public interface Factory {
    static ValidationException error(final String name) {
        stringNotEmpty("Name", name);
        return new ValidationException(name + " no tiene los argumentos requeridos");
    }
}
