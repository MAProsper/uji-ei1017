package app.ventanas.vistas;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaNuevo;
import app.ventanas.controladores.ControladorFacturaNueva;
import app.ventanas.textboxes.TextboxFacturaNuevo;
import clientes.Cliente;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.validate;

public class VistaFacturaNueva extends VistaNuevo {
    protected final Cliente cliente;

    public VistaFacturaNueva(final Cliente cliente) {
        super(TextboxFacturaNuevo.values());
        this.cliente = referenceNotNull("Cliente", cliente);
    }

    public final Cliente getCliente() {
        return cliente;
    }

    @Override
    protected Controlador validateControlador(Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorFacturaNueva);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorFacturaNueva();
    }
}
