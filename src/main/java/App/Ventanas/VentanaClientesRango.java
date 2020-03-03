package App.Ventanas;

import App.Formato;
import App.Gestor;
import Clientes.Cliente;
import Helpers.Fecha;
import com.google.common.collect.Range;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaClientesRango extends Ventana {
    Range<Date> periodo;

    public VentanaClientesRango(final Range<Date> periodo) {
        super("Clientes (rango)",
                "Los clientes en el periodo " + Formato.periodo(periodo),
                true,
                Collections.singletonList("Selecionado NIF"),
                Arrays.asList("Ver cliente", "Volver"));
        this.periodo = periodo;
    }

    @Override
    public void update() {
        final List<Cliente> clientes = Fecha.filterRange(getGestor().getClientes(), periodo);
        setList(clientes.stream().map(Formato::cliente).collect(Collectors.toList()));
    }

    @Override
    public Ventana handle(String button) {
        Ventana ventana = null;
        final Gestor gestor = getGestor();

        switch (button) {
            case "Ver cliente":
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
