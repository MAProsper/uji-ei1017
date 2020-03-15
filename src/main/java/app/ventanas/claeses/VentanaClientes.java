package app.ventanas.claeses;

import app.Formato;
import app.Gestor;
import app.ventanas.abstractas.Ventana;

import java.util.Optional;
import java.util.stream.Collectors;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.stringNotEmpty;

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
    public Optional<Ventana> pressButton(final app.ventanas.interfaces.Button button) {
        Ventana ventana = null;

        switch ((Button) referenceNotNull("Button", button)) {
            case VER_CLIENTE:
                final Gestor gestor = getGestor();
                final String NIF = getTextbox(Textbox.SELECIONADO_NIF);
                ventana = VentanaError.attempt(() -> gestor.buscarCliente(NIF), gestor::getVisor);
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

        return Optional.ofNullable(ventana);
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        SELECIONADO_NIF("Selecionado NIF");

        private final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Button implements app.ventanas.interfaces.Button {
        VER_CLIENTE("Ver cliente"),
        NUEVO_CLIENTE("Nuevo cliente"),
        NUEVO_PARTICULAR("Nuevo particular"),
        NUEVO_EMPRESA("Nuevo empresa"),
        VOLVER("Volver");

        private final String description;

        Button(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
