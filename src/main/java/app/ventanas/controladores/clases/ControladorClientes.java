package app.ventanas.controladores.clases;

import app.Modelo;
import app.componentes.Accion;
import app.componentes.acciones.AccionClientes;
import app.componentes.tables.TableClientes;
import app.helpers.interfaces.FactoryClientes;
import app.ventanas.controladores.abstractas.Controlador;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.*;
import helpers.estaticos.Arguments;

import java.util.List;
import java.util.Optional;

import static helpers.estaticos.Arguments.validate;

public class ControladorClientes extends Controlador {
    public ControladorClientes(){
        super();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaClientes);
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionClientes);
        Vista vista = null;

        switch ((AccionClientes) accion) {
            case VER_CLIENTE:
                final VistaClientes vistaActual = (VistaClientes) getVista();
                final Optional<List<String>> selection = vistaActual.getSelectedRow();

                if (selection.isPresent()) {
                    final Modelo modelo = getModelo();
                    final List<String> row = selection.get();
                    final String NIF = row.get(TableClientes.NIF.ordinal());
                    vista = modelo.getVisor(modelo.buscarCliente(NIF));
                } else {
                    vista = new VistaError("No se ha selecionado ningun cliente");
                }

                break;
            case NUEVO_CLIENTE:
               vista = new VistaClienteNuevo((FactoryClientes) accion);
                break;
            case NUEVO_PARTICULAR:
                vista = new VistaClienteParticularNuevo((FactoryClientes) accion);
                break;
            case NUEVO_EMPRESA:
                vista = new VistaClienteEmpresaNuevo((FactoryClientes) accion);
                break;
            case VOLVER:
                break;
            default:
                throw new Arguments.ValidationException("Acción no clasificada");
        }

        vistaNext(vista);
    }
}
