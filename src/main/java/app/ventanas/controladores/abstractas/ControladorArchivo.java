package app.ventanas.controladores.abstractas;

import app.componentes.Button;
import app.componentes.buttons.ButtonArchivo;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.abstractas.VistaArchivo;
import helpers.estaticos.Arguments;

import java.nio.file.Path;
import java.util.Optional;

import static helpers.estaticos.Arguments.validate;

public abstract class ControladorArchivo extends Controlador {
    public ControladorArchivo() {
        super();
    }

    public abstract Optional<Vista> processFile(final Path path);

    @Override
    public void gestionaButton(final Button button) {
        validate("Button tiene que ser de este controlador", button instanceof ButtonArchivo);
        Vista vista = null;

        switch ((ButtonArchivo) button) {
            case PROCESAR:
                final VistaArchivo vistaActual = (VistaArchivo) getVista();
                vista = processFile(vistaActual.getPath()).orElse(null);
                break;
            case CANCELAR:
                break;
            default:
                throw new Arguments.ValidationException("Button no clasificado");
        }

        vistaNext(vista);
    }
}
