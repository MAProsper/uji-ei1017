package app.ventanas.claeses;

import app.Gestor;
import app.ventanas.abstractas.Ventana;
import clientes.Cliente;

import static helpers.estaticos.Arguments.stringNotEmpty;

public class VentanaClienteBuscar extends Ventana {
    public VentanaClienteBuscar() {
        super(
                "Busqueda",
                "Intoduzca el NIF del cliente",
                false, Textbox.values(), Button.values());
    }

    @Override
    protected void update() {
    }

    @Override
    public Ventana handle(final app.ventanas.interfaces.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case BUSCAR:
                final Gestor gestor = getGestor();
                final Cliente cliente = gestor.getCliente(getTextbox(Textbox.NIF));
                ventana = gestor.getVisor(cliente);
                break;
            case VOLVER:
                break;
        }

        return ventana;
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
