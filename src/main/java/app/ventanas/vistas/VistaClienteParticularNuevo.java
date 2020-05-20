package app.ventanas.vistas;

import app.helpers.Parser;
import app.ventanas.abstractas.Controlador;
import app.ventanas.controladores.ControladorClienteNuevo;
import app.ventanas.controladores.ControladorClienteParticularNuevo;
import app.ventanas.textboxes.TextboxClienteParticularNuevo;
import clientes.Cliente;
import helpers.clases.Direccion;
import helpers.interfaces.FactoryClientes;
import tarifas.Tarifa;
import tarifas.TarifaBase;

import static helpers.estaticos.Arguments.stringNotEmpty;
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
