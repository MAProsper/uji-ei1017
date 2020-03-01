package App.Ventanas;

import Clientes.Cliente;

import java.util.Arrays;
import java.util.Collections;

public class VentanaFacturaBuscar extends Ventana {
    public VentanaFacturaBuscar() {
        super(
                "Busqueda",
                "Intoduzca el codigo de la factura",
                false,
                Collections.singletonList("Codigo"),
                Arrays.asList("Buscar", "Volver"));
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(String button) {
        Ventana ventana = null;

        switch (button) {
            case "Buscar":
                Cliente cliente = getGestor().getCliente(getCodigo());
                ventana = (cliente != null) ? new VentanaFacturas() : new VentanaError();
                gestor.setClienteSelecionado(cliente);
                break;
            case "Volver":
                break;
        }

        return ventana;
    }

    Integer getCodigo() {
        final String codigo = getTextbox("Codigo");

        try {
            return Integer.parseInt(codigo);
        } catch (IllegalArgumentException ignored) {
        }

        return null;
    }
}
