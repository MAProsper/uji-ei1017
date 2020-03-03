package App.Ventanas;

import Helpers.Fecha;
import com.google.common.collect.Range;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import static Helpers.Fecha.parseDate;

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

    Ventana getVentana(final Range<Date> periodo) {
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

    Range<Date> getPeriodo() {
        Range<Date> periodo = null;

        try {
            periodo = Fecha.getPeriodo(parseDate(getTextbox("Fecha inicial (YYYY-MM-DD)")), parseDate(getTextbox("Fecha final (YYYY-MM-DD)")));
        } catch (ParseException | IllegalArgumentException ignored) {
        }

        return periodo;
    }
}
