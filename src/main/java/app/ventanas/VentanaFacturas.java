package app.ventanas;

import app.Formato;
import clientes.Cliente;

import java.util.Arrays;
import java.util.Collections;
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
        final Cliente cliente = getGestor().getClienteSelecionado();
        setList(cliente.getFacturas().stream().map(Formato::factura).collect(Collectors.toList()));
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
