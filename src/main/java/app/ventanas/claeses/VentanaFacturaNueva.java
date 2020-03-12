package app.ventanas.claeses;

import app.ventanas.abstractas.VentanaNuevo;
import clientes.Cliente;
import com.google.common.collect.Range;
import helpers.clases.Factura;
import helpers.clases.Llamada;
import helpers.estaticos.Fecha;
import helpers.estaticos.Parse;
import tarifas.Tarifa;

import java.time.LocalDate;
import java.util.List;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.stringNotEmpty;
import static helpers.estaticos.Fecha.filterRange;

public class VentanaFacturaNueva extends VentanaNuevo {
    protected final Cliente cliente;

    public VentanaFacturaNueva(final Cliente cliente) {
        super(Textbox.values());
        this.cliente = referenceNotNull("Cliente", cliente);
    }

    @Override
    protected void crear() {
        final Tarifa tarifa = cliente.getTarifa();
        final String codigo = getTextbox(Textbox.CODIGO);
        final String fachaEmision = getTextbox(Textbox.FECHA_EMISION);
        final String fechaInicio = getTextbox(Textbox.FECHA_INICIO);
        final String fechaFinal = getTextbox(Textbox.FECHA_FINAL);

        final Range<LocalDate> periodo = Fecha.getPeriodo(Parse.fecha(Textbox.FECHA_INICIO.getDescription(), fechaInicio), Parse.fecha(Textbox.FECHA_INICIO.getDescription(), fechaFinal));
        final Factura factura = new Factura(Parse.entreo(Textbox.CODIGO.getDescription(), codigo), tarifa, Parse.fecha(Textbox.FECHA_EMISION.getDescription(), fachaEmision), periodo, getImporte(periodo));

        cliente.addFactura(factura);
        getGestor().addFactura(cliente, factura);
    }

    protected double getImporte(final Range<LocalDate> periodo) {
        final Tarifa tarifa = cliente.getTarifa();
        final List<Llamada> llamadas = filterRange(cliente.getLlamadas(), periodo);
        return llamadas.stream().mapToDouble(tarifa::getImporte).sum();
    }

    public final Cliente getCliente() {
        return cliente;
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        CODIGO("Codigo"),
        FECHA_EMISION("Fecha de emision (YYYY-MM-DD)"),
        FECHA_INICIO("Fecha de inicio (YYYY-MM-DD)"),
        FECHA_FINAL("Fecha de final (YYYY-MM-DD)");
        final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
