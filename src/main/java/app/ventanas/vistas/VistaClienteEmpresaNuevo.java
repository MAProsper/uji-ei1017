package app.ventanas.vistas;

import app.ventanas.textboxes.TextboxClienteNuevo;
import helpers.interfaces.FactoryClientes;

public class VistaClienteEmpresaNuevo extends VistaClienteNuevo {

    public VistaClienteEmpresaNuevo(final FactoryClientes factoria) {
        super(TextboxClienteNuevo.values(), factoria);
    }
}