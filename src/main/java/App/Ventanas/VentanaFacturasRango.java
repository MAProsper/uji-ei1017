package App.Ventanas;

import App.Formato;
import Helpers.Factura;
import Helpers.Fecha;
import com.google.common.collect.Range;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaFacturasRango extends Ventana {
    final Range<Date> periodo;

    public VentanaFacturasRango(final Range<Date> perido) {
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
