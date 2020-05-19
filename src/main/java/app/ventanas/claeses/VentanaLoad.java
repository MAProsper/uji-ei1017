package app.ventanas.claeses;

import app.Gestor;
import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.VentanaArchivo;

import java.nio.file.Path;
import java.util.Optional;

// Relacion Vista-Controlador
public class VentanaLoad extends VentanaArchivo {

    // Vista (define la vista contreta)
    public VentanaLoad() {
        super();
    }

    @Override
    final protected int showDialog() {
        return jFile.showOpenDialog(jFrame);
    }

    // Controlador (define el controlador concreto)
    @Override
    public Optional<Gestionable> processFile(final Path path) {
        // Modelo.load (3. actualiza el modelo)
        final Gestor gestor = getGestor();
        final Gestionable ventana = VentanaError.attempt(() -> gestor.load(path));
        return Optional.ofNullable(ventana);
    }
}
