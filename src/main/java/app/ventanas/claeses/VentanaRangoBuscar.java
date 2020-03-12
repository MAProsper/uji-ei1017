package app.ventanas.claeses;

import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Description;
import com.google.common.collect.Range;
import helpers.estaticos.Fecha;
import helpers.estaticos.Parse;

import java.time.LocalDate;

import static helpers.estaticos.Arguments.*;

public class VentanaRangoBuscar extends Ventana {
    protected final Tipo tipo;

    public VentanaRangoBuscar(final Tipo tipo) {
        super(
                "Busqueda en rango",
                "Introduce un rango de fechas para buscar",
                false, Textbox.values(), Button.values());
        this.tipo = referenceNotNull("Tipo", tipo);
    }

    @Override
    protected void update() {
    }

    @Override
    public Ventana handle(final app.ventanas.interfaces.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case BUSCAR:
                Range<LocalDate> periodo = null;

                try {
                    periodo = getPeriodo();
                } catch (ValidationException e) {
                    ventana = new VentanaError(e);
                }

                if (periodo != null) ventana = getVentana(periodo);
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    protected Ventana getVentana(final Range<LocalDate> periodo) {
        referenceNotNull("Periodo", periodo);
        Ventana ventana = null;

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
        }

        return ventana;
    }

    protected Range<LocalDate> getPeriodo() {
        final String fechaInicio = getTextbox(Textbox.FECHA_INICIAL);
        final String fechaFinal = getTextbox(Textbox.FECHA_FINAL);
        return Fecha.getPeriodo(Parse.fecha(Textbox.FECHA_INICIAL.getDescription(), fechaInicio), Parse.fecha(Textbox.FECHA_INICIAL.getDescription(), fechaFinal));
    }

    public final Tipo getTipo() {
        return tipo;
    }

    public enum Tipo implements Description {
        CLIENTES("clientes"),
        FACTURAS("facturas"),
        LLAMADAS("llamadas");

        private final String description;

        Tipo(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        FECHA_INICIAL("Fecha inicial (YYYY-MM-DD)"),
        FECHA_FINAL("Fecha final (YYYY-MM-DD)");

        private final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Button implements app.ventanas.interfaces.Button {
        BUSCAR("Buscar"),
        VOLVER("Volver");

        private final String description;

        Button(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
