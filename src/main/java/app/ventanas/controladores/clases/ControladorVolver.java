package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonVolver;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.abstractas.VistaVolver;

import static helpers.estaticos.Arguments.validate;

public class ControladorVolver extends Controlador {
    public ControladorVolver() {
        super();
    }

    @Override
    public void gestionaButton(final Button button) {
        validate("Button tiene que ser de este controlador", button instanceof ButtonVolver);
        validate("Button no clasificado", button == ButtonVolver.VOLVER);
        vistaBack();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaVolver);
    }
}
