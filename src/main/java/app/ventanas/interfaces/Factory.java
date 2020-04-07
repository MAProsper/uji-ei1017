package app.ventanas.interfaces;

import static helpers.estaticos.Arguments.ValidationException;

public interface Factory {
    ValidationException errorInstance = new ValidationException("Los argumentos no son validos para esta clase");
}
