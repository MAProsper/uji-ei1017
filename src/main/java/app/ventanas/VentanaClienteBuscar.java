package app.ventanas;

import app.Gestor;
import clientes.Cliente;

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
                final Gestor gestor = getGestor();
                final Cliente cliente = gestor.getCliente(getTextbox("NIF"));
                gestor.setClienteSelecionado(cliente);
                ventana = gestor.getVisor();
                break;
            case "Volver":
                break;
        }

        return ventana;
    }
}
