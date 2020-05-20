package app.ventanas.controladores;

import app.helpers.Parser;
import app.ventanas.abstractas.ControladorNuevo;
import app.ventanas.abstractas.Vista;
import app.ventanas.textboxes.TextboxFacturaNuevo;
import app.ventanas.vistas.VistaFacturaNueva;
import clientes.Cliente;
import com.google.common.collect.Range;
import helpers.clases.Factura;
import helpers.clases.Llamada;
import helpers.estaticos.Fecha;
import tarifas.Tarifa;

import java.time.LocalDateTime;
import java.util.List;

import static helpers.estaticos.Arguments.validate;
import static helpers.estaticos.Fecha.filterRange;

public class ControladorFacturaNueva extends ControladorNuevo {
    public ControladorFacturaNueva() {
        super();
    }

    @Override
    protected void crear() {
        final VistaFacturaNueva vistaFacturaNueva = (VistaFacturaNueva) getVista();
        final Cliente cliente = vistaFacturaNueva.getCliente();

        final Tarifa tarifa = cliente.getTarifa();
        final String codigo = vistaFacturaNueva.getTextbox(TextboxFacturaNuevo.CODIGO);
        final String fachaEmision = vistaFacturaNueva.getTextbox(TextboxFacturaNuevo.FECHA_EMISION);
        final String fechaInicio = vistaFacturaNueva.getTextbox(TextboxFacturaNuevo.FECHA_INICIO);
        final String fechaFinal = vistaFacturaNueva.getTextbox(TextboxFacturaNuevo.FECHA_FINAL);

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
    protected Vista validateVista(final Vista vista) {
        return validate("Controlador tiene que ser del mismo tipo", vista, vista instanceof VistaFacturaNueva);
    }
}
