package App.Ventanas;

import Clientes.Cliente;
import Helpers.Factura;
import com.google.common.collect.Range;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class VentanaFacturas extends Ventana {
    public VentanaFacturas() {
        super(
                "Facturas",
                "Gestion de facturas",
                true,
                Collections.emptyList(),
                Arrays.asList("Añadir factura", "Volver"));
    }

    @Override
    public void update() {
        Cliente cliente = getGestor().getClienteSelecionado();
        setList(cliente.getFacturas().stream().map(this::resumirFactura).collect(Collectors.toList()));
    }

    String resumirFactura(final Factura factura) {
        final Range<Date> periodo = factura.getPeriodo();
        return String.format("[%s-%s] [%s-%s] %s (%s euros/min)", factura.getCodigo(), factura.getFecha(), periodo.lowerEndpoint(), periodo.upperEndpoint(), factura.getImporte(), factura.getTarifa().getPrecio());
    }

    @Override
    public Ventana handle(final String button) {
        Ventana ventana = null;

        switch (button) {
            case "Añadir factura":
                ventana = new VentanaFacturaNueva();
                break;
            case "Volver":
                break;
        }

        return ventana;
    }
}
