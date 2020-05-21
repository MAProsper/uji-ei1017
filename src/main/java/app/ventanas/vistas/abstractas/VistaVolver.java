package app.ventanas.vistas.abstractas;

import app.componentes.Table;
import app.componentes.Textbox;
import app.componentes.acciones.AccionVolver;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorVolver;

import static helpers.estaticos.Arguments.validate;

public abstract class VistaVolver extends VistaPropia {
    public VistaVolver(final String title, final String info) {
        super(
                title, info,
                Table.empty(), Textbox.empty(), AccionVolver.values());
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorVolver);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorVolver();
    }
}
