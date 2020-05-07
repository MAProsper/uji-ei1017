package app.ventanas.abstractas;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public abstract class VentanaArchivo extends Gestionable {
    protected final JFrame jFrame;
    protected final JFileChooser jFile;
    protected final FileNameExtensionFilter filter;

    public VentanaArchivo() {
        super();
        jFrame = new JFrame();
        jFile = new JFileChooser();
        filter = new FileNameExtensionFilter("Fichero binario", "bin");
        jFile.setFileFilter(filter);
    }

    final protected Path getPath() {
        final File file = jFile.getSelectedFile();
        return Paths.get(file.getPath());
    }

    protected abstract Optional<Gestionable> processFile();

    protected abstract int showDialog();

    @Override
    final public void show() {
        super.show();
        final Gestionable ventana;
        if (showDialog() == JFileChooser.APPROVE_OPTION)
            ventana = processFile().orElse(null);
        else ventana = null;
        showNext(ventana);
    }
}
