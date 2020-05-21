package app.ventanas.vistas.clases;

import app.componentes.tables.TableFacturas;
import app.helpers.estaticos.Formatter;
import app.ventanas.vistas.abstractas.VistaRango;
import com.google.common.collect.Range;
import helpers.clases.Factura;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VistaFacturasRango extends VistaRango {
    public VistaFacturasRango(final Range<LocalDateTime> perido) {
        super(TableFacturas.values(), perido);
    }

    @Override
    public void update() {
        final List<Factura> facturas = Fecha.filterRange(getModelo().getFacturas(), getPeriodo());
        setTable(facturas.stream().map(Formatter::format).collect(Collectors.toList()));
    }
}
