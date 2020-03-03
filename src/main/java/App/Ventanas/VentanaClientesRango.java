package App.Ventanas;

import App.Formato;
import App.Gestor;
import Clientes.Cliente;
import com.google.common.collect.Range;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaClientesRango extends Ventana {

    Range<Date> periodo;

    public VentanaClientesRango(final Range<Date> periodo) {
        super("Clientes en el Periodo",
                "Los clientes en el periodo " + periodo + " son:",
                false,
                Collections.emptyList(),
                Arrays.asList("Ver Cliente", "Volver"));
        this.periodo = periodo;
    }

    @Override
    public void update() {
        Gestor gestor = getGestor();
        final List<Cliente> clientes = gestor.filterRange(gestor.getClientes(), periodo);
        setList(clientes.stream().map(Formato::cliente).collect(Collectors.toList()));
    }

    @Override
    public Ventana handle(String button) {
        Ventana ventana = null;
        Gestor gestor = getGestor();
        switch (button) {
            case "Ver Cliente":
                final Cliente cliente = gestor.getCliente(getTextbox("Selecionado NIF"));
                ventana = (cliente != null) ? gestor.getVisor(cliente) : new VentanaError();
                gestor.setClienteSelecionado(cliente);
                break;
            case "Volver":
                break;
        }
        return ventana;
    }
}
