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
    public boolean validateButton(final Button button) {
        return button instanceof ButtonVolver;
    }

    @Override
    public void gestionaButton(final Button button) {
        super.gestionaButton(button);
        validate("Button no clasificado", button == ButtonVolver.VOLVER);
        vistaBack();
    }

    @Override
    protected boolean validateVista(final Vista vista) {
        return vista instanceof VistaVolver;
    }
}
