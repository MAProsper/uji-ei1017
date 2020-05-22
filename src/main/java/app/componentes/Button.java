package app.componentes;

import helpers.estaticos.Arguments;
import helpers.interfaces.Description;

public interface Button extends Description {
    Arguments.ValidationException MISSING = new Arguments.ValidationException("Button no clasificado");
}
