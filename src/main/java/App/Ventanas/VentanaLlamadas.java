package App.Ventanas;

import Clientes.Cliente;
import Helpers.Llamada;

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
        setList(cliente.getLlamadas().stream().map(this::resumirLlamda).collect(Collectors.toList()));
    }

    String resumirLlamda(final Llamada llamada) {
        return String.format("[%s] %s (%s min)", llamada.getFecha(), llamada.getTelefono(), llamada.getDuracion());
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
