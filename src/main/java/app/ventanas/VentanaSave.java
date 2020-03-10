package app.ventanas;

import app.Gestor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaSave extends Ventana {

    public VentanaSave() {
        super(
                "Guardar datos",
                "Introduce la ruta del fichero a guardar",
                false, Textbox.values(), Button.values());
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final app.Button button) {
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

    public Path getPath() {
        return Paths.get(getTextbox(Textbox.PATH));
    }

    public Ventana save(final Path path) {
        Ventana ventana = null;

        try {
            getGestor().save(path);
        } catch (IOException ignored) {
            ventana = new VentanaError();
        }

        return ventana;
    }

    enum Textbox implements app.Textbox {
        PATH("Ruta");

        final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    enum Button implements app.Button {
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
