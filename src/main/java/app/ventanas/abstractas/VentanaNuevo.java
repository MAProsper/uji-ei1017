package app.ventanas.abstractas;

import app.ventanas.claeses.VentanaError;
import app.ventanas.interfaces.Textbox;

import java.util.Optional;

import static helpers.estaticos.Arguments.*;

public abstract class VentanaNuevo extends Ventana {
    public VentanaNuevo(final Textbox[] textboxes) {
        super(
                "Nuevo",
                "Rellena los attributos de la nueva entrada",
                true, textboxes, Button.values());
    }

    @Override
    public Optional<Ventana> pressButton(final app.ventanas.interfaces.Button button) {
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Ventana ventana = null;

        switch ((Button) button) {
            case CREAR:
                ventana = VentanaError.attempt(this::crear);
                break;
            case VOLVER:
                break;
            default:
                throw new ValidationException("Button no clasificado");
        }

        return Optional.ofNullable(ventana);
    }

    protected abstract void crear();

    public enum Button implements app.ventanas.interfaces.Button {
        CREAR("Crear"),
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
