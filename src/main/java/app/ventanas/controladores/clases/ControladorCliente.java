package app.ventanas.controladores.clases;

import app.componentes.Accion;
import app.componentes.acciones.AccionCliente;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaCliente;
import app.ventanas.vistas.clases.VistaFacturas;
import app.ventanas.vistas.clases.VistaLlamadas;
import app.ventanas.vistas.clases.VistaTarfias;
import clientes.Cliente;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public class ControladorCliente extends Controlador {
    public ControladorCliente(){
        super();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaCliente);
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser de este controlador", accion instanceof AccionCliente);
        Vista vista = null;

        final VistaCliente vistaActual = (VistaCliente) getVista();
        final Cliente cliente = vistaActual.getCliente();

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

        vistaNext(vista);
    }
}
