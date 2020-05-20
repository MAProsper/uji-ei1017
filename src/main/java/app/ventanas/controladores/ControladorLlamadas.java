package app.ventanas.controladores;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.Vista;
import app.ventanas.acciones.AccionLlamadas;
import app.ventanas.interfaces.Accion;
import app.ventanas.vistas.VistaLlamadaNueva;
import app.ventanas.vistas.VistaLlamadas;
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
                final VistaLlamadas vistaLlamadas = (VistaLlamadas) getVista();
                vista = new VistaLlamadaNueva(vistaLlamadas.getCliente());
                break;
            case VOLVER:
                break;
            default:
                throw new Arguments.ValidationException("Acción no clasificada");
        }

        showNext(vista);
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Controlador tiene que ser del mismo tipo", vista, vista instanceof VistaLlamadas);
    }
}
