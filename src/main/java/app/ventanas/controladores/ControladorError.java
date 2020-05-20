package app.ventanas.controladores;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.Vista;
import app.ventanas.acciones.AccionError;

import static helpers.estaticos.Arguments.validate;

public class ControladorError extends Controlador {
    @Override
    public void gestionaAccion(final app.ventanas.interfaces.Accion accion) {
        validate("Button tiene que ser esta ventana", accion instanceof AccionError);
        validate("Acción no clasificada", accion == AccionError.VOLVER);
        showNext(null);
    }

    @Override
    protected Vista validateVista(Vista vista) {
        return null;
    }
}
