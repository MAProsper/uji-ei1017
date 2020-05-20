package app.ventanas.vistas;

import app.ventanas.abstractas.Vista;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador
public class VistaError extends VistaPropia {

    // Vista (define la vista contreta)
    public VistaError(final String message) {
        super(
                "Error",
                stringNotEmpty("Message", message),
                Table.empty(), Textbox.empty(), Accion.values());
    }

    public VistaError(final ValidationException exception) {
        this(exception.getMessage());
    }

    public enum Accion implements app.ventanas.interfaces.Accion {
        VOLVER("Volver");

        private final String description;

        Accion(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    // Controlador (define el controlador concreto)
    @Override
    public Optional<Vista> pressButton(final app.ventanas.interfaces.Accion accion) {
        validate("Button tiene que ser esta ventana", accion instanceof Accion);
        validate("Acción no clasificada", accion == Accion.VOLVER);
        return Optional.empty();
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
        } catch (ValidationException e) {
            ventana = new VistaError(e);
        }

        return ventana == null ? result.apply(value) : ventana;
    }
}
