package app.ventanas.controladores.abstractas;

import app.componentes.Button;
import app.componentes.buttons.ButtonNuevo;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaError;

public abstract class ControladorNuevo extends Controlador {
    public ControladorNuevo() {
        super();
    }

    @Override
    public boolean validateButton(final Button button) {
        return button instanceof ButtonNuevo;
    }

    @Override
    public void gestionaButton(final Button button) {
        super.gestionaButton(button);
        Vista vista = null;

        switch ((ButtonNuevo) button) {
            case CREAR:
                vista = VistaError.attempt(this::crear);
                break;
            case VOLVER:
                break;
            default:
                throw Button.MISSING;
        }

        vistaNext(vista);
    }

    protected abstract void crear();
}
