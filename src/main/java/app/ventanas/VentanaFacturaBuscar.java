package app.ventanas;

import app.Gestor;
import clientes.Cliente;

import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaFacturaBuscar extends Ventana {
    public VentanaFacturaBuscar() {
        super(
                "Busqueda",
                "Intoduzca el codigo de la factura",
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
                final Cliente cliente = gestor.getCliente(getCodigo());
                gestor.setClienteSelecionado(cliente);
                ventana = gestor.getVisor();
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    Integer getCodigo() {
        final String codigo = getTextbox(Textbox.CODIGO);

        try {
            return Integer.parseInt(codigo);
        } catch (IllegalArgumentException ignored) {
        }

        return null;
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

    enum Textbox implements app.Textbox {
        CODIGO("Codigo");
        final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

}
