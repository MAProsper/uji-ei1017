package app.ventanas.abstractas;

import app.ventanas.acciones.AccionArchivo;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        final boolean valido = (showDialog() == JFileChooser.APPROVE_OPTION);
        getControlador().gestionaAccion(valido ? AccionArchivo.PROCESAR : AccionArchivo.CANCELAR);
    }

    public final Path getPath() {
        final File file = jFile.getSelectedFile();
        return Paths.get(file.getPath());
    }
}
