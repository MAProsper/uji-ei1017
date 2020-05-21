package app.ventanas.vistas.clases;

import app.componentes.textboxes.TextboxClienteParticularNuevo;
import app.helpers.interfaces.FactoryClientes;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorClienteParticularNuevo;

import static helpers.estaticos.Arguments.validate;

public class VistaClienteParticularNuevo extends VistaClienteNuevo {
    public VistaClienteParticularNuevo(final FactoryClientes factoria) {
        super(TextboxClienteParticularNuevo.values(), factoria);
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorClienteParticularNuevo);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorClienteParticularNuevo();
    }
}
