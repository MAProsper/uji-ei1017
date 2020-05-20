package app.ventanas.controladores;

import app.Modelo;
import app.ventanas.abstractas.Controlador;
import app.ventanas.abstractas.Vista;
import app.ventanas.acciones.AccionClientes;
import app.ventanas.interfaces.Accion;
import app.ventanas.tables.TableClientes;
import app.ventanas.vistas.*;
import helpers.estaticos.Arguments;
import helpers.interfaces.FactoryClientes;

import java.util.List;
import java.util.Optional;

import static helpers.estaticos.Arguments.validate;

public class ControladorClientes extends Controlador {

    public ControladorClientes(){
        super();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Controlador tiene que ser del mismo tipo", vista, vista instanceof VistaClientes);
    }

    @Override
    public void gestionaAccion(final Accion accion) {
        validate("Acción tiene que ser esta ventana", accion instanceof AccionClientes);
        Vista vista = null;
        switch ((AccionClientes) accion) {
            case VER_CLIENTE:

                final VistaClientes vistaClientes = (VistaClientes) getVista();
                final Optional<List<String>> selection = vistaClientes.getSelectedRow();

                if (selection.isPresent()) {
                    final List<String> row = selection.get();
                    final String NIF = row.get(TableClientes.NIF.ordinal());

                    // Modelo.buscarCliente (3. consulta datos del modelo)
                    final Modelo modelo = getModelo();
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
        showNext(vista);
    }
}
