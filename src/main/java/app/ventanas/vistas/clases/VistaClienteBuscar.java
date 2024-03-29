package app.ventanas.vistas.clases;

import app.componentes.Table;
import app.componentes.buttons.ButtonBuscar;
import app.componentes.textboxes.TextboxClienteBuscar;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorClienteBuscar;
import app.ventanas.vistas.abstractas.VistaPropia;

public class VistaClienteBuscar extends VistaPropia {
    public VistaClienteBuscar() {
        super(
                "Busqueda",
                "Intoduzca el NIF del cliente",
                Table.empty(), TextboxClienteBuscar.values(), ButtonBuscar.values());
    }

    @Override
    protected boolean validateControlador(final Controlador controlador) {
        return controlador instanceof ControladorClienteBuscar;
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorClienteBuscar();
    }
}
