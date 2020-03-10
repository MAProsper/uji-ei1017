package app.ventanas.claeses;

import app.Formato;
import app.ventanas.abstractas.VentanaRango;
import clientes.Cliente;
import com.google.common.collect.Range;
import helpers.estaticos.Fecha;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaClientesRango extends VentanaRango {
    public VentanaClientesRango(final Range<LocalDate> perido) {
        super(perido);
    }

    @Override
    protected void update() {
        final List<Cliente> clientes = Fecha.filterRange(getGestor().getClientes(), getPeriodo());
        setList(clientes.stream().map(Formato::cliente).collect(Collectors.toList()));
    }
}