package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonBuscar;
import app.componentes.textboxes.TextboxClienteBuscar;
import app.helpers.clases.Manejador;
import app.helpers.clases.Modelo;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaClienteBuscar;
import app.ventanas.vistas.clases.VistaError;

public class ControladorClienteBuscar extends Controlador {
    public ControladorClienteBuscar() {
        super();
    }

    @Override
    protected boolean validateVista(final Vista vista) {
        return vista instanceof VistaClienteBuscar;
    }

    @Override
    public boolean validateButton(final Button button) {
        return button instanceof ButtonBuscar;
    }

    @Override
    public void gestionaButton(final Button button) {
        super.gestionaButton(button);
        Vista vista = null;

        switch ((ButtonBuscar) button) {
            case BUSCAR:
                final Modelo modelo = getModelo();
                final VistaClienteBuscar vistaActual = (VistaClienteBuscar) getVista();
                final String NIF = vistaActual.getTextbox(TextboxClienteBuscar.NIF);
                vista = VistaError.attempt(() -> modelo.buscarCliente(NIF), Manejador::getVisor);
                break;
            case VOLVER:
                break;
            default:
                throw Button.MISSING;
        }

        vistaNext(vista);
    }
}
