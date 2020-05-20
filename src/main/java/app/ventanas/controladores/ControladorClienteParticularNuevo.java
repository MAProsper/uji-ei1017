package app.ventanas.controladores;

import app.helpers.Parser;
import app.ventanas.abstractas.ControladorNuevo;
import app.ventanas.abstractas.Vista;
import app.ventanas.textboxes.TextboxClienteParticularNuevo;
import app.ventanas.vistas.VistaClienteNuevo;
import app.ventanas.vistas.VistaClienteParticular;
import app.ventanas.vistas.VistaClienteParticularNuevo;
import clientes.Cliente;
import helpers.clases.Direccion;
import tarifas.Tarifa;
import tarifas.TarifaBase;

public class ControladorClienteParticularNuevo extends ControladorClienteNuevo {

    public ControladorClienteParticularNuevo(){
        super();
    }

    @Override
    protected void crear() {

        final VistaClienteParticularNuevo vistaClienteParticularnuevo = (VistaClienteParticularNuevo) getVista();

        final String NIF = vistaClienteParticularnuevo.getTextbox(TextboxClienteParticularNuevo.NIF);
        final String apellidos = vistaClienteParticularnuevo.getTextbox(TextboxClienteParticularNuevo.APELLIDOS);
        final String nombre = vistaClienteParticularnuevo.getTextbox(TextboxClienteParticularNuevo.NOMBRE);
        final String codigoPostal = vistaClienteParticularnuevo.getTextbox(TextboxClienteParticularNuevo.CODIGO_POSTAL);
        final String porvincia = vistaClienteParticularnuevo.getTextbox(TextboxClienteParticularNuevo.PROVINCIA);
        final String poblacion = vistaClienteParticularnuevo.getTextbox(TextboxClienteParticularNuevo.POBLACION);
        final String correo = vistaClienteParticularnuevo.getTextbox(TextboxClienteParticularNuevo.CORREO);
        final String fechaAlta = vistaClienteParticularnuevo.getTextbox(TextboxClienteParticularNuevo.FECHA_ALTA);
        final String tarifaBase = vistaClienteParticularnuevo.getTextbox(TextboxClienteParticularNuevo.TARIFA);

        final Tarifa tarifa = new TarifaBase(Parser.real(TextboxClienteParticularNuevo.TARIFA.getDescription(), tarifaBase));
        final Direccion direccion = new Direccion(Parser.entreo(TextboxClienteParticularNuevo.CODIGO_POSTAL.getDescription(), codigoPostal), porvincia, poblacion);
        final Cliente cliente = vistaClienteParticularnuevo.getFactoria().getCliente(NIF, apellidos, nombre, direccion, correo, Parser.fecha(TextboxClienteParticularNuevo.FECHA_ALTA.getDescription(), fechaAlta), tarifa);

        getModelo().addCliente(cliente);
    }


}
