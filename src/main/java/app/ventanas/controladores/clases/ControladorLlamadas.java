package app.ventanas.controladores.clases;

import app.componentes.Accion;
import app.componentes.acciones.AccionLlamadas;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaLlamadaNueva;
import app.ventanas.vistas.clases.VistaLlamadas;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public class ControladorLlamadas extends Controlador {
    public ControladorLlamadas() {
        super();
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionLlamadas);
        Vista vista = null;

        switch ((AccionLlamadas) accion) {
            case NUEVA_LLAMADA:
                final VistaLlamadas vistaActual = (VistaLlamadas) getVista();
                vista = new VistaLlamadaNueva(vistaActual.getCliente());
                break;
            case VOLVER:
                break;
            default:
                throw new Arguments.ValidationException("Acción no clasificada");
        }

        vistaNext(vista);
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaLlamadas);
    }
}
