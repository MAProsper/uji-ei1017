package app.ventanas.controladores;

import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.Vista;
import app.ventanas.acciones.AccionCliente;
import app.ventanas.interfaces.Accion;
import app.ventanas.vistas.VistaCliente;
import app.ventanas.vistas.VistaFacturas;
import app.ventanas.vistas.VistaLlamadas;
import app.ventanas.vistas.VistaTarfias;
import clientes.Cliente;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public class ControladorCliente extends Controlador {

    public ControladorCliente(){
        super();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Controlador tiene que ser del mismo tipo", vista, vista instanceof VistaCliente);
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionCliente);
        Vista vista = null;
        final VistaCliente vistaCliente = (VistaCliente) getVista();
        final Cliente cliente = vistaCliente.getCliente();

        switch ((AccionCliente) accion) {
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
                throw new Arguments.ValidationException("Acción no clasificada");
        }
        showNext(vista);
    }
}
