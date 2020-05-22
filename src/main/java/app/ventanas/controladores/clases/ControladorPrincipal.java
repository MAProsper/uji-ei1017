package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonPrincipal;
import app.helpers.estaticos.TipoRangoBuscar;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.*;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public class ControladorPrincipal extends Controlador {
    public ControladorPrincipal() {
        super();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaPrincipal);
    }

    @Override
    public void gestionaButton(final Button button) {
        validate("Button tiene que ser de este controlador", button instanceof ButtonPrincipal);
        Vista vista = null;

        switch ((ButtonPrincipal) button) {
            case VER_CLIENTES:
                vista = new VistaClientes();
                break;
            case BUSCAR_CLIENTE_NIF:
                vista = new VistaClienteBuscar();
                break;
            case BUSCAR_CLIENTE_FACTURA:
                vista = new VistaFacturaBuscar();
                break;
            case BUSCAR_CLIENTE_RANGO:
                vista = new VistaRangoBuscar(TipoRangoBuscar.CLIENTES);
                break;
            case BUSCAR_FACTURA_RANGO:
                vista = new VistaRangoBuscar(TipoRangoBuscar.FACTURAS);
                break;
            case BUSCAR_LLAMADAS_RANGO:
                vista = new VistaRangoBuscar(TipoRangoBuscar.LLAMADAS);
                break;
            case CARGAR:
                vista = new VistaLoad();
                break;
            case GUARDAR:
                vista = new VistaSave();
                break;
            case CERRAR:
                break;
            default:
                throw new Arguments.ValidationException("Button no clasificado");
        }

        vistaNext(vista);
    }
}
