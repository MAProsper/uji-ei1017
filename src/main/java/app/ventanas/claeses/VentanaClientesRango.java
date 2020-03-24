package app.ventanas.claeses;

import app.Formatter;
import app.ventanas.abstractas.VentanaRango;
import clientes.Cliente;
import com.google.common.collect.Range;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaClientesRango extends VentanaRango {
    public VentanaClientesRango(final Range<LocalDateTime> perido) {
        super(perido);
    }

    @Override
    protected void update() {
        final List<Cliente> clientes = Fecha.filterRange(getGestor().getClientes(), getPeriodo());
        setList(clientes.stream().map(Formatter::format).collect(Collectors.toList()));
    }
}