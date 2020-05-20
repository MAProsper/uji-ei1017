package app.ventanas.abstractas;

import app.ventanas.acciones.AccionNuevo;
import app.ventanas.interfaces.Accion;
import app.ventanas.vistas.VistaError;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public abstract class ControladorNuevo extends Controlador {
    public ControladorNuevo() {
        super();
    }

    @Override
    public void gestionaAccion(final Accion accion) { // Gestiona la accion del usuario
        validate("Acción tiene que ser esta ventana", accion instanceof AccionNuevo);
        Vista vista = null;

        switch ((AccionNuevo) accion) {
            case CREAR:
                vista = VistaError.attempt(this::crear);
                break;
            case VOLVER:
                break;
            default:
                throw new Arguments.ValidationException("Acción no clasificada");
        }

        showNext(vista);
    }

    protected abstract void crear();
}
