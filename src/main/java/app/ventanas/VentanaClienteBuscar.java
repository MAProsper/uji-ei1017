package app.ventanas;

import app.Gestor;
import clientes.Cliente;

import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaClienteBuscar extends Ventana {
    public VentanaClienteBuscar() {
        super(
                "Busqueda",
                "Intoduzca el NIF del cliente",
                false, Textbox.values(), Button.values());
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final app.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case BUSCAR:
                final Gestor gestor = getGestor();
                final Cliente cliente = gestor.getCliente(getTextbox(Textbox.NIF));
                gestor.setClienteSelecionado(cliente);
                ventana = gestor.getVisor();
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    enum Textbox implements app.Textbox {
        NIF("NIF");

        final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    enum Button implements app.Button {
        BUSCAR("Buscar"),
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
