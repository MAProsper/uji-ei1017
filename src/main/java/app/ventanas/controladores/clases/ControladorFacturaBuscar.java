package app.ventanas.controladores.clases;

import app.Modelo;
import app.componentes.Accion;
import app.componentes.acciones.AccionBuscar;
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
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionBuscar);
        Vista vista = null;

        switch ((AccionBuscar) accion) {
            case BUSCAR:
                final Modelo modelo = getModelo();
                final VistaFacturaBuscar vistaActual = (VistaFacturaBuscar) getVista();
                final int codigo = vistaActual.getCodigo();
                vista = VistaError.attempt(() -> modelo.buscarCliente(codigo), modelo::getVisor);
                break;
            case VOLVER:
                break;
            default:
                throw new Arguments.ValidationException("Acción no clasificada");
        }

        vistaNext(vista);
    }
}
