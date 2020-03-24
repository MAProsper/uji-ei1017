package app.ventanas.claeses;

import app.Gestor;
import app.Parser;
import app.ventanas.abstractas.Ventana;

import java.util.Optional;

import static helpers.estaticos.Arguments.stringNotEmpty;
import static helpers.estaticos.Arguments.validate;

public class VentanaFacturaBuscar extends Ventana {
    public VentanaFacturaBuscar() {
        super(
                "Busqueda",
                "Intoduzca el codigo de la factura",
                false, Textbox.values(), Button.values());
    }

    @Override
    public Optional<Ventana> pressButton(final app.ventanas.interfaces.Button button) {
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Ventana ventana = null;

        switch ((Button) button) {
            case BUSCAR:
                final Gestor gestor = getGestor();
                ventana = VentanaError.attempt(() -> gestor.buscarCliente(getCodigo()), gestor::getVisor);
                break;
            case VOLVER:
                break;
            default:
                validate("Button no clasificado", false);
        }

        return Optional.ofNullable(ventana);
    }

    protected Integer getCodigo() {
        final String codigo = getTextbox(Textbox.CODIGO);
        return Parser.entreo(Textbox.CODIGO.getDescription(), codigo);
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        CODIGO("Codigo");
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
