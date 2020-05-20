package app.ventanas.controladores;

import app.ventanas.abstractas.ControladorRango;
import app.ventanas.abstractas.Vista;
import app.ventanas.vistas.VistaLlamadasRango;

import static helpers.estaticos.Arguments.validate;

public class ControladorLlamadasRango extends ControladorRango {
    public ControladorLlamadasRango() {
        super();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Controlador tiene que ser del mismo tipo", vista, vista instanceof VistaLlamadasRango);
    }
}
