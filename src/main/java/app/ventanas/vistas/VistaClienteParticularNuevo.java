package app.ventanas.vistas;

import app.helpers.Parser;
import app.ventanas.textboxes.TextboxClienteParticularNuevo;
import clientes.Cliente;
import helpers.clases.Direccion;
import helpers.interfaces.FactoryClientes;
import tarifas.Tarifa;
import tarifas.TarifaBase;

import static helpers.estaticos.Arguments.stringNotEmpty;

public class VistaClienteParticularNuevo extends VistaClienteNuevo {

    public VistaClienteParticularNuevo(final FactoryClientes factoria) {
        super(TextboxClienteParticularNuevo.values(), factoria);
    }

}
