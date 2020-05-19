package app.ventanas.claeses;

import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador
public class VentanaError extends Ventana {

    // Vista (define la vista contreta)
    public VentanaError(final String message) {
        super(
                "Error",
                stringNotEmpty("Message", message),
                Table.empty(), Textbox.empty(), Button.values());
    }

    public VentanaError(final ValidationException exception) {
        this(exception.getMessage());
    }

    public static Gestionable attempt(final Runnable func) {
        // Tiene muchos nulos, pressButton espera nulos, no sabemos como limpiarlo más
        return attempt(() -> {
            func.run();
            return null;
        }, v -> null);
    }

    // Controlador (define el controlador concreto)
    @Override
    public Optional<Gestionable> pressButton(final app.ventanas.interfaces.Button button) {  // Gestiona la acción del usuario
        validate("Button tiene que ser esta ventana", button instanceof Button);
        validate("Button no clasificado", button == Button.VOLVER);
        return Optional.empty();
    }

    public enum Button implements app.ventanas.interfaces.Button {
        VOLVER("Volver");

        private final String description;

        Button(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public static <T> Gestionable attempt(final Supplier<T> func, final Function<T, Ventana> result) {
        Gestionable ventana = null;
        T value = null;

        try {
            value = func.get();
        } catch (ValidationException e) {
            ventana = new VentanaError(e);
        }

        return ventana == null ? result.apply(value) : ventana;
    }
}
