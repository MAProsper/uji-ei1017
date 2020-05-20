package app.ventanas.controladores;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.Vista;
import app.ventanas.acciones.AccionRangoBuscar;
import app.ventanas.interfaces.Accion;
import app.ventanas.vistas.VistaError;
import app.ventanas.vistas.VistaRangoBuscar;

import static helpers.estaticos.Arguments.validate;

public class ControladorRangoBuscar extends Controlador {
    public ControladorRangoBuscar() {
        super();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaRangoBuscar);
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta vista", accion instanceof AccionRangoBuscar);
        Vista vista = null;

        switch ((AccionRangoBuscar) accion) {
            case BUSCAR:
                final VistaRangoBuscar vistaBusqueda = (VistaRangoBuscar) getVista();
                vista = VistaError.attempt(vistaBusqueda::getPeriodo, vistaBusqueda.getTipo()::getVista);
                break;
            case VOLVER:
                break;
        }

        showNext(vista);
    }
}
