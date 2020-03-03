package App.Ventanas;

import App.Gestor;
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
                final Gestor gestor = getGestor();
                final Cliente cliente = gestor.getCliente(getCodigo());
                ventana = (cliente != null) ? gestor.getVisor(cliente) : new VentanaError();
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
