package app.ventanas;

import app.Formato;
import app.Gestor;
import clientes.Cliente;

import java.util.stream.Collectors;

import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaClientes extends Ventana {
    public VentanaClientes() {
        super("Clientes", "Listado de clientes", true, Textbox.values(), Button.values());
    }

    @Override
    public void update() {
        setList(getGestor().getClientes().stream().map(Formato::cliente).collect(Collectors.toList()));
    }

    @Override
    public Ventana handle(final app.Button button) {
        Ventana ventana = null;
        final Gestor gestor = getGestor();

        switch ((Button) button) {
            case VER_CLIENTE:
                final Cliente cliente = gestor.getCliente(getTextbox(Textbox.SELECIONADO_NIF));
                gestor.setClienteSelecionado(cliente);
                ventana = gestor.getVisor();
                break;
            case NUEVO_CLIENTE:
                ventana = new VentanaClienteNuevo();
                break;
            case NUEVO_PARTICULAR:
                ventana = new VentanaClienteParticularNuevo();
                break;
            case NUEVO_EMPRESA:
                ventana = new VentanaClienteEmpresaNuevo();
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    enum Textbox implements app.Textbox {
        SELECIONADO_NIF("Selecionado NIF");

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
        NUEVO_CLIENTE("Nuevo cliente"),
        NUEVO_PARTICULAR("Nuevo particular"),
        NUEVO_EMPRESA("Nuevo empresa"),
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
