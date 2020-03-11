package app.ventanas.abstractas;

import app.ventanas.claeses.VentanaError;
import app.ventanas.interfaces.Textbox;

import static helpers.estaticos.Arguments.ValidationException;
import static helpers.estaticos.Arguments.stringNotEmpty;

public abstract class VentanaNuevo extends Ventana {
    public VentanaNuevo(final Textbox[] textboxes) {
        super(
                "Nuevo",
                "Rellena los attributos de la nueva entrada",
                true, textboxes, Button.values());
    }

    @Override
    protected void update() {
    }

    @Override
    protected Ventana handle(final app.ventanas.interfaces.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case CREAR:
                try {
                    crear();
                } catch (ValidationException e) {
                    ventana = new VentanaError(e);
                }
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    protected abstract void crear();

    private enum Button implements app.ventanas.interfaces.Button {
        CREAR("Crear"),
        VOLVER("Volver");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
