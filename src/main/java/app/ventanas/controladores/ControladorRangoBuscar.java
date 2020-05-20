package app.ventanas.controladores;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.Vista;
import app.ventanas.acciones.AccionRangoBuscar;
import app.ventanas.helpers.TipoRangoBuscar;
import app.ventanas.interfaces.Accion;
import app.ventanas.vistas.VistaError;
import app.ventanas.vistas.VistaRangoBuscar;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.validate;

public class ControladorRangoBuscar extends Controlador {
    protected final TipoRangoBuscar tipo;

    public ControladorRangoBuscar(final TipoRangoBuscar tipo) {
        this.tipo = referenceNotNull("Tipo", tipo);
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaRangoBuscar);
    }

    public final TipoRangoBuscar getTipo() {
        return tipo;
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionRangoBuscar);
        Vista ventana = null;

        switch ((AccionRangoBuscar) accion) {
            case BUSCAR:
                final VistaRangoBuscar vistaBusqueda = (VistaRangoBuscar) getVista();
                ventana = VistaError.attempt(vistaBusqueda::getPeriodo, tipo::getVista);
                break;
            case VOLVER:
                break;
        }

        showNext(ventana);
    }
}
