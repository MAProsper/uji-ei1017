package app.ventanas.claeses;

import app.Formato;
import app.Gestor;
import app.ventanas.abstractas.Ventana;
import clientes.Cliente;

import java.util.stream.Collectors;

import static helpers.estaticos.ValidatorArguments.stringNotEmpty;

public class VentanaClientes extends Ventana {
    public VentanaClientes() {
        super(
                "Clientes",
                "Listado de clientes",
                true, Textbox.values(), Button.values());
    }

    @Override
    protected void update() {
        setList(getGestor().getClientes().stream().map(Formato::cliente).collect(Collectors.toList()));
    }

    @Override
    protected Ventana handle(final app.ventanas.interfaces.Button button) {
        Ventana ventana = null;
        final Gestor gestor = getGestor();

        switch ((Button) button) {
            case VER_CLIENTE:
                final String NIF = getTextbox(Textbox.SELECIONADO_NIF);
                final Cliente cliente = gestor.getCliente(NIF);
                ventana = gestor.getVisor(cliente);
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

    private enum Textbox implements app.ventanas.interfaces.Textbox {
        SELECIONADO_NIF("Selecionado NIF");

        final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    private enum Button implements app.ventanas.interfaces.Button {
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
