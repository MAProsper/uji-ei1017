package app.ventanas.controladores.clases;

import app.helpers.clases.Modelo;
import app.ventanas.controladores.abstractas.ControladorArchivo;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaError;
import app.ventanas.vistas.clases.VistaSave;

import java.nio.file.Path;
import java.util.Optional;

import static helpers.estaticos.Arguments.validate;

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
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaSave);
    }
}