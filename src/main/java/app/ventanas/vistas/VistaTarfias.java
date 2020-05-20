package app.ventanas.vistas;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.acciones.AccionTarifas;
import app.ventanas.controladores.ControladorTarifas;
import app.ventanas.interfaces.Table;
import app.ventanas.textboxes.TextboxTarifas;
import clientes.Cliente;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.validate;

public class VistaTarfias extends VistaPropia {
    protected final Cliente cliente;

    public VistaTarfias(final Cliente cliente) {
        super("Tarifas", "Seleciona una tarifa", Table.empty(), TextboxTarifas.values(), AccionTarifas.values());
        this.cliente = referenceNotNull("Cliente", cliente);
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorTarifas);
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorTarifas();
    }
}
