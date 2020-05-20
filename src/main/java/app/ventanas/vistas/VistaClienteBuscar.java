package app.ventanas.vistas;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.acciones.AccionClienteBuscar;
import app.ventanas.controladores.ControladorClienteBuscar;
import app.ventanas.interfaces.Table;
import app.ventanas.textboxes.TextboxClienteBuscar;

import static helpers.estaticos.Arguments.validate;

public class VistaClienteBuscar extends VistaPropia {
    public VistaClienteBuscar() {
        super(
                "Busqueda",
                "Intoduzca el NIF del cliente",
                Table.empty(), TextboxClienteBuscar.values(), AccionClienteBuscar.values());
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorClienteBuscar);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorClienteBuscar();
    }
}
