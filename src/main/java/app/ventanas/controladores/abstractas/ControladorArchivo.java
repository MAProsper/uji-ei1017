package app.ventanas.controladores.abstractas;

import app.componentes.Accion;
import app.componentes.acciones.AccionArchivo;
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
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionArchivo);
        Vista vista = null;

        switch ((AccionArchivo) accion) {
            case PROCESAR:
                final VistaArchivo vistaActual = (VistaArchivo) getVista();
                vista = processFile(vistaActual.getPath()).orElse(null);
                break;
            case CANCELAR:
                break;
            default:
                throw new Arguments.ValidationException("Acción no clasificada");
        }

        vistaNext(vista);
    }
}
