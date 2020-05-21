package app.ventanas.controladores.clases;

import app.componentes.Accion;
import app.componentes.acciones.AccionVolver;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.abstractas.VistaVolver;

import static helpers.estaticos.Arguments.validate;

public class ControladorVolver extends Controlador {
    public ControladorVolver() {
        super();
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser de este controlador", accion instanceof AccionVolver);
        validate("Acción no clasificada", accion == AccionVolver.VOLVER);
        vistaBack();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaVolver);
    }
}
