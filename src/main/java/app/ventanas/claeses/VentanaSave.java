package app.ventanas.claeses;

import app.Gestor;
import app.ventanas.abstractas.Ventana;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.stringNotEmpty;

public class VentanaSave extends Ventana {
    public VentanaSave() {
        super(
                "Guardar datos",
                "Introduce la ruta del fichero a guardar",
                false, Textbox.values(), Button.values());
    }

    @Override
    public Optional<Ventana> pressButton(final app.ventanas.interfaces.Button button) {
        Ventana ventana = null;

        switch ((Button) referenceNotNull("Button", button)) {
            case GUARDAR:
                final Path path = getPath();
                final Gestor gestor = getGestor();
                ventana = VentanaError.attempt(() -> gestor.save(path));
                break;
            case VOLVER:
                break;
        }

        return Optional.ofNullable(ventana);
    }

    protected Path getPath() {
        return Paths.get(getTextbox(Textbox.PATH));
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        PATH("Ruta");

        private final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    public enum Button implements app.ventanas.interfaces.Button {
        GUARDAR("Guardar"),
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
