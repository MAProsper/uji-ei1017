package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonCliente;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaCliente;
import app.ventanas.vistas.clases.VistaFacturas;
import app.ventanas.vistas.clases.VistaLlamadas;
import app.ventanas.vistas.clases.VistaTarfias;
import clientes.Cliente;

public class ControladorCliente extends Controlador {
    public ControladorCliente() {
        super();
    }

    @Override
    protected boolean validateVista(final Vista vista) {
        return vista instanceof VistaCliente;
    }

    @Override
    public boolean validateButton(final Button button) {
        return button instanceof ButtonCliente;
    }

    @Override
    public void gestionaButton(final Button button) {
        super.gestionaButton(button);
        Vista vista = null;

        final VistaCliente vistaActual = (VistaCliente) getVista();
        final Cliente cliente = vistaActual.getCliente();

        switch ((ButtonCliente) button) {
            case VER_LLAMADAS:
                vista = new VistaLlamadas(cliente);
                break;
            case VER_FACTURAS:
                vista = new VistaFacturas(cliente);
                break;
            case ANYADIR_TARIFAS:
                vista = new VistaTarfias(cliente);
                break;
            case BORRAR_CLIENTE:
                getModelo().removeCliente(cliente);
                break;
            case VOLVER:
                break;
            default:
                throw Button.MISSING;
        }

        vistaNext(vista);
    }
}
