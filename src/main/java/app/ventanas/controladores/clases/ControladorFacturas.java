package app.ventanas.controladores.clases;

import app.componentes.Accion;
import app.componentes.acciones.AccionFacturas;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaFacturaNueva;
import app.ventanas.vistas.clases.VistaFacturas;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public class ControladorFacturas extends Controlador {
    public ControladorFacturas() {
        super();
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser de este controlador", accion instanceof AccionFacturas);
        Vista vista = null;

        switch ((AccionFacturas) accion) {
            case ANYADIR_FACTURA:
                final VistaFacturas vistaActual = (VistaFacturas) getVista();
                vista = new VistaFacturaNueva(vistaActual.getCliente());
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
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaFacturas);
    }
}
