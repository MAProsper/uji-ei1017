package app.ventanas;

import com.google.common.collect.Range;
import helpers.Fecha;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;

import static helpers.Fecha.parse;

public class VentanaRangoBuscar extends Ventana {
    final String tipo;

    public VentanaRangoBuscar(final String tipo) {
        super(
                "Busqueda en rango",
                "Introduce un rango de fechas para buscar " + tipo,
                false,
                Arrays.asList("Fecha inicial (YYYY-MM-DD)", "Fecha final (YYYY-MM-DD)"),
                Arrays.asList("Buscar", "Volver"));
        this.tipo = tipo;
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final String button) {
        Ventana ventana = null;

        switch (button) {
            case "Buscar":
                ventana = getVentana(getPeriodo());
                break;
            case "Volver":
                break;
        }

        return ventana;
    }

    Ventana getVentana(final Range<LocalDate> periodo) {
        final Ventana ventana;

        switch (tipo) {
            case "clientes":
                ventana = new VentanaClientesRango(periodo);
                break;
            case "facturas":
                ventana = new VentanaFacturasRango(periodo);
                break;
            case "llamadas":
                ventana = new VentanaLlamadasRango(periodo);
                break;
            default:
                ventana = new VentanaError();
        }

        return ventana;
    }

    Range<LocalDate> getPeriodo() {
        final String fechaInicio = getTextbox("Fecha inicial (YYYY-MM-DD)");
        final String fechaFinal = getTextbox("Fecha final (YYYY-MM-DD)");
        Range<LocalDate> periodo = null;

        try {
            periodo = Fecha.getPeriodo(parse(fechaInicio), parse(fechaFinal));
        } catch (ParseException | IllegalArgumentException ignored) {
        }

        return periodo;
    }
}
