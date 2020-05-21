package app.ventanas.vistas.clases;

import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.abstractas.VistaVolver;
import helpers.estaticos.Arguments;

import java.util.function.Function;
import java.util.function.Supplier;

import static helpers.estaticos.Arguments.ValidationException;
import static helpers.estaticos.Arguments.stringNotEmpty;

public class VistaError extends VistaVolver {
    public VistaError(final String message) {
        super("Error", stringNotEmpty("Message", message));
    }

    public VistaError(final ValidationException exception) {
        this(exception.getMessage());
    }

    public static Vista attempt(final Runnable func) {
        // Inetnta funcion, si funciona ninguna vista, si no vista error
        return attempt(() -> {
            func.run();
            return null;
        }, v -> null);
    }

    public static <T> Vista attempt(final Supplier<T> func, final Function<T, Vista> map) {
        // Inetnta funcion, si funciona map trasforma resultado a vista, si no vista error
        Vista vista = null;
        T value = null;

        try {
            value = func.get();
        } catch (Arguments.ValidationException e) {
            vista = new VistaError(e);
        }

        return vista == null ? map.apply(value) : vista;
    }
}
