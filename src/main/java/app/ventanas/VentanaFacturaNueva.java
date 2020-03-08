package app.ventanas;

import com.google.common.collect.Range;
import helpers.Factura;
import helpers.Llamada;
import tarifas.Tarifa;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static helpers.Fecha.parse;

public class VentanaFacturaNueva extends Ventana {
    public VentanaFacturaNueva() {
        super(
                "Facturas",
                "Rellena los datos de la nueva factura",
                false,
                Arrays.asList("Codigo", "Fecha de emision (YYYY-MM-DD)", "Fecha de inicio (YYYY-MM-DD)", "Fecha de final (YYYY-MM-DD)"),
                Arrays.asList("Crear", "Volver"));
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final String button) {
        Ventana ventana = null;

        switch (button) {
            case "Crear":
                Factura factura = crearFactura();
                if (factura == null) ventana = new VentanaError();
                else getGestor().addFactura(factura);
                break;
            case "Volver":
                break;
        }

        return ventana;
    }

    Factura crearFactura() {
        final Tarifa tarifa = getGestor().getClienteSelecionado().getTarifa();
        Factura factura = null;

        final String codigo = getTextbox("Codigo");
        final String fachaEmision = getTextbox("Fecha de emision (YYYY-MM-DD)");
        final String fechaInicio = getTextbox("Fecha de inicio (YYYY-MM-DD)");
        final String fechaFinal = getTextbox("Fecha de final (YYYY-MM-DD)");

        try {
            final Range<LocalDate> periodo = Range.closedOpen(parse(fechaInicio), parse(fechaFinal));
            factura = new Factura(Integer.parseInt(codigo), tarifa, parse(fachaEmision), periodo, getImporte(getLlamadas(periodo)));
        } catch (ParseException | IllegalArgumentException ignored) {
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
}
