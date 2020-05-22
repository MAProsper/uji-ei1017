package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonBuscar;
import app.helpers.clases.Manejador;
import app.helpers.clases.Modelo;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaError;
import app.ventanas.vistas.clases.VistaFacturaBuscar;

public class ControladorFacturaBuscar extends Controlador {
    public ControladorFacturaBuscar() {
        super();
    }

    @Override
    protected boolean validateVista(final Vista vista) {
        return vista instanceof VistaFacturaBuscar;
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
                final VistaFacturaBuscar vistaActual = (VistaFacturaBuscar) getVista();
                final int codigo = vistaActual.getCodigo();
                vista = VistaError.attempt(() -> modelo.buscarCliente(codigo), Manejador::getVisor);
                break;
            case VOLVER:
                break;
            default:
                throw Button.MISSING;
        }

        vistaNext(vista);
    }
}
