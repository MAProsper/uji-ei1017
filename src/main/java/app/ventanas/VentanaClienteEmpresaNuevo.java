package app.ventanas;

import clientes.Cliente;
import clientes.ClienteEmpresa;
import helpers.Direccion;
import tarifas.Tarifa;

import java.time.format.DateTimeParseException;

import static helpers.Fecha.parse;
import static helpers.ValidatorArguments.stringNotEmpty;

public class VentanaClienteEmpresaNuevo extends Ventana {
    public VentanaClienteEmpresaNuevo() {
        super(
                "Cliente (empresa)",
                "Rellena los datos del nuevo cliente",
                false, Textbox.values(), Button.values());
    }

    @Override
    public void update() {
    }

    @Override
    public Ventana handle(final app.Button button) {
        Ventana ventana = null;

        switch ((Button) button) {
            case CREAR:
                Cliente cliente = crearCliente();
                if (cliente == null) ventana = new VentanaError();
                else getGestor().addCliente(cliente);
                break;
            case VOLVER:
                break;
        }

        return ventana;
    }

    Cliente crearCliente() {
        Cliente cliente = null;
        final String NIF = getTextbox(Textbox.NIF);
        final String nombre = getTextbox(Textbox.NOMBRE);
        final String codigoPostal = getTextbox(Textbox.CODIGO_POSTAL);
        final String porvincia = getTextbox(Textbox.PROVINCIA);
        final String poblacion = getTextbox(Textbox.POBLACION);
        final String correo = getTextbox(Textbox.CORREO);
        final String fechaAlta = getTextbox(Textbox.FECHA_ALTA);
        final String tarifaBase = getTextbox(Textbox.TARIFA_BASE);

        try {
            final Tarifa tarifa = new Tarifa(Double.parseDouble(tarifaBase));
            final Direccion direccion = new Direccion(Integer.parseInt(codigoPostal), porvincia, poblacion);
            cliente = new ClienteEmpresa(NIF, nombre, direccion, correo, parse(fechaAlta), tarifa);
        } catch (DateTimeParseException | IllegalArgumentException ignored) {
        }

        return cliente;
    }

    enum Textbox implements app.Textbox {
        NIF("NIF"),
        NOMBRE("Nombre"),
        CODIGO_POSTAL("Codigo postal"),
        PROVINCIA("Provincia"),
        POBLACION("Poblacion"),
        CORREO("Correo electronico"),
        FECHA_ALTA("Fecha de alta (YYYY-MM-DD)"),
        TARIFA_BASE("Tarifa base");

        final String description;

        Textbox(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }

    enum Button implements app.Button {
        CREAR("Crear"),
        VOLVER("Volver");

        final String description;

        Button(final String description) {
            this.description = stringNotEmpty("descripcion", description);
        }

        public String getDescription() {
            return description;
        }
    }
}
