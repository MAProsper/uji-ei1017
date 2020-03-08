package app.ventanas;

import app.Formato;
import com.google.common.collect.Range;
import helpers.Factura;
import helpers.Fecha;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaFacturasRango extends Ventana {
    final Range<LocalDate> periodo;

    public VentanaFacturasRango(final Range<LocalDate> perido) {
        super(
                "Facturas (rango)",
                "Las facturas en el periodo " + Formato.periodo(perido),
                true,
                Collections.emptyList(),
                Collections.singletonList("Volver"));
        this.periodo = perido;
    }

    @Override
    public void update() {
        final List<Factura> facturas = Fecha.filterRange(getGestor().getFacturas(), periodo);
        setList(facturas.stream().map(Formato::factura).collect(Collectors.toList()));
    }

    @Override
    public Ventana handle(final String button) {
        return null;
    }
}
