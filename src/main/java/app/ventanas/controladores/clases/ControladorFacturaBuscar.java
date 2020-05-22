package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonBuscar;
import app.helpers.clases.Manejador;
import app.helpers.clases.Modelo;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaError;
import app.ventanas.vistas.clases.VistaFacturaBuscar;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public class ControladorFacturaBuscar extends Controlador {
    public ControladorFacturaBuscar() {
        super();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaFacturaBuscar);
    }

    @Override
    public void gestionaButton(final Button button) {
        validate("Button tiene que ser de este controlador", button instanceof ButtonBuscar);
        Vista vista = null;

        switch ((ButtonBuscar) button) {
            case BUSCAR:
                final Modelo modelo = getModelo();
                final VistaFacturaBuscar vistaActual = (VistaFacturaBuscar) getVista();
                final int codigo = vistaActual.getCodigo();
                vista = VistaError.attempt(() -> modelo.buscarCliente(codigo), Manejador::getVisor);
                break;
            case VOLVER:
                break;
            default:
                throw new Arguments.ValidationException("Button no clasificado");
        }

        vistaNext(vista);
    }
}
