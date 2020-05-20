package app.ventanas.controladores;

import app.Modelo;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.Vista;
import app.ventanas.acciones.AccionFacturaBuscar;
import app.ventanas.interfaces.Accion;
import app.ventanas.vistas.VistaError;
import app.ventanas.vistas.VistaFacturaBuscar;
import helpers.estaticos.Arguments;

import static helpers.estaticos.Arguments.validate;

public class ControladorFacturaBuscar extends Controlador {
    public ControladorFacturaBuscar() {
        super();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Controlador tiene que ser del mismo tipo", vista, vista instanceof VistaFacturaBuscar);
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionFacturaBuscar);
        Vista vista = null;

        switch ((AccionFacturaBuscar) accion) {
            case BUSCAR:
                final Modelo modelo = getModelo();
                final VistaFacturaBuscar vistaBusqueda = (VistaFacturaBuscar) getVista();
                final int codigo = vistaBusqueda.getCodigo();
                vista = VistaError.attempt(() -> modelo.buscarCliente(codigo), modelo::getVisor);
                break;
            case VOLVER:
                break;
            default:
                throw new Arguments.ValidationException("Acción no clasificada");
        }

        showNext(vista);
    }
}
