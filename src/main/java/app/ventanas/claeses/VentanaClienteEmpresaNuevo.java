package app.ventanas.claeses;

import app.ventanas.abstractas.VentanaNuevo;
import clientes.ClienteEmpresa;
import helpers.clases.Direccion;
import helpers.estaticos.Parse;
import tarifas.Tarifa;

import static helpers.estaticos.Arguments.stringNotEmpty;

public class VentanaClienteEmpresaNuevo extends VentanaNuevo {
    public VentanaClienteEmpresaNuevo() {
        super(Textbox.values());
    }

    @Override
    protected void crear() {
        final String NIF = getTextbox(Textbox.NIF);
        final String nombre = getTextbox(Textbox.NOMBRE);
        final String codigoPostal = getTextbox(Textbox.CODIGO_POSTAL);
        final String porvincia = getTextbox(Textbox.PROVINCIA);
        final String poblacion = getTextbox(Textbox.POBLACION);
        final String correo = getTextbox(Textbox.CORREO);
        final String fechaAlta = getTextbox(Textbox.FECHA_ALTA);
        final String tarifaBase = getTextbox(Textbox.TARIFA_BASE);

        final Tarifa tarifa = new Tarifa(Parse.real(Textbox.TARIFA_BASE.getDescription(), tarifaBase));
        final Direccion direccion = new Direccion(Parse.entreo(Textbox.CODIGO_POSTAL.getDescription(), codigoPostal), porvincia, poblacion);
        final ClienteEmpresa cliente = new ClienteEmpresa(NIF, nombre, direccion, correo, Parse.fecha(Textbox.FECHA_ALTA.getDescription(), fechaAlta), tarifa);

        getGestor().addCliente(cliente);
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        NIF("NIF"),
        NOMBRE("Nombre"),
        CODIGO_POSTAL("Codigo postal"),
        PROVINCIA("Provincia"),
        POBLACION("Poblacion"),
        CORREO("Correo electronico"),
        FECHA_ALTA("Fecha de alta (YYYY-MM-DD)"),
        TARIFA_BASE("Tarifa base");

        private final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
