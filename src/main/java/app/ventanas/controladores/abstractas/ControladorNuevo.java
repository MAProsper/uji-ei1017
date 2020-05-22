package app.ventanas.controladores.abstractas;

import app.componentes.Button;
import app.componentes.buttons.ButtonNuevo;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaError;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public abstract class ControladorNuevo extends Controlador {
    public ControladorNuevo() {
        super();
    }

    @Override
    public void gestionaButton(final Button button) {
        validate("Button tiene que ser de este controlador", button instanceof ButtonNuevo);
        Vista vista = null;

        switch ((ButtonNuevo) button) {
            case CREAR:
                vista = VistaError.attempt(this::crear);
                break;
            case VOLVER:
                break;
            default:
                throw new Arguments.ValidationException("Button no clasificado");
        }

        vistaNext(vista);
    }

    protected abstract void crear();
}
