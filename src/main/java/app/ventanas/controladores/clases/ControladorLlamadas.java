package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonLlamadas;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaLlamadaNueva;
import app.ventanas.vistas.clases.VistaLlamadas;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public class ControladorLlamadas extends Controlador {
    public ControladorLlamadas() {
        super();
    }

    @Override
    public void gestionaButton(final Button button) {
        validate("Button tiene que ser de este controlador", button instanceof ButtonLlamadas);
        Vista vista = null;

        switch ((ButtonLlamadas) button) {
            case NUEVA_LLAMADA:
                final VistaLlamadas vistaActual = (VistaLlamadas) getVista();
                vista = new VistaLlamadaNueva(vistaActual.getCliente());
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
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaLlamadas);
    }
}
