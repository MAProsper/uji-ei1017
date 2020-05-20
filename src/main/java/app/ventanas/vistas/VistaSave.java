package app.ventanas.vistas;

import app.Modelo;
import app.ventanas.abstractas.Vista;
import app.ventanas.abstractas.VistaArchivo;

import java.nio.file.Path;
import java.util.Optional;

// Relacion Vista-Controlador
public class VistaSave extends VistaArchivo {
    // Vista (define la vista contreta)
    public VistaSave() {
        super();
    }

    @Override
    final protected int showDialog() {
        return jFile.showSaveDialog(jFrame);
    }

    // Controlador (define el controlador concreto)
    @Override
    public Optional<Vista> processFile(final Path path) {
        // Modelo.save (3. actualiza el modelo)
        final Modelo modelo = getModelo();
        final Vista ventana = VistaError.attempt(() -> modelo.save(path));
        return Optional.ofNullable(ventana);
    }
}
