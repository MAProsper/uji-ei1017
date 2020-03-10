package app.ventanas;

import app.Formato;
import app.Gestor;
import clientes.Cliente;
import com.google.common.collect.Range;
import helpers.Fecha;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaClientesRango extends Ventana {
    Range<LocalDate> periodo;

    public VentanaClientesRango(final Range<LocalDate> periodo) {
        super("Clientes (rango)",
                "Los clientes en el periodo " + Formato.periodo(periodo),
                true, Textbox.values(), Button.values());
        this.periodo = periodo;
    }

    @Override
    public void update() {
        final List<Cliente> clientes = Fecha.filterRange(getGestor().getClientes(), periodo);
        setList(clientes.stream().map(Formato::cliente).collect(Collectors.toList()));
    }

    @Override
    public Ventana handle(final app.Button button) {
        Ventana ventana = null;
        final Gestor gestor = getGestor();

        switch ((Button) button) {
            case VER_CLIENTE:
                final Cliente cliente = gestor.getCliente(getTextbox(Textbox.SELECIONAR_NIF));
                gestor.setClienteSelecionado(cliente);
                ventana = gestor.getVisor();
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    enum Textbox implements app.Textbox {
        SELECIONAR_NIF("NIF");

        final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    enum Button implements app.Button {
        VER_CLIENTE("Ver cliente"),
        VOLVER("Volver");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
