package app.ventanas.vistas;

import app.helpers.Parser;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaNuevo;
import app.ventanas.controladores.ControladorCliente;
import app.ventanas.controladores.ControladorClienteNuevo;
import app.ventanas.textboxes.TextboxClienteNuevo;
import clientes.Cliente;
import helpers.clases.Direccion;
import helpers.interfaces.FactoryClientes;
import tarifas.Tarifa;
import tarifas.TarifaBase;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador
public class VistaClienteNuevo extends VistaNuevo {
    protected final FactoryClientes factoria;

    // Vista (define la vista contreta)
    public VistaClienteNuevo(final FactoryClientes factoria) {
        this(TextboxClienteNuevo.values(), factoria);
    }

    protected VistaClienteNuevo(final app.ventanas.interfaces.Textbox[] texboxes, final FactoryClientes factoria) {
        super(texboxes);
        this.factoria = referenceNotNull("factoria", factoria);
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorClienteNuevo);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorCliente();
    }

    public FactoryClientes getFactoria() {
        return factoria;
    }
}
