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

import java.util.List;
import java.util.Optional;

public class ControladorClientes extends Controlador {
    public ControladorClientes() {
        super();
    }

    @Override
    protected boolean validateVista(final Vista vista) {
        return vista instanceof VistaClientes;
    }

    @Override
    public boolean validateButton(final Button button) {
        return button instanceof ButtonClientes;
    }

    @Override
    public void gestionaButton(final Button button) {
        super.gestionaButton(button);
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
                throw Button.MISSING;
        }

        vistaNext(vista);
    }
}
