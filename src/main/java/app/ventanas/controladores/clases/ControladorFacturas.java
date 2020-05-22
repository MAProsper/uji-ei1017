package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonFacturas;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaFacturaNueva;
import app.ventanas.vistas.clases.VistaFacturas;

public class ControladorFacturas extends Controlador {
    public ControladorFacturas() {
        super();
    }

    @Override
    public boolean validateButton(final Button button) {
        return button instanceof ButtonFacturas;
    }

    @Override
    public void gestionaButton(final Button button) {
        super.gestionaButton(button);
        Vista vista = null;

        switch ((ButtonFacturas) button) {
            case ANYADIR_FACTURA:
                final VistaFacturas vistaActual = (VistaFacturas) getVista();
                vista = new VistaFacturaNueva(vistaActual.getCliente());
                break;
            case VOLVER:
                break;
            default:
                throw Button.MISSING;
        }

        vistaNext(vista);
    }

    @Override
    protected boolean validateVista(final Vista vista) {
        return vista instanceof VistaFacturas;
    }
}
