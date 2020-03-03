package App.Ventanas;

import App.Formato;
import App.Gestor;
import Clientes.Cliente;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class VentanaClientes extends Ventana {
    public VentanaClientes() {
        super(
                "Clientes",
                "Listado de clientes",
                true,
                Collections.singletonList("Selecionado NIF"),
                Arrays.asList("Ver cliente", "Nuevo cliente", "Nuevo particular", "Nueva empresa", "Volver"));
    }

    @Override
    public void update() {
        setList(getGestor().getClientes().stream().map(Formato::cliente).collect(Collectors.toList()));
    }

    @Override
    public Ventana handle(final String button) {
        Ventana ventana = null;
        final Gestor gestor = getGestor();

        switch (button) {
            case "Ver cliente":
                final Cliente cliente = gestor.getCliente(getTextbox("Selecionado NIF"));
                ventana = (cliente != null) ? gestor.getVisor(cliente) : new VentanaError();
                gestor.setClienteSelecionado(cliente);
                break;
            case "Nuevo cliente":
                ventana = new VentanaClienteNuevo();
                break;
            case "Nuevo particular":
                ventana = new VentanaClienteParticularNuevo();
                break;
            case "Nuevo empresa":
                ventana = new VentanaClienteEmpresaNuevo();
                break;
            case "Volver":
                break;
        }

        return ventana;
    }
}
