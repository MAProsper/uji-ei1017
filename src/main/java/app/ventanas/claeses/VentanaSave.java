package app.ventanas.claeses;

import app.Gestor;
import app.ventanas.abstractas.Gestionable;
import app.ventanas.abstractas.VentanaArchivo;

import java.nio.file.Path;
import java.util.Optional;

// Relacion Vista-Controlador
public class VentanaSave extends VentanaArchivo {
    // Vista (define la vista contreta)
    public VentanaSave() {
        super();
    }

    @Override
    final protected int showDialog() {
        return jFile.showSaveDialog(jFrame);
    }

    // Controlador (define el controlador concreto)
    @Override
    public Optional<Gestionable> processFile(final Path path) {
        // Modelo.save (3. actualiza el modelo)
        final Gestor gestor = getGestor();
        final Gestionable ventana = VentanaError.attempt(() -> gestor.save(path));
        return Optional.ofNullable(ventana);
    }

}
