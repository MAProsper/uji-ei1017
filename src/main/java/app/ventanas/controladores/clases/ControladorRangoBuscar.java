package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonBuscar;
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
    public void gestionaButton(final Button button) {
        validate("Acción tiene que ser esta vista", button instanceof ButtonBuscar);
        Vista vista = null;

        switch ((ButtonBuscar) button) {
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
