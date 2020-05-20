package app.ventanas.vistas;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaNuevo;
import app.ventanas.controladores.ControladorLlamadaNueva;
import app.ventanas.textboxes.TextboxLlamadaNueva;
import clientes.Cliente;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.validate;

public class VistaLlamadaNueva extends VistaNuevo {
    protected final Cliente cliente;

    public VistaLlamadaNueva(final Cliente cliente) {
        super(TextboxLlamadaNueva.values());
        this.cliente = referenceNotNull("Cliente", cliente);
    }

    public final Cliente getCliente() {
        return cliente;
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorLlamadaNueva);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorLlamadaNueva();
    }
}
