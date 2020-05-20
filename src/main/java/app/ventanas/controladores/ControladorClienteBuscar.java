package app.ventanas.controladores;

import app.Modelo;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.Vista;
import app.ventanas.acciones.AccionClienteBuscar;
import app.ventanas.interfaces.Accion;
import app.ventanas.textboxes.TextboxClienteBuscar;
import app.ventanas.vistas.VistaClienteBuscar;
import app.ventanas.vistas.VistaError;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public class ControladorClienteBuscar extends Controlador {

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Controlador tiene que ser del mismo tipo", vista, vista instanceof VistaClienteBuscar);
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionClienteBuscar);
        Vista vista = null;

        switch ((AccionClienteBuscar) accion) {
            case BUSCAR:
                final Modelo modelo = getModelo();
                final VistaClienteBuscar vistaBusqueda = (VistaClienteBuscar) getVista();
                final String NIF = vistaBusqueda.getTextbox(TextboxClienteBuscar.NIF);
                vista = VistaError.attempt(() -> modelo.buscarCliente(NIF), modelo::getVisor);
                break;
            case VOLVER:
                break;
            default:
                throw new Arguments.ValidationException("Acción no clasificada");
        }

        showNext(vista);
    }
}
