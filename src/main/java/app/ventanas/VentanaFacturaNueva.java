package app.ventanas;

import com.google.common.collect.Range;
import helpers.Factura;
import helpers.Llamada;
import tarifas.Tarifa;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import static helpers.Fecha.parse;
import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaFacturaNueva extends Ventana {
    public VentanaFacturaNueva() {
        super(
                "Facturas",
                "Rellena los datos de la nueva factura",
                false, Textbox.values(), Button.values());
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final app.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case CREAR:
                Factura factura = crearFactura();
                if (factura == null) ventana = new VentanaError();
                else getGestor().gestionarFactura(factura);
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    Factura crearFactura() {
        final Tarifa tarifa = getGestor().getClienteSelecionado().getTarifa();
        Factura factura = null;

        final String codigo = getTextbox(Textbox.CODIGO);
        final String fachaEmision = getTextbox(Textbox.FECHA_EMISION);
        final String fechaInicio = getTextbox(Textbox.FECHA_INICIO);
        final String fechaFinal = getTextbox(Textbox.FECHA_FINAL);

        try {
            final Range<LocalDate> periodo = Range.closedOpen(parse(fechaInicio), parse(fechaFinal));
            factura = new Factura(Integer.parseInt(codigo), tarifa, parse(fachaEmision), periodo, getImporte(getLlamadas(periodo)));
        } catch (DateTimeParseException | IllegalArgumentException ignored) {
        }

        return factura;
    }

    List<Llamada> getLlamadas(final Range<LocalDate> periodo) {
        final List<Llamada> llamadas = new LinkedList<>();
        for (Llamada llamada : getGestor().getClienteSelecionado().getLlamadas())
            if (periodo.contains(llamada.getFecha())) llamadas.add(llamada);
        return llamadas;
    }

    double getImporte(final List<Llamada> llamadas) {
        final Tarifa tarifa = getGestor().getClienteSelecionado().getTarifa();
        double importe = 0;
        for (Llamada llamada : llamadas) importe += tarifa.getImporte(llamada);
        return importe;
    }

    enum Button implements app.Button {
        CREAR("crear"),
        VOLVER("volver");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    enum Textbox implements app.Textbox {
        CODIGO("codigo"),
        FECHA_EMISION("Fecha de emision (YYYY-MM-DD)"),
        FECHA_INICIO("Fecha de inicio (YYYY-MM-DD)"),
        FECHA_FINAL("Fecha de final (YYYY-MM-DD)");
        final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }


}
