package app.ventanas.vistas.clases;

import app.componentes.textboxes.TextboxClienteEmpresaNuevo;
import app.helpers.interfaces.FactoryClientes;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorClienteEmpresaNuevo;

import static helpers.estaticos.Arguments.validate;

public class VistaClienteEmpresaNuevo extends VistaClienteNuevo {
    public VistaClienteEmpresaNuevo(final FactoryClientes factoria) {
        super(TextboxClienteEmpresaNuevo.values(), factoria);
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorClienteEmpresaNuevo);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorClienteEmpresaNuevo();
    }
}