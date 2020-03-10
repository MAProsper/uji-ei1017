package app.ventanas;

import app.Formato;
import app.Textbox;
import com.google.common.collect.Range;
import helpers.Factura;
import helpers.Fecha;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaFacturasRango extends Ventana {
    final Range<LocalDate> periodo;

    public VentanaFacturasRango(final Range<LocalDate> perido) {
        super(
                "Facturas (rango)",
                "Las facturas en el periodo " + Formato.periodo(perido),
                true, Textbox.empty(), Button.values());
        this.periodo = perido;
    }

    @Override
    public void update() {
        final List<Factura> facturas = Fecha.filterRange(getGestor().getFacturas(), periodo);
        setList(facturas.stream().map(Formato::factura).collect(Collectors.toList()));
    }

    @Override
    public Ventana handle(final app.Button button) {
        return null;
    }

    enum Button implements app.Button {
        RAGNO("Rango");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
