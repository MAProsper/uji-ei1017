package app.ventanas.vistas.abstractas;

import app.componentes.Table;
import app.componentes.Textbox;
import app.componentes.buttons.ButtonVolver;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorVolver;

public abstract class VistaVolver extends VistaPropia {
    public VistaVolver(final String title, final String info) {
        super(
                title, info,
                Table.empty(), Textbox.empty(), ButtonVolver.values());
    }

    @Override
    protected boolean validateControlador(final Controlador controlador) {
        return controlador instanceof ControladorVolver;
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorVolver();
    }
}
