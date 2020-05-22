package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonFacturas;
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
    public void gestionaButton(final Button button) {
        validate("Button tiene que ser de este controlador", button instanceof ButtonFacturas);
        Vista vista = null;

        switch ((ButtonFacturas) button) {
            case ANYADIR_FACTURA:
                final VistaFacturas vistaActual = (VistaFacturas) getVista();
                vista = new VistaFacturaNueva(vistaActual.getCliente());
                break;
            case VOLVER:
                break;
            default:
                throw new Arguments.ValidationException("Button no clasificado");
        }

        vistaNext(vista);
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaFacturas);
    }
}
