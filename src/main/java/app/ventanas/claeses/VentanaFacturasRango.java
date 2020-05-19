package app.ventanas.claeses;

import app.Formatter;
import app.ventanas.abstractas.VentanaRango;
import com.google.common.collect.Range;
import helpers.clases.Factura;
import helpers.estaticos.Fecha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// Relacion Vista-Controlador
public class VentanaFacturasRango extends VentanaRango {

    // Vista (define la vista contreta)
    public VentanaFacturasRango(final Range<LocalDateTime> perido) {
        super(VentanaFacturas.Table.values(), perido);
    }

    @Override
    protected void update() { // Gestiona la notificacion del modelo
        // Modelo.getFacturas (5. solicita nuevos datos)
        final List<Factura> facturas = Fecha.filterRange(getGestor().getFacturas(), getPeriodo());
        setTable(facturas.stream().map(Formatter::format).collect(Collectors.toList()));
    }
}
