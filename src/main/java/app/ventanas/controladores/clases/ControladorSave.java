package app.ventanas.controladores.clases;

import app.helpers.clases.Modelo;
import app.ventanas.controladores.abstractas.ControladorArchivo;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaError;
import app.ventanas.vistas.clases.VistaSave;

import java.nio.file.Path;
import java.util.Optional;

public class ControladorSave extends ControladorArchivo {
    public ControladorSave() {
        super();
    }

    @Override
    public Optional<Vista> processFile(final Path path) {
        final Modelo modelo = getModelo();
        final Vista vista = VistaError.attempt(() -> modelo.save(path));
        return Optional.ofNullable(vista);
    }

    @Override
    protected boolean validateVista(final Vista vista) {
        return vista instanceof VistaSave;
    }
}