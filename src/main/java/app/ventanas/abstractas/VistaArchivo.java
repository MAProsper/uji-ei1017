package app.ventanas.abstractas;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public abstract class VistaArchivo extends Vista {
    protected final JFrame jFrame;
    protected final JFileChooser jFile;
    protected final FileNameExtensionFilter filter;

    public VistaArchivo() {
        super();
        jFrame = new JFrame();
        jFile = new JFileChooser();
        filter = new FileNameExtensionFilter("Fichero binario", "bin");
        jFile.setFileFilter(filter);
    }

    protected abstract int showDialog();

    @Override
    final public void show() {
        super.show();
        final Vista ventana;
        if (showDialog() == JFileChooser.APPROVE_OPTION)
            ventana = processFile(getPath()).orElse(null);
        else ventana = null;
        showNext(ventana);
    }

    // Metodos para el Controlador (informarse de la vista)
    public final Path getPath() {
        final File file = jFile.getSelectedFile();
        return Paths.get(file.getPath());
    }

    // Controlador (define el controlador contreto)
    public abstract Optional<Vista> processFile(final Path path);
}
