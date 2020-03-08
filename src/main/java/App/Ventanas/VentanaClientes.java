package App.Ventanas;

import App.Formato;
import App.Gestor;
import Clientes.Cliente;

import java.util.stream.Collectors;

import static Helpers.ValidatorArguments.stringNotEmpty;

public class VentanaClientes extends Ventana {
    public VentanaClientes() {
        super("Clientes", "Listado de clientes", true, Textbox.values(), Button.values());
    }

    @Override
    public void update() {
        setList(getGestor().getClientes().stream().map(Formato::cliente).collect(Collectors.toList()));
    }

    @Override
    public Ventana handle(final App.Button button) {
        Ventana ventana = null;
        final Gestor gestor = getGestor();

        switch ((Button) button) {
            case VER_CLIENTE:
                final Cliente cliente = gestor.getCliente(getTextbox(Textbox.SELECIONADO_NIF));
                ventana = (cliente != null) ? gestor.getVisor(cliente) : new VentanaError();
                gestor.setClienteSelecionado(cliente);
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

    enum Textbox implements App.Textbox {
        SELECIONADO_NIF("Selecionado NIF");

        final String descripcion;

        Textbox(final String descripcion) {
            this.descripcion = stringNotEmpty("descripcion", descripcion);
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    enum Button implements App.Button {
        VER_CLIENTE("Ver cliente"),
        NUEVO_CLIENTE("Nuevo cliente"),
        NUEVO_PARTICULAR("Nuevo particular"),
        NUEVO_EMPRESA("Nuevo empresa"),
        VOLVER("Volver");

        final String descripcion;

        Button(final String descripcion) {
            this.descripcion = stringNotEmpty("descripcion", descripcion);
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
