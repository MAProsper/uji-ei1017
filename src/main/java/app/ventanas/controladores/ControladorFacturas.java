package app.ventanas.controladores;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.Vista;
import app.ventanas.acciones.AccionFacturas;
import app.ventanas.interfaces.Accion;
import app.ventanas.vistas.VistaFacturaNueva;
import app.ventanas.vistas.VistaFacturas;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public class ControladorFacturas extends Controlador {
    public ControladorFacturas() {
        super();
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionFacturas);
        Vista vista = null;

        switch ((AccionFacturas) accion) {
            case ANYADIR_FACTURA:
                final VistaFacturas vistaFacturas = (VistaFacturas) getVista();
                vista = new VistaFacturaNueva(vistaFacturas.getCliente());
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
        return validate("Controlador tiene que ser del mismo tipo", vista, vista instanceof VistaFacturas);
    }
}
