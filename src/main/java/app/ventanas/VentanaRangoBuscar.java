package app.ventanas;

import com.google.common.collect.Range;
import helpers.Description;
import helpers.Fecha;

import java.text.ParseException;
import java.time.LocalDate;

import static helpers.Fecha.parse;
import static helpers.ValidatorArguments.referenceNotNull;
import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaRangoBuscar extends Ventana {
    final Tipo tipo;

    public VentanaRangoBuscar(final Tipo tipo) {
        super(
                "Busqueda en rango",
                "Introduce un rango de fechas para buscar " + referenceNotNull("tipo", tipo).getDescription(),
                false, Textbox.values(), Button.values());
        this.tipo = tipo;
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final app.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case BUSCAR:
                ventana = getVentana(getPeriodo());
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    Ventana getVentana(final Range<LocalDate> periodo) {
        final Ventana ventana;

        switch (tipo) {
            case CLIENTES:
                ventana = new VentanaClientesRango(periodo);
                break;
            case FACTURAS:
                ventana = new VentanaFacturasRango(periodo);
                break;
            case LLAMADAS:
                ventana = new VentanaLlamadasRango(periodo);
                break;
            default:
                ventana = new VentanaError();
        }

        return ventana;
    }

    Range<LocalDate> getPeriodo() {
        final String fechaInicio = getTextbox(Textbox.FECHA_INICIAL);
        final String fechaFinal = getTextbox(Textbox.FECHA_FINAL);
        Range<LocalDate> periodo = null;

        try {
            periodo = Fecha.getPeriodo(parse(fechaInicio), parse(fechaFinal));
        } catch (ParseException | IllegalArgumentException ignored) {
        }

        return periodo;
    }

    public enum Tipo implements Description {
        CLIENTES("clientes"),
        FACTURAS("Facturas"),
        LLAMADAS("llamadas");

        final String description;

        Tipo(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    enum Textbox implements app.Textbox {
        FECHA_INICIAL("Fecha inicial (YYYY-MM-DD)"),
        FECHA_FINAL("Fecha final (YYYY-MM-DD)");

        final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    enum Button implements app.Button {
        BUSCAR("Buscar"),
        VOLVER("Volver");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
