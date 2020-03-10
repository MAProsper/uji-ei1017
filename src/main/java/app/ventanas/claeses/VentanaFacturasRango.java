package app.ventanas.claeses;

import app.Formato;
import app.ventanas.abstractas.VentanaRango;
import com.google.common.collect.Range;
import helpers.clases.Factura;
import helpers.estaticos.Fecha;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaFacturasRango extends VentanaRango {
    public VentanaFacturasRango(final Range<LocalDate> perido) {
        super(perido);
    }

    @Override
    protected void update() {
        final List<Factura> facturas = Fecha.filterRange(getGestor().getFacturas(), getPeriodo());
        setList(facturas.stream().map(Formato::factura).collect(Collectors.toList()));
    }
}
