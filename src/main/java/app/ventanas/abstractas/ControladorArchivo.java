package app.ventanas.abstractas;

import app.ventanas.acciones.AccionArchivo;
import app.ventanas.interfaces.Accion;
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
    public void gestionaAccion(Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionArchivo);
        Vista vista = null;

        switch ((AccionArchivo) accion) {
            case PROCESAR:
                final VistaArchivo vistaArchivo = (VistaArchivo) getVista();
                vista = processFile(vistaArchivo.getPath()).orElse(null);
                break;
            case CANCELAR:
                break;
            default:
                throw new Arguments.ValidationException("Acción no clasificada");
        }

        showNext(vista);
    }
}
