package app.ventanas.controladores;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.Vista;
import app.ventanas.acciones.AccionError;
import app.ventanas.interfaces.Accion;
import app.ventanas.vistas.VistaError;

import static helpers.estaticos.Arguments.validate;

public class ControladorError extends Controlador {
    public ControladorError() {
        super();
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Button tiene que ser esta ventana", accion instanceof AccionError);
        validate("Acción no clasificada", accion == AccionError.VOLVER);
        showNext(null);
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Controlador tiene que ser del mismo tipo", vista, vista instanceof VistaError);
    }
}
