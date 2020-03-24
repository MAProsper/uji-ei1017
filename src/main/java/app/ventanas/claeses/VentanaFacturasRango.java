package app.ventanas.claeses;

import app.Formatter;
import app.ventanas.abstractas.VentanaRango;
import com.google.common.collect.Range;
import helpers.clases.Factura;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaFacturasRango extends VentanaRango {
    public VentanaFacturasRango(final Range<LocalDateTime> perido) {
        super(perido);
    }

    @Override
    protected void update() {
        final List<Factura> facturas = Fecha.filterRange(getGestor().getFacturas(), getPeriodo());
        setList(facturas.stream().map(Formatter::format).collect(Collectors.toList()));
    }
}
