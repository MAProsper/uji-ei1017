package App.Ventanas;

import App.Gestor;
import Clientes.Cliente;
import Clientes.ClienteEmpresa;
import Clientes.ClientePaticular;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
        List<String> clientes = new LinkedList<>();
        for (Cliente cliente : getGestor().getClientes())
            clientes.add(resumirCliente(cliente));
        setList(clientes);
    }

    String resumirCliente(final Cliente cliente) {
        String tipo = "Otros";
        String nombre = cliente.getNombre();

        if (cliente instanceof ClientePaticular) {
            final ClientePaticular particular = (ClientePaticular) cliente;
            tipo = "Particular";
            nombre = String.format("%s, %s", particular.getApellidos(), particular.getNombre());
        } else if (cliente instanceof ClienteEmpresa) {
            final ClienteEmpresa empresa = (ClienteEmpresa) cliente;
            tipo = "Empresa";
        }

        return String.format("[%s] [%s] %s", cliente.getNIF(), tipo, nombre);
    }

    @Override
    public Ventana handle(final String button) {
        Ventana ventana = null;
        Gestor gestor = getGestor();

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
