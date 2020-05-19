package app.ventanas.abstractas;

import app.ventanas.claeses.VentanaError;
import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;

import java.util.Optional;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador (abstracta para crear objectos)
public abstract class VentanaNuevo extends Ventana {

    // Vista (define la vista contreta)
    public VentanaNuevo(final Textbox[] textboxes) {
        super(
                "Nuevo",
                "Rellena los attributos de la nueva entrada",
                Table.empty(), textboxes, Button.values());
    }

    // Controlador (gestiona la acción del usuario)
    @Override
    public Optional<Gestionable> pressButton(final app.ventanas.interfaces.Button button) { // Gestiona la accion del usuario
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Gestionable ventana = null;

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

    protected abstract void crear();
}
