package app.ventanas.abstractas;

import app.ventanas.acciones.AccionRango;
import app.ventanas.interfaces.Accion;

import static helpers.estaticos.Arguments.validate;

public abstract class ControladorRango extends Controlador {
    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionRango);
        validate("Acción no clasificada", accion == AccionRango.VOLVER);
        showNext(null);
    }
}
