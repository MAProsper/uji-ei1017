package app.ventanas.claeses;

import app.Gestor;
import app.ventanas.abstractas.Ventana;
import helpers.estaticos.Parse;

import static helpers.estaticos.Arguments.ValidationException;
import static helpers.estaticos.Arguments.stringNotEmpty;

public class VentanaFacturaBuscar extends Ventana {
    public VentanaFacturaBuscar() {
        super(
                "Busqueda",
                "Intoduzca el codigo de la factura",
                false, Textbox.values(), Button.values());
    }

    @Override
    protected void update() {
    }

    @Override
    protected Ventana handle(final app.ventanas.interfaces.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case BUSCAR:
                final Gestor gestor = getGestor();
                Integer codigo = null;

                try {
                    codigo = getCodigo();
                } catch (ValidationException e) {
                    ventana = new VentanaError(e);
                }

                if (codigo != null) ventana = gestor.getVisor(gestor.getCliente(codigo));
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    protected Integer getCodigo() {
        final String codigo = getTextbox(Textbox.CODIGO);
        return Parse.entreo(Textbox.CODIGO.getDescription(), codigo);
    }

    private enum Button implements app.ventanas.interfaces.Button {
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

    private enum Textbox implements app.ventanas.interfaces.Textbox {
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
