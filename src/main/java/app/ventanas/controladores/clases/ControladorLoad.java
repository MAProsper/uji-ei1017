package app.ventanas.controladores.clases;

import app.Modelo;
import app.ventanas.controladores.abstractas.ControladorArchivo;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaError;
import app.ventanas.vistas.clases.VistaLoad;

import java.nio.file.Path;
import java.util.Optional;

import static helpers.estaticos.Arguments.validate;

public class ControladorLoad extends ControladorArchivo {
    public ControladorLoad() {
        super();
    }

    @Override
    public Optional<Vista> processFile(final Path path) {
        final Modelo modelo = getModelo();
        final Vista ventana = VistaError.attempt(() -> modelo.load(path));
        return Optional.ofNullable(ventana);
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaLoad);
    }
}
