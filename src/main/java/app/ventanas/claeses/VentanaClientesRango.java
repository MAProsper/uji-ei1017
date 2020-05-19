package app.ventanas.claeses;

import app.Formatter;
import app.ventanas.abstractas.VentanaRango;
import clientes.Cliente;
import com.google.common.collect.Range;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// Relacion Vista-Controlador
public class VentanaClientesRango extends VentanaRango {

    // Vista (define la vista contreta)
    public VentanaClientesRango(final Range<LocalDateTime> perido) {
        super(VentanaClientes.Table.values(), perido);
    }

    @Override
    protected void update() { // Gestiona la notificacion del modelo
        final List<Cliente> clientes = Fecha.filterRange(getGestor().getClientes(), getPeriodo());
        setTable(clientes.stream().map(Formatter::format).collect(Collectors.toList()));
    }
}