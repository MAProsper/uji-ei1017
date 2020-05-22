package app.componentes;

import helpers.interfaces.Description;

import static helpers.estaticos.Arguments.ValidationException;

public interface Button extends Description {
    ValidationException MISSING = new ValidationException("Button no clasificado");
}
