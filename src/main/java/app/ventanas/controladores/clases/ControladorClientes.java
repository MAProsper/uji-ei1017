package app.ventanas.controladores.clases;

import app.componentes.Button;
import app.componentes.buttons.ButtonClientes;
import app.componentes.tables.TableClientes;
import app.helpers.clases.Manejador;
import app.helpers.clases.Modelo;
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
    public void gestionaButton(final Button button) {
        validate("Button tiene que ser de este controlador", button instanceof ButtonClientes);
        Vista vista = null;

        switch ((ButtonClientes) button) {
            case VER_CLIENTE:
                final VistaClientes vistaActual = (VistaClientes) getVista();
                final Optional<List<String>> selection = vistaActual.getSelectedRow();

                if (selection.isPresent()) {
                    final Modelo modelo = getModelo();
                    final List<String> row = selection.get();
                    final String NIF = row.get(TableClientes.NIF.ordinal());
                    vista = Manejador.getVisor(modelo.buscarCliente(NIF));
                } else {
                    vista = new VistaError("No se ha selecionado ningun cliente");
                }

                break;
            case NUEVO_CLIENTE:
                vista = new VistaClienteNuevo((FactoryClientes) button);
                break;
            case NUEVO_PARTICULAR:
                vista = new VistaClienteParticularNuevo((FactoryClientes) button);
                break;
            case NUEVO_EMPRESA:
                vista = new VistaClienteEmpresaNuevo((FactoryClientes) button);
                break;
            case VOLVER:
                break;
            default:
                throw new Arguments.ValidationException("Button no clasificado");
        }

        vistaNext(vista);
    }
}
