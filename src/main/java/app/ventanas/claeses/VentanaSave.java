package app.ventanas.claeses;

import app.Gestor;
import app.ventanas.abstractas.Ventana;

import java.nio.file.Path;
import java.nio.file.Paths;

import static helpers.estaticos.ValidatorArguments.ValidationException;
import static helpers.estaticos.ValidatorArguments.stringNotEmpty;

public class VentanaSave extends Ventana {
    public VentanaSave() {
        super(
                "Guardar datos",
                "Introduce la ruta del fichero a guardar",
                false, Textbox.values(), Button.values());
    }

    @Override
    protected void update() {
    }

    @Override
    protected Ventana handle(final app.ventanas.interfaces.Button button) {
        Ventana ventana = null;
        final Gestor gestor = getGestor();

        switch ((Button) button) {
            case GUARDAR:
                ventana = save(getPath());
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    protected Path getPath() {
        return Paths.get(getTextbox(Textbox.PATH));
    }

    protected Ventana save(final Path path) {
        Ventana ventana = null;

        try {
            getGestor().save(path);
        } catch (ValidationException e) {
            ventana = new VentanaError(e);
        }

        return ventana;
    }

    private enum Textbox implements app.ventanas.interfaces.Textbox {
        PATH("Ruta");

        final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    private enum Button implements app.ventanas.interfaces.Button {
        GUARDAR("Guardar"),
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
