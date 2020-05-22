package app.ventanas.vistas.clases;

import app.componentes.textboxes.TextboxClienteEmpresaNuevo;
import app.helpers.interfaces.FactoryClientes;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorClienteEmpresaNuevo;

public class VistaClienteEmpresaNuevo extends VistaClienteNuevo {
    public VistaClienteEmpresaNuevo(final FactoryClientes factoria) {
        super(TextboxClienteEmpresaNuevo.values(), factoria);
    }

    @Override
    protected boolean validateControlador(final Controlador controlador) {
        return controlador instanceof ControladorClienteEmpresaNuevo;
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorClienteEmpresaNuevo();
    }
}