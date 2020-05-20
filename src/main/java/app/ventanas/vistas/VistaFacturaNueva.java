package app.ventanas.vistas;

import app.helpers.Parser;
import app.ventanas.abstractas.VistaNuevo;
import clientes.Cliente;
import com.google.common.collect.Range;
import helpers.clases.Factura;
import helpers.clases.Llamada;
import helpers.estaticos.Fecha;
import tarifas.Tarifa;

import java.time.LocalDateTime;
import java.util.List;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.stringNotEmpty;
import static helpers.estaticos.Fecha.filterRange;

// Relacion Vista-Controlador
public class VistaFacturaNueva extends VistaNuevo {
    protected final Cliente cliente;

    // Vista (define la vista contreta)
    public VistaFacturaNueva(final Cliente cliente) {
        super(Textbox.values());
        this.cliente = referenceNotNull("Cliente", cliente);
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        CODIGO("Codigo"),
        FECHA_EMISION("Fecha de emision"),
        FECHA_INICIO("Fecha de inicio"),
        FECHA_FINAL("Fecha de final");
        private final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public final Cliente getCliente() {
        return cliente;
    }

    // Controlador (define el controlador concreto)
    @Override
    protected void crear() {
        // Vista.getTextbox (2. solicita datos a la vista)
        final Tarifa tarifa = cliente.getTarifa();
        final String codigo = getTextbox(Textbox.CODIGO);
        final String fachaEmision = getTextbox(Textbox.FECHA_EMISION);
        final String fechaInicio = getTextbox(Textbox.FECHA_INICIO);
        final String fechaFinal = getTextbox(Textbox.FECHA_FINAL);

        // Cliente~getImporte (3. consulta datos del modelo)
        final Range<LocalDateTime> periodo = Fecha.getPeriodo(Parser.fecha(Textbox.FECHA_INICIO.getDescription(), fechaInicio), Parser.fecha(Textbox.FECHA_INICIO.getDescription(), fechaFinal));
        final Factura factura = new Factura(Parser.entreo(Textbox.CODIGO.getDescription(), codigo), tarifa, Parser.fecha(Textbox.FECHA_EMISION.getDescription(), fachaEmision), periodo, getImporte(periodo));

        // Modelo.addFactura (3. actualiza el modelo)
        cliente.addFactura(factura);
        getModelo().addFactura(cliente, factura);
    }

    protected double getImporte(final Range<LocalDateTime> periodo) {
        final Tarifa tarifa = cliente.getTarifa();
        final List<Llamada> llamadas = filterRange(cliente.getLlamadas(), periodo);
        return llamadas.stream().mapToDouble(tarifa::getImporte).sum();
    }
}
