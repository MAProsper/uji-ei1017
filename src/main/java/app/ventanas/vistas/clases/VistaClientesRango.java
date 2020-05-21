package app.ventanas.vistas.clases;

import app.componentes.tables.TableClientes;
import app.helpers.estaticos.Formatter;
import app.ventanas.vistas.abstractas.VistaRango;
import clientes.Cliente;
import com.google.common.collect.Range;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VistaClientesRango extends VistaRango {
    public VistaClientesRango(final Range<LocalDateTime> perido) {
        super(TableClientes.values(), perido);
    }

    @Override
    public void update() {
        final List<Cliente> clientes = Fecha.filterRange(getModelo().getClientes(), getPeriodo());
        setTable(clientes.stream().map(Formatter::format).collect(Collectors.toList()));
    }
}