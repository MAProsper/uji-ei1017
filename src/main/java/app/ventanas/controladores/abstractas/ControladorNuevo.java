package app.ventanas.controladores.abstractas;

import app.componentes.Accion;
import app.componentes.acciones.AccionNuevo;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaError;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public abstract class ControladorNuevo extends Controlador {
    public ControladorNuevo() {
        super();
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser de este controlador", accion instanceof AccionNuevo);
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

        vistaNext(vista);
    }

    protected abstract void crear();
}
