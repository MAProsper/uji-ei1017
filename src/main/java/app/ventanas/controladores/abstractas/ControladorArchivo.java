package app.ventanas.controladores.abstractas;

import app.componentes.Button;
import app.componentes.buttons.ButtonArchivo;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.abstractas.VistaArchivo;

import java.nio.file.Path;
import java.util.Optional;

public abstract class ControladorArchivo extends Controlador {
    public ControladorArchivo() {
        super();
    }

    public abstract Optional<Vista> processFile(final Path path);

    @Override
    public boolean validateButton(final Button button) {
        return button instanceof ButtonArchivo;
    }

    @Override
    public void gestionaButton(final Button button) {
        super.gestionaButton(button);
        Vista vista = null;

        switch ((ButtonArchivo) button) {
            case PROCESAR:
                final VistaArchivo vistaActual = (VistaArchivo) getVista();
                vista = processFile(vistaActual.getPath()).orElse(null);
                break;
            case CANCELAR:
                break;
            default:
                throw Button.MISSING;
        }

        vistaNext(vista);
    }
}
