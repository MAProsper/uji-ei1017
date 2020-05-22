package app.ventanas.vistas.clases;

import app.componentes.Textbox;
import app.componentes.buttons.ButtonLlamadas;
import app.componentes.tables.TableLlamadas;
import app.helpers.estaticos.Formatter;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.controladores.clases.ControladorLlamadas;
import app.ventanas.vistas.abstractas.VistaPropia;
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
                TableLlamadas.values(), Textbox.empty(), ButtonLlamadas.values());

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
