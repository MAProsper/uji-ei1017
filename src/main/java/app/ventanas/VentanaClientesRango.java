package app.ventanas;

import app.Formato;
import app.Gestor;
import clientes.Cliente;
import com.google.common.collect.Range;
import helpers.Fecha;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaClientesRango extends Ventana {
    Range<LocalDate> periodo;

    public VentanaClientesRango(final Range<LocalDate> periodo) {
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
                gestor.setClienteSelecionado(cliente);
                ventana = gestor.getVisor();
                break;
            case "Volver":
                break;
        }

        return ventana;
    }
}
