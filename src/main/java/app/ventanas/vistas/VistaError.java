package app.ventanas.vistas;

import app.ventanas.abstractas.Vista;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.acciones.AccionError;
import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;
import helpers.estaticos.Arguments;

import java.util.function.Function;
import java.util.function.Supplier;

import static helpers.estaticos.Arguments.ValidationException;
import static helpers.estaticos.Arguments.stringNotEmpty;

// Relacion Vista-Controlador
public class VistaError extends VistaPropia {

    // Vista (define la vista contreta)
    public VistaError(final String message) {
        super(
                "Error",
                stringNotEmpty("Message", message),
                Table.empty(), Textbox.empty(), AccionError.values());
    }

    public VistaError(final ValidationException exception) {
        this(exception.getMessage());
    }

    public static Vista attempt(final Runnable func) {
        // Tiene muchos nulos, pressButton espera nulos, no sabemos como limpiarlo más
        return attempt(() -> {
            func.run();
            return null;
        }, v -> null);
    }

    public static <T> Vista attempt(final Supplier<T> func, final Function<T, Vista> result) {
        Vista ventana = null;
        T value = null;

        try {
            value = func.get();
        } catch (Arguments.ValidationException e) {
            ventana = new VistaError(e);
        }

        return ventana == null ? result.apply(value) : ventana;
    }
}
