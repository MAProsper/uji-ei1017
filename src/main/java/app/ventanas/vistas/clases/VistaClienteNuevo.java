package app.ventanas.vistas.clases;

import app.componentes.Textbox;
import app.componentes.textboxes.TextboxClienteNuevo;
import app.helpers.interfaces.FactoryClientes;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorClienteNuevo;
import app.ventanas.vistas.abstractas.VistaNuevo;

import static helpers.estaticos.Arguments.referenceNotNull;

public class VistaClienteNuevo extends VistaNuevo {
    protected final FactoryClientes factoria;

    public VistaClienteNuevo(final FactoryClientes factoria) {
        this(TextboxClienteNuevo.values(), factoria);
    }

    protected VistaClienteNuevo(final Textbox[] texboxes, final FactoryClientes factoria) {
        super(texboxes);
        this.factoria = referenceNotNull("factoria", factoria);
    }

    @Override
    protected boolean validateControlador(final Controlador controlador) {
        return controlador instanceof ControladorClienteNuevo;
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorClienteNuevo();
    }

    public FactoryClientes getFactoria() {
        return factoria;
    }
}
