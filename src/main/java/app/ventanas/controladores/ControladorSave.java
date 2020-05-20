package app.ventanas.controladores;

import app.Modelo;
import app.ventanas.abstractas.ControladorArchivo;
import app.ventanas.abstractas.Vista;
import app.ventanas.vistas.VistaError;
import app.ventanas.vistas.VistaSave;

import java.nio.file.Path;
import java.util.Optional;

import static helpers.estaticos.Arguments.validate;

public class ControladorSave extends ControladorArchivo {
    public ControladorSave() {
        super();
    }

    @Override
    public Optional<Vista> processFile(final Path path) {
        // Modelo.load (3. actualiza el modelo)
        final Modelo modelo = getModelo();
        final Vista ventana = VistaError.attempt(() -> modelo.save(path));
        return Optional.ofNullable(ventana);
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Controlador tiene que ser del mismo tipo", vista, vista instanceof VistaSave);
    }
}