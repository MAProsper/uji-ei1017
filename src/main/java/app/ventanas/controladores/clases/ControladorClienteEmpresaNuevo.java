package app.ventanas.controladores.clases;

import app.componentes.textboxes.TextboxClienteEmpresaNuevo;
import app.helpers.estaticos.Parser;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaClienteEmpresaNuevo;
import clientes.Cliente;
import helpers.clases.Direccion;
import tarifas.Tarifa;
import tarifas.TarifaBase;

public class ControladorClienteEmpresaNuevo extends ControladorClienteNuevo {
    public ControladorClienteEmpresaNuevo() {
        super();
    }

    @Override
    protected boolean validateVista(final Vista vista) {
        return vista instanceof VistaClienteEmpresaNuevo;
    }

    @Override
    protected void crear() {
        final VistaClienteEmpresaNuevo vistaActual = (VistaClienteEmpresaNuevo) getVista();

        final String NIF = vistaActual.getTextbox(TextboxClienteEmpresaNuevo.NIF);
        final String nombre = vistaActual.getTextbox(TextboxClienteEmpresaNuevo.NOMBRE);
        final String codigoPostal = vistaActual.getTextbox(TextboxClienteEmpresaNuevo.CODIGO_POSTAL);
        final String porvincia = vistaActual.getTextbox(TextboxClienteEmpresaNuevo.PROVINCIA);
        final String poblacion = vistaActual.getTextbox(TextboxClienteEmpresaNuevo.POBLACION);
        final String correo = vistaActual.getTextbox(TextboxClienteEmpresaNuevo.CORREO);
        final String fechaAlta = vistaActual.getTextbox(TextboxClienteEmpresaNuevo.FECHA_ALTA);
        final String tarifaBase = vistaActual.getTextbox(TextboxClienteEmpresaNuevo.TARIFA);

        final Tarifa tarifa = new TarifaBase(Parser.real(TextboxClienteEmpresaNuevo.TARIFA.getDescription(), tarifaBase));
        final Direccion direccion = new Direccion(Parser.entreo(TextboxClienteEmpresaNuevo.CODIGO_POSTAL.getDescription(), codigoPostal), porvincia, poblacion);
        final Cliente cliente = vistaActual.getFactoria().getCliente(NIF, nombre, direccion, correo, Parser.fecha(TextboxClienteEmpresaNuevo.FECHA_ALTA.getDescription(), fechaAlta), tarifa);

        getModelo().addCliente(cliente);
    }
}
