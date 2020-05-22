package app.ventanas.controladores.clases;

import app.componentes.textboxes.TextboxClienteNuevo;
import app.helpers.estaticos.Parser;
import app.ventanas.controladores.abstractas.ControladorNuevo;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaClienteNuevo;
import clientes.Cliente;
import helpers.clases.Direccion;
import tarifas.Tarifa;
import tarifas.TarifaBase;

public class ControladorClienteNuevo extends ControladorNuevo {
    public ControladorClienteNuevo(){
        super();
    }

    @Override
    protected boolean validateVista(final Vista vista) {
        return vista instanceof VistaClienteNuevo;
    }

    @Override
    protected void crear() {
        final VistaClienteNuevo vistaActual = (VistaClienteNuevo) getVista();

        final String NIF = vistaActual.getTextbox(TextboxClienteNuevo.NIF);
        final String nombre = vistaActual.getTextbox(TextboxClienteNuevo.NOMBRE);
        final String codigoPostal = vistaActual.getTextbox(TextboxClienteNuevo.CODIGO_POSTAL);
        final String porvincia = vistaActual.getTextbox(TextboxClienteNuevo.PROVINCIA);
        final String poblacion = vistaActual.getTextbox(TextboxClienteNuevo.POBLACION);
        final String correo = vistaActual.getTextbox(TextboxClienteNuevo.CORREO);
        final String fechaAlta = vistaActual.getTextbox(TextboxClienteNuevo.FECHA_ALTA);
        final String tarifaBase = vistaActual.getTextbox(TextboxClienteNuevo.TARIFA);

        final Tarifa tarifa = new TarifaBase(Parser.real(TextboxClienteNuevo.TARIFA.getDescription(), tarifaBase));
        final Direccion direccion = new Direccion(Parser.entreo(TextboxClienteNuevo.CODIGO_POSTAL.getDescription(), codigoPostal), porvincia, poblacion);
        final Cliente cliente = vistaActual.getFactoria().getCliente(NIF, nombre, direccion, correo, Parser.fecha(TextboxClienteNuevo.FECHA_ALTA.getDescription(), fechaAlta), tarifa);

        getModelo().addCliente(cliente);
    }
}
