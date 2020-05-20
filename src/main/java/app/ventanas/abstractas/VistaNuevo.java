package app.ventanas.abstractas;

import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;
import app.ventanas.vistas.VistaError;

import java.util.Optional;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador (abstracta para crear objectos)
public abstract class VistaNuevo extends VistaPropia {

    // Vista (define la vista contreta)
    public VistaNuevo(final Textbox[] textboxes) {
        super(
                "Nuevo",
                "Rellena los attributos de la nueva entrada",
                Table.empty(), textboxes, Accion.values());
    }

    public enum Accion implements app.ventanas.interfaces.Accion {
        CREAR("Crear"),
        VOLVER("Volver");

        private final String description;

        Accion(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    // Controlador (gestiona la acción del usuario)
    @Override
    public Optional<Vista> pressButton(final app.ventanas.interfaces.Accion accion) { // Gestiona la accion del usuario
        validate("Acción tiene que ser esta ventana", accion instanceof Accion);
        Vista ventana = null;

        switch ((Accion) accion) {
            case CREAR:
                ventana = VistaError.attempt(this::crear);
                break;
            case VOLVER:
                break;
            default:
                throw new ValidationException("Acción no clasificada");
        }

        return Optional.ofNullable(ventana);
    }

    protected abstract void crear();
}
