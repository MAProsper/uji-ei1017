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
    protected Optional<Gestionable> processFile() {
        Gestionable ventana;
        final Optional<Path> selected = getPath();

        if (selected.isPresent()) {
            final Path path = selected.get();
            final Gestor gestor = getGestor();
            ventana = VentanaError.attempt(() -> gestor.load(path));
        } else ventana = null;

        return Optional.ofNullable(ventana);
    }

    @Override
    final protected int showDialog() {
        return jFile.showOpenDialog(jFrame);
    }
}
