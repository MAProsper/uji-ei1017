package app.ventanas.claeses;

import app.Gestor;
import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.VentanaArchivo;

import java.nio.file.Path;
import java.util.Optional;

public class VentanaLoad extends VentanaArchivo {
    public VentanaLoad() {
        super();
    }

    @Override
    public Optional<Gestionable> processFile(final Path path) {
        final Gestor gestor = getGestor();
        final Gestionable ventana = VentanaError.attempt(() -> gestor.load(path));
        return Optional.ofNullable(ventana);
    }

    @Override
    final protected int showDialog() {
        return jFile.showOpenDialog(jFrame);
    }
}
