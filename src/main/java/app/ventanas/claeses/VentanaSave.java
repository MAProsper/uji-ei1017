package app.ventanas.claeses;

import app.Gestor;
import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.VentanaArchivo;

import java.nio.file.Path;
import java.util.Optional;

public class VentanaSave extends VentanaArchivo {
    public VentanaSave() {
        super();
    }

    @Override
    public Optional<Gestionable> processFile(final Path path) {
        final Gestor gestor = getGestor();
        final Gestionable ventana = VentanaError.attempt(() -> gestor.save(path));
        return Optional.ofNullable(ventana);
    }

    @Override
    final protected int showDialog() {
        return jFile.showSaveDialog(jFrame);
    }
}
