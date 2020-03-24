package app.ventanas.claeses;

import app.Gestor;
import app.ventanas.abstractas.Ventana;

import java.util.Optional;

import static helpers.estaticos.Arguments.stringNotEmpty;
import static helpers.estaticos.Arguments.validate;

public class VentanaClienteBuscar extends Ventana {
    public VentanaClienteBuscar() {
        super(
                "Busqueda",
                "Intoduzca el NIF del cliente",
                false, Textbox.values(), Button.values());
    }

    @Override
    public Optional<Ventana> pressButton(final app.ventanas.interfaces.Button button) {
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Ventana ventana = null;

        switch ((Button) button) {
            case BUSCAR:
                final Gestor gestor = getGestor();
                final String NIF = getTextbox(Textbox.NIF);
                ventana = VentanaError.attempt(() -> gestor.buscarCliente(NIF), gestor::getVisor);
                break;
            case VOLVER:
                break;
            default:
                validate("Button no clasificado", false);
        }

        return Optional.ofNullable(ventana);
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        NIF("NIF");

        private final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Button implements app.ventanas.interfaces.Button {
        BUSCAR("Buscar"),
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
