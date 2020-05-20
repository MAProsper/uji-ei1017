package app.ventanas.claeses;

import app.Gestor;
import app.Parser;
import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Table;

import java.util.Optional;

import static helpers.estaticos.Arguments.*;

// Relacion Vista-Controlador
public class VentanaFacturaBuscar extends Ventana {
    // Vista (define la vista contreta)
    public VentanaFacturaBuscar() {
        super(
                "Busqueda",
                "Intoduzca el codigo de la factura",
                Table.empty(), Textbox.values(), Button.values());
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

    public final int getCodigo() {
        final String codigo = getTextbox(Textbox.CODIGO);
        return Parser.entreo(Textbox.CODIGO.getDescription(), codigo);
    }

    // Controlador (define el controlador concreto)
    @Override
    public Optional<Gestionable> pressButton(final app.ventanas.interfaces.Button button) { // Gestiona la acción del usuario
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Gestionable ventana = null;

        switch ((Button) button) {
            case BUSCAR:
                final Gestor gestor = getGestor();

                // getCodigo (2. solicita datos a la vista)
                // Modelo.buscarCliente (3. consulta datos del modelo)
                ventana = VentanaError.attempt(() -> gestor.buscarCliente(getCodigo()), gestor::getVisor);
                break;
            case VOLVER:
                break;
            default:
                throw new ValidationException("Button no clasificado");
        }

        return Optional.ofNullable(ventana);
    }
}
