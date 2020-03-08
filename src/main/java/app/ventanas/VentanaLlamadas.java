package app.ventanas;

import app.Formato;
import clientes.Cliente;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class VentanaLlamadas extends Ventana {
    public VentanaLlamadas() {
        super(
                "Llamadas",
                "Gestion de llamadas",
                true,
                Collections.emptyList(),
                Arrays.asList("Añadir llamada", "Volver"));
    }

    @Override
    public void update() {
        Cliente cliente = getGestor().getClienteSelecionado();
        setList(cliente.getLlamadas().stream().map(Formato::llamda).collect(Collectors.toList()));
    }

    @Override
    public Ventana handle(final String button) {
        Ventana ventana = null;

        switch (button) {
            case "Añadir llamada":
                ventana = new VentanaLlamadaNueva();
                break;
            case "Volver":
                break;
        }

        return ventana;
    }
}
