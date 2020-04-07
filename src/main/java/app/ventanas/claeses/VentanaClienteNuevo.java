package app.ventanas.claeses;

import app.Parser;
import app.ventanas.abstractas.VentanaNuevo;
import app.ventanas.interfaces.FactoryClientes;
import clientes.Cliente;
import helpers.clases.Direccion;
import tarifas.Tarifa;

import static helpers.estaticos.Arguments.referenceNotNull;
import static helpers.estaticos.Arguments.stringNotEmpty;

public class VentanaClienteNuevo extends VentanaNuevo {
    protected final FactoryClientes factoria;

    public VentanaClienteNuevo(final FactoryClientes factoria) {
        this(Textbox.values(), factoria);
    }

    protected VentanaClienteNuevo(final app.ventanas.interfaces.Textbox[] texboxes, final FactoryClientes factoria) {
        super(texboxes);
        this.factoria = referenceNotNull("factoria", factoria);
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
        final String tarifaBase = getTextbox(Textbox.TARIFA);

        final Tarifa tarifa = new Tarifa(Parser.real(Textbox.TARIFA.getDescription(), tarifaBase));
        final Direccion direccion = new Direccion(Parser.entreo(Textbox.CODIGO_POSTAL.getDescription(), codigoPostal), porvincia, poblacion);
        final Cliente cliente = factoria.getCliente(NIF, nombre, direccion, correo, Parser.fecha(Textbox.FECHA_ALTA.getDescription(), fechaAlta), tarifa);

        getGestor().addCliente(cliente);
    }

    public enum Textbox implements app.ventanas.interfaces.Textbox {
        NIF("NIF"),
        NOMBRE("Nombre"),
        CODIGO_POSTAL("Codigo postal"),
        PROVINCIA("Provincia"),
        POBLACION("Poblacion"),
        CORREO("Correo electronico"),
        FECHA_ALTA("Fecha de alta"),
        TARIFA("Tarifa");

        private final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("Descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
