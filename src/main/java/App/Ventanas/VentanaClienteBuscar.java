package App.Ventanas;

import App.Gestor;
import Clientes.Cliente;

import java.util.Arrays;
import java.util.Collections;

public class VentanaClienteBuscar extends Ventana {
    public VentanaClienteBuscar() {
        super(
                "Busqueda",
                "Intoduzca el NIF del cliente",
                false,
                Collections.singletonList("NIF"),
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
                Gestor gestor = getGestor();
                Cliente cliente = gestor.getCliente(getTextbox("NIF"));
                ventana = (cliente != null) ? gestor.getVisor(cliente) : new VentanaError();
                gestor.setClienteSelecionado(cliente);
                break;
            case "Volver":
                break;
        }

        return ventana;
    }
}
