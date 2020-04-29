package app.ventanas.claeses;

import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static helpers.estaticos.Arguments.*;

public class VentanaError extends Ventana {
    public VentanaError(final String message) {
        super(
                "Error",
                stringNotEmpty("Message", message),
                Table.empty(), Textbox.empty(), Button.values());
    }

    public VentanaError(final ValidationException exception) {
        this(exception.getMessage());
    }

    public static Ventana attempt(final Runnable func) {
        //Tiene muchos nulos, pressButton espera nulos, no sabemos como limpiarlo más
        return attempt(() -> {
            func.run();
            return null;
        }, v -> null);
    }

    public static <T> Ventana attempt(final Supplier<T> func, final Function<T, Ventana> result) {
        Ventana ventana = null;
        T value = null;

        try {
            value = func.get();
        } catch (ValidationException e) {
            ventana = new VentanaError(e);
        }

        return ventana == null ? result.apply(value) : ventana;
    }

    @Override
    public Optional<Ventana> pressButton(final app.ventanas.interfaces.Button button) {
        validate("Button tiene que ser esta ventana", button instanceof Button);
        validate("Ventana no clasificada", button == Button.VOLVER);
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
}
