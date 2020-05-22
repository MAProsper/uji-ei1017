package app.ventanas.controladores.clases;

import app.componentes.textboxes.TextboxFacturaNuevo;
import app.helpers.estaticos.Parser;
import app.ventanas.controladores.abstractas.ControladorNuevo;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaFacturaNueva;
import clientes.Cliente;
import com.google.common.collect.Range;
import helpers.clases.Factura;
import helpers.clases.Llamada;
import helpers.estaticos.Fecha;
import tarifas.Tarifa;

import java.time.LocalDateTime;
import java.util.List;

import static helpers.estaticos.Fecha.filterRange;

public class ControladorFacturaNueva extends ControladorNuevo {
    public ControladorFacturaNueva() {
        super();
    }

    @Override
    protected void crear() {
        final VistaFacturaNueva vistaActual = (VistaFacturaNueva) getVista();
        final Cliente cliente = vistaActual.getCliente();

        final Tarifa tarifa = cliente.getTarifa();
        final String codigo = vistaActual.getTextbox(TextboxFacturaNuevo.CODIGO);
        final String fachaEmision = vistaActual.getTextbox(TextboxFacturaNuevo.FECHA_EMISION);
        final String fechaInicio = vistaActual.getTextbox(TextboxFacturaNuevo.FECHA_INICIO);
        final String fechaFinal = vistaActual.getTextbox(TextboxFacturaNuevo.FECHA_FINAL);

        final Range<LocalDateTime> periodo = Fecha.getPeriodo(Parser.fecha(TextboxFacturaNuevo.FECHA_INICIO.getDescription(), fechaInicio), Parser.fecha(TextboxFacturaNuevo.FECHA_INICIO.getDescription(), fechaFinal));
        final Factura factura = new Factura(Parser.entreo(TextboxFacturaNuevo.CODIGO.getDescription(), codigo), tarifa, Parser.fecha(TextboxFacturaNuevo.FECHA_EMISION.getDescription(), fachaEmision), periodo, getImporte(periodo));

        cliente.addFactura(factura);
        getModelo().addFactura(cliente, factura);
    }

    protected double getImporte(final Range<LocalDateTime> periodo) {
        final VistaFacturaNueva vistaFacturaNueva = (VistaFacturaNueva) getVista();
        final Cliente cliente = vistaFacturaNueva.getCliente();

        final Tarifa tarifa = cliente.getTarifa();
        final List<Llamada> llamadas = filterRange(cliente.getLlamadas(), periodo);
        return llamadas.stream().mapToDouble(tarifa::getImporte).sum();
    }

    @Override
    protected boolean validateVista(final Vista vista) {
        return vista instanceof VistaFacturaNueva;
    }
}
