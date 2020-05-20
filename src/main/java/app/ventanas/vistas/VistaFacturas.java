package app.ventanas.vistas;

import app.helpers.Formatter;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.acciones.AccionFacturas;
import app.ventanas.controladores.ControladorFacturas;
import app.ventanas.interfaces.Textbox;
import app.ventanas.tables.TableFacturas;
import clientes.Cliente;

import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.validate;

public class VistaFacturas extends VistaPropia {
    protected final Cliente cliente;

    public VistaFacturas(final Cliente cliente) {
        super(
                "Facturas",
                "Gestion de facturas",
                TableFacturas.values(), Textbox.empty(), AccionFacturas.values());

        this.cliente = referenceNotNull("Cliente", cliente);
    }


    @Override
    protected Controlador validateControlador(Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorFacturas);
    }

    @Override
    public void update() { // Gestiona la notificacion del modelo
        // Cliente.getFacturas (5. solicita nuevos datos)
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
