package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonBuscar;
import app.helpers.interfaces.FactoryVistaRango;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaError;
import app.ventanas.vistas.clases.VistaRangoBuscar;

public class ControladorRangoBuscar extends Controlador {
    public ControladorRangoBuscar() {
        super();
    }

    @Override
    protected boolean validateVista(final Vista vista) {
        return vista instanceof VistaRangoBuscar;
    }

    @Override
    protected boolean validateButton(Button button) {
        return button instanceof ButtonBuscar;
    }

    @Override
    public void gestionaButton(final Button button) {
        super.gestionaButton(button);
        Vista vista = null;

        switch ((ButtonBuscar) button) {
            case BUSCAR:
                final VistaRangoBuscar vistaActual = (VistaRangoBuscar) getVista();
                final FactoryVistaRango factoria = vistaActual.getTipo();
                vista = VistaError.attempt(vistaActual::getPeriodo, factoria::getVista);
                break;
            case VOLVER:
                break;
        }

        vistaNext(vista);
    }
}
