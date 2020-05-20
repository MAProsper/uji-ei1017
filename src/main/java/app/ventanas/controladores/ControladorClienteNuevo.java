package app.ventanas.controladores;

import app.helpers.Parser;
import app.ventanas.abstractas.ControladorNuevo;
import app.ventanas.abstractas.Vista;
import app.ventanas.textboxes.TextboxClienteNuevo;
import app.ventanas.vistas.VistaClienteNuevo;
import clientes.Cliente;
import helpers.clases.Direccion;
import tarifas.Tarifa;
import tarifas.TarifaBase;

import static helpers.estaticos.Arguments.validate;

public class ControladorClienteNuevo extends ControladorNuevo {

    public ControladorClienteNuevo(){
        super();
    }

    @Override
    protected Vista validateVista(Vista vista) {
        return validate("Controlador tiene que ser del mismo tipo", vista, vista instanceof VistaClienteNuevo);
    }

    @Override
    protected void crear() {

        final VistaClienteNuevo vistaClientenuevo = (VistaClienteNuevo) getVista();

        final String NIF = vistaClientenuevo.getTextbox(TextboxClienteNuevo.NIF);
        final String nombre = vistaClientenuevo.getTextbox(TextboxClienteNuevo.NOMBRE);
        final String codigoPostal = vistaClientenuevo.getTextbox(TextboxClienteNuevo.CODIGO_POSTAL);
        final String porvincia = vistaClientenuevo.getTextbox(TextboxClienteNuevo.PROVINCIA);
        final String poblacion = vistaClientenuevo.getTextbox(TextboxClienteNuevo.POBLACION);
        final String correo = vistaClientenuevo.getTextbox(TextboxClienteNuevo.CORREO);
        final String fechaAlta = vistaClientenuevo.getTextbox(TextboxClienteNuevo.FECHA_ALTA);
        final String tarifaBase = vistaClientenuevo.getTextbox(TextboxClienteNuevo.TARIFA);

        final Tarifa tarifa = new TarifaBase(Parser.real(TextboxClienteNuevo.TARIFA.getDescription(), tarifaBase));
        final Direccion direccion = new Direccion(Parser.entreo(TextboxClienteNuevo.CODIGO_POSTAL.getDescription(), codigoPostal), porvincia, poblacion);
        final Cliente cliente = vistaClientenuevo.getFactoria().getCliente(NIF, nombre, direccion, correo, Parser.fecha(TextboxClienteNuevo.FECHA_ALTA.getDescription(), fechaAlta), tarifa);

        getModelo().addCliente(cliente);
    }
}
