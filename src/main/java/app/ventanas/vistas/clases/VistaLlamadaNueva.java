package app.ventanas.vistas.clases;

import app.componentes.textboxes.TextboxLlamadaNueva;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorLlamadaNueva;
import app.ventanas.vistas.abstractas.VistaNuevo;
import clientes.Cliente;

import static helpers.estaticos.Arguments.referenceNotNull;

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
    protected boolean validateControlador(final Controlador controlador) {
        return controlador instanceof ControladorLlamadaNueva;
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorLlamadaNueva();
    }
}
