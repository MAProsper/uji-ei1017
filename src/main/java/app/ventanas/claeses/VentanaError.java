package app.ventanas.claeses;

import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Textbox;

import static helpers.estaticos.Arguments.ValidationException;
import static helpers.estaticos.Arguments.stringNotEmpty;

public class VentanaError extends Ventana {
    public VentanaError(final String message) {
        super(
                "Error",
                stringNotEmpty("message", message),
                false, Textbox.empty(), Button.values());
    }

    public VentanaError(final ValidationException exception) {
        this(exception.getMessage());
    }

    @Override
    protected void update() {
    }

    @Override
    protected Ventana handle(final app.ventanas.interfaces.Button button) {
        return null;
    }

    private enum Button implements app.ventanas.interfaces.Button {
        VOLVER("Volver");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
