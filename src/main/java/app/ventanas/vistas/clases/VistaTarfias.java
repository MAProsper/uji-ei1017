package app.ventanas.vistas.clases;

import app.componentes.Table;
import app.componentes.buttons.ButtonTarifas;
import app.componentes.textboxes.TextboxTarifas;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorTarifas;
import app.ventanas.vistas.abstractas.VistaPropia;
import clientes.Cliente;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.validate;

public class VistaTarfias extends VistaPropia {
    protected final Cliente cliente;

    public VistaTarfias(final Cliente cliente) {
        super("Tarifas", "Seleciona una tarifa", Table.empty(), TextboxTarifas.values(), ButtonTarifas.values());
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
