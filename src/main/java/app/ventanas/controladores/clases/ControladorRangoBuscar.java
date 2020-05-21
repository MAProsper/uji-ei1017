package app.ventanas.controladores.clases;

import app.componentes.Accion;
import app.componentes.acciones.AccionBuscar;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaError;
import app.ventanas.vistas.clases.VistaRangoBuscar;

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
        validate("Acción tiene que ser esta vista", accion instanceof AccionBuscar);
        Vista vista = null;

        switch ((AccionBuscar) accion) {
            case BUSCAR:
                final VistaRangoBuscar vistaActual = (VistaRangoBuscar) getVista();
                vista = VistaError.attempt(vistaActual::getPeriodo, vistaActual.getTipo()::getVista);
                break;
            case VOLVER:
                break;
        }

        vistaNext(vista);
    }
}
