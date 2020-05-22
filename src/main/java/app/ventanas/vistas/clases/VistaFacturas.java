package app.ventanas.vistas.clases;

import app.componentes.Textbox;
import app.componentes.buttons.ButtonFacturas;
import app.componentes.tables.TableFacturas;
import app.helpers.estaticos.Formatter;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorFacturas;
import app.ventanas.vistas.abstractas.VistaPropia;
import clientes.Cliente;

import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.referenceNotNull;

public class VistaFacturas extends VistaPropia {
    protected final Cliente cliente;

    public VistaFacturas(final Cliente cliente) {
        super(
                "Facturas",
                "Gestion de facturas",
                TableFacturas.values(), Textbox.empty(), ButtonFacturas.values());

        this.cliente = referenceNotNull("Cliente", cliente);
    }

    @Override
    protected boolean validateControlador(final Controlador controlador) {
        return controlador instanceof ControladorFacturas;
    }

    @Override
    public void update() {
        setTable(cliente.getFacturas().stream().map(Formatter::format).collect(Collectors.toList()));
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorFacturas();
    }

    public final Cliente getCliente() {
        return cliente;
    }
}
