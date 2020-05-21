package app.ventanas.controladores.clases;

import app.componentes.textboxes.TextboxClienteParticularNuevo;
import app.helpers.estaticos.Parser;
import app.ventanas.vistas.abstractas.Vista;
import app.ventanas.vistas.clases.VistaClienteParticularNuevo;
import clientes.Cliente;
import helpers.clases.Direccion;
import tarifas.Tarifa;
import tarifas.TarifaBase;

import static helpers.estaticos.Arguments.validate;

public class ControladorClienteParticularNuevo extends ControladorClienteNuevo {
    public ControladorClienteParticularNuevo() {
        super();
    }

    @Override
    protected Vista validateVista(final Vista vista) {
        return validate("Vista tiene que ser del mismo tipo", vista, vista instanceof VistaClienteParticularNuevo);
    }

    @Override
    protected void crear() {
        final VistaClienteParticularNuevo vistaActual = (VistaClienteParticularNuevo) getVista();

        final String NIF = vistaActual.getTextbox(TextboxClienteParticularNuevo.NIF);
        final String apellidos = vistaActual.getTextbox(TextboxClienteParticularNuevo.APELLIDOS);
        final String nombre = vistaActual.getTextbox(TextboxClienteParticularNuevo.NOMBRE);
        final String codigoPostal = vistaActual.getTextbox(TextboxClienteParticularNuevo.CODIGO_POSTAL);
        final String porvincia = vistaActual.getTextbox(TextboxClienteParticularNuevo.PROVINCIA);
        final String poblacion = vistaActual.getTextbox(TextboxClienteParticularNuevo.POBLACION);
        final String correo = vistaActual.getTextbox(TextboxClienteParticularNuevo.CORREO);
        final String fechaAlta = vistaActual.getTextbox(TextboxClienteParticularNuevo.FECHA_ALTA);
        final String tarifaBase = vistaActual.getTextbox(TextboxClienteParticularNuevo.TARIFA);

        final Tarifa tarifa = new TarifaBase(Parser.real(TextboxClienteParticularNuevo.TARIFA.getDescription(), tarifaBase));
        final Direccion direccion = new Direccion(Parser.entreo(TextboxClienteParticularNuevo.CODIGO_POSTAL.getDescription(), codigoPostal), porvincia, poblacion);
        final Cliente cliente = vistaActual.getFactoria().getCliente(NIF, apellidos, nombre, direccion, correo, Parser.fecha(TextboxClienteParticularNuevo.FECHA_ALTA.getDescription(), fechaAlta), tarifa);

        getModelo().addCliente(cliente);
    }
}
