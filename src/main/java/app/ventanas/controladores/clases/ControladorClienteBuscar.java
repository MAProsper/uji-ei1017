package app.ventanas.controladores.clases;

import app.componentes.Accion;
import app.componentes.acciones.AccionBuscar;
import app.componentes.textboxes.TextboxClienteBuscar;
import app.helpers.clases.Modelo;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaClienteBuscar;
import app.ventanas.vistas.clases.VistaError;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public class ControladorClienteBuscar extends Controlador {
    public ControladorClienteBuscar(){
        super();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaClienteBuscar);
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionBuscar);
        Vista vista = null;

        switch ((AccionBuscar) accion) {
            case BUSCAR:
                final Modelo modelo = getModelo();
                final VistaClienteBuscar vistaActual = (VistaClienteBuscar) getVista();
                final String NIF = vistaActual.getTextbox(TextboxClienteBuscar.NIF);
                vista = VistaError.attempt(() -> modelo.buscarCliente(NIF), modelo::getVisor);
                break;
            case VOLVER:
                break;
            default:
                throw new Arguments.ValidationException("Acción no clasificada");
        }

        vistaNext(vista);
    }
}
