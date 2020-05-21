package app.ventanas.vistas.clases;

import app.componentes.textboxes.TextboxFacturaNuevo;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorFacturaNueva;
import app.ventanas.vistas.abstractas.VistaNuevo;
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
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorFacturaNueva);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorFacturaNueva();
    }
}
