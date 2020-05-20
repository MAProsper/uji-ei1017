package app.ventanas.vistas;

import app.helpers.Formatter;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaPropia;
import app.ventanas.acciones.AccionLlamadas;
import app.ventanas.controladores.ControladorLlamadas;
import app.ventanas.interfaces.Textbox;
import app.ventanas.tables.TableLlamadas;
import clientes.Cliente;

import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.validate;

public class VistaLlamadas extends VistaPropia {
    protected final Cliente cliente;

    public VistaLlamadas(final Cliente cliente) {
        super(
                "Llamadas",
                "Gestion de llamadas",
                TableLlamadas.values(), Textbox.empty(), AccionLlamadas.values());

        this.cliente = referenceNotNull("Cliente", cliente);
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorLlamadas);
    }

    @Override
    public void update() {
        setTable(cliente.getLlamadas().stream().map(Formatter::format).collect(Collectors.toList()));
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorLlamadas();
    }

    public final Cliente getCliente() {
        return cliente;
    }
}
