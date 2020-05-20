package app.ventanas.vistas;

import app.helpers.Formatter;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.VistaRango;
import app.ventanas.controladores.ControladorClientesRango;
import app.ventanas.tables.TableClientes;
import clientes.Cliente;
import com.google.common.collect.Range;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.validate;

public class VistaClientesRango extends VistaRango {
    public VistaClientesRango(final Range<LocalDateTime> perido) {
        super(TableClientes.values(), perido);
    }

    @Override
    protected Controlador validateControlador(final Controlador controlador) {
        return validate("Controlador tiene que ser del mismo tipo", controlador, controlador instanceof ControladorClientesRango);
    }

    @Override
    public void update() {
        final List<Cliente> clientes = Fecha.filterRange(getModelo().getClientes(), getPeriodo());
        setTable(clientes.stream().map(Formatter::format).collect(Collectors.toList()));
    }

    @Override
    public Controlador getControladorDefault() {
        return new ControladorClientesRango();
    }
}