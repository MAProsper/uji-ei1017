package app.ventanas.controladores;

import app.ventanas.abstractas.ControladorRango;
import app.ventanas.abstractas.Vista;
import app.ventanas.vistas.VistaFacturasRango;

import static helpers.estaticos.Arguments.validate;

public class ControladorFacturasRango extends ControladorRango {
    public ControladorFacturasRango() {
        super();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Controlador tiene que ser del mismo tipo", vista, vista instanceof VistaFacturasRango);
    }
}
