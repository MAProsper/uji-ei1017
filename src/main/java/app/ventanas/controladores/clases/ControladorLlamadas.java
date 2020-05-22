package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonLlamadas;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaLlamadaNueva;
import app.ventanas.vistas.clases.VistaLlamadas;

public class ControladorLlamadas extends Controlador {
    public ControladorLlamadas() {
        super();
    }

    @Override
    public boolean validateButton(final Button button) {
        return button instanceof ButtonLlamadas;
    }

    @Override
    public void gestionaButton(final Button button) {
        super.gestionaButton(button);
        Vista vista = null;

        switch ((ButtonLlamadas) button) {
            case NUEVA_LLAMADA:
                final VistaLlamadas vistaActual = (VistaLlamadas) getVista();
                vista = new VistaLlamadaNueva(vistaActual.getCliente());
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
        return vista instanceof VistaLlamadas;
    }
}
