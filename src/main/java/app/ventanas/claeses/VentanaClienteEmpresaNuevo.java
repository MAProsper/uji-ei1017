package app.ventanas.claeses;

import app.Parser;
import clientes.Cliente;
import helpers.clases.Direccion;
import helpers.interfaces.FactoryClientes;
import tarifas.Tarifa;
import tarifas.TarifaBase;

import static helpers.estaticos.Arguments.stringNotEmpty;

// Relacion Vista-Controlador
public class VentanaClienteEmpresaNuevo extends VentanaClienteNuevo {

    // Vista (define la vista contreta)
    public VentanaClienteEmpresaNuevo(final FactoryClientes factoria) {
        super(Textbox.values(), factoria);
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

    // Controlador (define el controlador concreto)
    @Override
    protected void crear() {
        // Vista.getTextbox (2. solicita datos a la vista)
        final String NIF = getTextbox(Textbox.NIF);
        final String nombre = getTextbox(Textbox.NOMBRE);
        final String codigoPostal = getTextbox(Textbox.CODIGO_POSTAL);
        final String porvincia = getTextbox(Textbox.PROVINCIA);
        final String poblacion = getTextbox(Textbox.POBLACION);
        final String correo = getTextbox(Textbox.CORREO);
        final String fechaAlta = getTextbox(Textbox.FECHA_ALTA);
        final String tarifaBase = getTextbox(Textbox.TARIFA);

        final Tarifa tarifa = new TarifaBase(Parser.real(Textbox.TARIFA.getDescription(), tarifaBase));
        final Direccion direccion = new Direccion(Parser.entreo(Textbox.CODIGO_POSTAL.getDescription(), codigoPostal), porvincia, poblacion);
        final Cliente cliente = factoria.getCliente(NIF, nombre, direccion, correo, Parser.fecha(Textbox.FECHA_ALTA.getDescription(), fechaAlta), tarifa);

        // Modelo.addClientes (3. actualiza el modelo)
        getGestor().addCliente(cliente);
    }

}
