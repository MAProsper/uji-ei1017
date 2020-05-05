package app.ventanas.claeses;

import app.Gestor;
import app.ventanas.abstractas.Ventana;
import app.ventanas.interfaces.Table;
import app.ventanas.interfaces.Textbox;
import helpers.estaticos.Arguments;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static helpers.estaticos.Arguments.stringNotEmpty;
import static helpers.estaticos.Arguments.validate;

public class VentanaSaveSwing extends Ventana {
    private final JFileChooser selector;

    public VentanaSaveSwing() {
        super("Guardar datos", "Introduce la ruta del fichero a guardar", Table.empty(), Textbox.empty(), Button.values());
        selector = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Fichero binario", "bin");
        selector.setFileFilter(filtro);
    }

    @Override
    public Optional<Ventana> pressButton(final app.ventanas.interfaces.Button button) {
        validate("Button tiene que ser esta ventana", button instanceof Button);
        Ventana ventana;

        if (button == Button.GUARDAR) {
            final File file = selector.getSelectedFile();
            if (file != null) {
                final Path path = Paths.get(file.getPath());
                final Gestor gestor = getGestor();
                ventana = VentanaError.attempt(() -> gestor.save(path));
            } else ventana = null;
        } else {
            throw new Arguments.ValidationException("Button no clasificado");
        }

        return Optional.ofNullable(ventana);
    }

    @Override
    public void show() {
        if (!running.tryAcquire()) {
            throw new OverlappingVentanaException();
        } else {
            selector.showSaveDialog(jFrame); //FIXME: CANCEL_OPTION, APPROVE_OPTION, ERROR_OPTION
            running.release();
            getGestor().showNext(pressButton(Button.GUARDAR).orElse(null));
        }
    }

    public enum Button implements app.ventanas.interfaces.Button {
        GUARDAR("Guardar");

        private final String description;

        Button(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
